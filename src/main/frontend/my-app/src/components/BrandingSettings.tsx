import React, { useState, useEffect } from "react";
import { getTheme, saveTheme, uploadLogo } from "../api/theme";
import { useTheme } from "../context/ThemeContext";

const BrandingSettings: React.FC = () => {
  const [color, setColor] = useState("#1ABC9C");
  const [logoFile, setLogoFile] = useState<File | null>(null);
  const [logoPreview, setLogoPreview] = useState<string | null>(null);
  const [logoUrl, setLogoUrl] = useState<string | null>(null);

  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);

  const { primaryColor, updateTheme } = useTheme();

  useEffect(() => {
    const fetchTheme = async () => {
      try {
        const theme = await getTheme();
        const fetchedColor = theme.color || primaryColor;
        const fetchedLogoUrl = theme.logoUrl || null;

        setColor(fetchedColor);
        setLogoUrl(fetchedLogoUrl);

        if (fetchedLogoUrl) {
          const base64 = await convertImageUrlToBase64(fetchedLogoUrl);
          setLogoPreview(base64);
        }
      } catch {
        setError("Error fetching theme settings");
      }
    };

    fetchTheme();
  }, [updateTheme]);

  const normalizeBase64Image = (base64: string): string => {
    if (base64.startsWith("data:image/")) return base64;

    const base64Index = base64.indexOf("base64,");
    const cleanBase64 =
      base64Index !== -1 ? base64.substring(base64Index + 7) : base64;

    return `data:image/png;base64,${cleanBase64}`;
  };

  const convertImageUrlToBase64 = async (url: string): Promise<string> => {
    const fullUrl = url.startsWith("http")
      ? url
      : `http://localhost:8080${url.startsWith("/") ? "" : "/"}${url}`;

    try {
      const response = await fetch(fullUrl, {
        method: "GET",
        headers: {
          Accept: "image/*",
        },
        credentials: "include",
      });

      if (!response.ok) {
        throw new Error(
          `Failed to fetch image from server: ${response.status}`
        );
      }

      const blob = await response.blob();

      return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onloadend = () => {
          const rawBase64 = reader.result as string;
          resolve(normalizeBase64Image(rawBase64));
        };
        reader.onerror = () => reject("Failed to convert image to base64");
        reader.readAsDataURL(blob);
      });
    } catch (error) {
      console.error("Error fetching image from backend:", error);
      throw error;
    }
  };

  const handleColorChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setColor(e.target.value);
    setSuccess(null);
    setError(null);
  };

  const handleLogoChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      setLogoFile(file);

      const reader = new FileReader();
      reader.onloadend = () => {
        const result = reader.result as string;
        setLogoPreview(normalizeBase64Image(result));
      };
      reader.readAsDataURL(file);

      setSuccess(null);
      setError(null);
    }
  };

  const handleSave = async () => {
    setLoading(true);
    setError(null);
    setSuccess(null);

    try {
      let uploadedLogoUrl = logoUrl;

      if (logoFile) {
        const response = await uploadLogo(logoFile);
        uploadedLogoUrl = response.logoUrl;
        setLogoUrl(uploadedLogoUrl);
      }

      await saveTheme({
        primaryColor: color,
        logoUrl: uploadedLogoUrl,
      });

      updateTheme(color, logoPreview ?? ""); // ✅ FIXED HERE
      setSuccess("Theme updated successfully.");
    } catch (err: any) {
      setError(err.message || "Error saving theme settings");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-2xl mx-auto p-6 bg-white rounded-2xl shadow-lg space-y-6 border border-gray-100 mt-6">
      <h2 className="text-3xl font-bold text-gray-800">Branding Settings</h2>

      {error && <p className="text-red-500 text-sm">{error}</p>}
      {success && <p className="text-green-600 text-sm">{success}</p>}

      <div className="space-y-2">
        <label className="block text-sm font-medium text-gray-700">
          Primary Color
        </label>
        <div className="flex items-center space-x-4">
          <input
            type="color"
            value={color}
            onChange={handleColorChange}
            className="w-12 h-12 border border-gray-300 rounded cursor-pointer"
          />
          <span className="text-sm text-gray-600">{color}</span>
        </div>
      </div>

      <div className="space-y-2">
        <label className="block text-sm font-medium text-gray-700">
          Upload Logo
        </label>
        <input
          type="file"
          accept="image/*"
          onChange={handleLogoChange}
          className="text-sm"
        />
        {logoPreview && (
          <div className="mt-4 border rounded-md p-2 w-32 h-32 flex items-center justify-center bg-gray-50 shadow-inner">
            <img
              src={logoPreview}
              alt="Logo Preview"
              className="max-h-full max-w-full object-contain"
            />
          </div>
        )}
      </div>

      <button
        onClick={handleSave}
        disabled={loading}
        className={`mt-6 bg-blue-600 hover:bg-blue-700 text-white font-semibold px-6 py-2 rounded-md transition ${
          loading ? "opacity-50 cursor-not-allowed" : ""
        }`}
      >
        {loading ? "Saving..." : "Save Theme"}
      </button>
    </div>
  );
};

export default BrandingSettings;
