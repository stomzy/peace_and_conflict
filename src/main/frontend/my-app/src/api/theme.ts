import axios from "axios";

const API_URL = "/api/theme";

export const getTheme = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    if (axios.isAxiosError(error)) {
      throw new Error(error.response?.data || "Error fetching theme");
    } else {
      throw new Error("An unexpected error occurred");
    }
  }
};

export const saveTheme = async (theme: {
  primaryColor: string;
  logoUrl: string | null;
}) => {
  try {
    const response = await axios.post(API_URL, theme);
    return response.data;
  } catch (error) {
    if (axios.isAxiosError(error)) {
      throw new Error(error.response?.data || "Error saving theme");
    } else {
      throw new Error("An unexpected error occurred");
    }
  }
};

export const uploadLogo = async (file: File) => {
  try {
    const formData = new FormData();
    formData.append("logo", file);

    const response = await axios.post(`${API_URL}/logo`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    return response.data;
  } catch (error) {
    if (axios.isAxiosError(error)) {
      throw new Error(error.response?.data || "Error uploading logo");
    } else {
      throw new Error("An unexpected error occurred");
    }
  }
};
