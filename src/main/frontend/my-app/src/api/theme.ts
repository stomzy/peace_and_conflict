import axios from "axios";

const API_URL = "http://localhost:8080/api";

const getAuthToken = () => {
  return localStorage.getItem("token");
};

const apiClient = axios.create({
  baseURL: API_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

apiClient.interceptors.request.use((config) => {
  const token = getAuthToken();

  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }

  return config;
});

export const getTheme = async () => {
  try {
    const response = await apiClient.get("/theme");
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
    const response = await apiClient.post("/theme", theme);
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

    const response = await apiClient.post("/theme/logo", formData, {
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
