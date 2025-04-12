// src/components/Sidebar.tsx
import React from "react";
import { NavLink } from "react-router-dom";
import { useTheme } from "../context/ThemeContext";
import { HomeIcon, ListChecksIcon, SettingsIcon } from "lucide-react";

const Sidebar: React.FC = () => {
  const { logoUrl } = useTheme();

  const linkClasses =
    "flex items-center gap-2 px-4 py-2 rounded-md transition-all hover:bg-gray-100";

  return (
    <aside className="w-64 bg-white border-r border-gray-200 shadow-md flex flex-col justify-between">
      {/* Top: Logo + Main Navigation */}
      <div>
        <div className="p-6 flex items-center justify-center border-b border-gray-100">
          {logoUrl ? (
            <img src={logoUrl} alt="Logo" className="h-12 w-auto" />
          ) : (
            <span className="text-xl font-semibold">PCR</span>
          )}
        </div>

        <nav className="p-4 space-y-2 text-gray-800 text-sm font-medium">
          <NavLink
            to="/dashboard"
            className={({ isActive }) =>
              `${linkClasses} ${
                isActive ? "bg-gray-100 text-[color:var(--tw-prose-links)]" : ""
              }`
            }
          >
            <HomeIcon className="h-5 w-5" />
            Dashboard
          </NavLink>

          <NavLink
            to="/cases"
            className={({ isActive }) =>
              `${linkClasses} ${
                isActive ? "bg-gray-100 text-[color:var(--tw-prose-links)]" : ""
              }`
            }
          >
            <ListChecksIcon className="h-5 w-5" />
            Case List
          </NavLink>
        </nav>
      </div>

      {/* Bottom: Settings Menu */}
      <div className="p-4 mt-8 border-t border-gray-100">
        <NavLink
          to="/brand-setting"
          className={({ isActive }) =>
            `${linkClasses} ${
              isActive ? "bg-gray-100 text-[color:var(--tw-prose-links)]" : ""
            }`
          }
        >
          <SettingsIcon className="h-5 w-5" />
          Settings
        </NavLink>
      </div>
    </aside>
  );
};

export default Sidebar;
