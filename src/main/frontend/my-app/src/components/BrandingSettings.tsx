import React from "react";

interface Props {
  color: string;
  logoPreview: string | null;
  onColorChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  onLogoChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  onSave: () => void;
}

const BrandingSettings: React.FC<Props> = ({
  color,
  logoPreview,
  onColorChange,
  onLogoChange,
  onSave,
}) => {
  return (
    <div className="max-w-2xl mx-auto p-6 bg-white rounded-2xl shadow-lg space-y-8 border border-gray-100">
      <h2 className="text-3xl font-bold text-gray-800">Branding Settings</h2>

      <div className="space-y-4">
        <label className="block text-sm font-medium text-gray-700">
          Primary Color
        </label>
        <div className="flex items-center space-x-4">
          <input
            type="color"
            value={color}
            onChange={onColorChange}
            className="w-12 h-12 border border-gray-300 rounded cursor-pointer"
          />
          <span className="text-sm text-gray-600">{color}</span>
        </div>
      </div>

      <div className="space-y-4">
        <label className="block text-sm font-medium text-gray-700">
          Upload Logo
        </label>
        <input
          type="file"
          accept="image/*"
          onChange={onLogoChange}
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
        onClick={onSave}
        className="mt-6 bg-blue-600 hover:bg-blue-700 text-white font-semibold px-6 py-2 rounded-md transition"
      >
        Save Theme
      </button>
    </div>
  );
};

export default BrandingSettings;
