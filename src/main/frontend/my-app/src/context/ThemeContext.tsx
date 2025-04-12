import React, {
  createContext,
  useContext,
  useState,
  useEffect,
  ReactNode,
} from "react";

interface ThemeContextType {
  primaryColor: string;
  logoUrl: string | null;
  updateTheme: (color: string, logo: string | null) => void;
}

const DEFAULT_PRIMARY_COLOR = "#1ABC9C";

const ThemeContext = createContext<ThemeContextType | null>(null);

interface ThemeProviderProps {
  children: ReactNode;
}

export const ThemeProvider: React.FC<ThemeProviderProps> = ({ children }) => {
  const [primaryColor, setPrimaryColor] = useState<string>(
    DEFAULT_PRIMARY_COLOR
  );
  const [logoUrl, setLogoUrl] = useState<string | null>(null);

  // Load from localStorage on mount
  useEffect(() => {
    const storedColor = localStorage.getItem("primaryColor");
    const storedLogo = localStorage.getItem("logoUrl");

    if (storedColor) setPrimaryColor(storedColor);
    if (storedLogo) setLogoUrl(storedLogo);
  }, []);

  // Update CSS variable when primary color changes
  useEffect(() => {
    document.documentElement.style.setProperty(
      "--tw-prose-links",
      primaryColor
    );
  }, [primaryColor]);

  const updateTheme = (color: string, logo: string | null) => {
    setPrimaryColor(color);
    setLogoUrl(logo);

    localStorage.setItem("primaryColor", color);
    if (logo) {
      localStorage.setItem("logoUrl", logo);
    } else {
      localStorage.removeItem("logoUrl");
    }
  };

  return (
    <ThemeContext.Provider value={{ primaryColor, logoUrl, updateTheme }}>
      {children}
    </ThemeContext.Provider>
  );
};

export const useTheme = (): ThemeContextType => {
  const ctx = useContext(ThemeContext);
  if (!ctx) throw new Error("useTheme must be used within ThemeProvider");
  return ctx;
};
