/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      colors: {
        primary: "#1ABC9C", // default theme color
        dark: "#2C3E50",
      },
    },
  },
  plugins: [],
};
