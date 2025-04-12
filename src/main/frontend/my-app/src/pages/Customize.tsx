import React, { useState } from "react";
import { useTheme } from "../context/ThemeContext";
import DashboardLayout from "../components/DashboardLayout";
import BrandingSettings from "../components/BrandingSettings";

const Dashboard: React.FC = () => {
  const { primaryColor, logoUrl, updateTheme } = useTheme();
  const [color, setColor] = useState(primaryColor);
  const [previewLogo, setPreviewLogo] = useState<string | null>(logoUrl);

  const handleColorChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setColor(e.target.value);
  };

  const handleLogoChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        const base64 = reader.result as string;
        setPreviewLogo(base64);
      };
      reader.readAsDataURL(file);
    }
  };

  const handleSave = () => {
    updateTheme(color, previewLogo);
    alert("Theme updated!");
  };

  return (
    <DashboardLayout>
      <BrandingSettings
        color={color}
        logoPreview={previewLogo}
        onColorChange={handleColorChange}
        onLogoChange={handleLogoChange}
        onSave={handleSave}
      />
    </DashboardLayout>
  );
};

export default Dashboard;
