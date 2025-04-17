import axios from "axios";

const API_URL = "http://localhost:8080/api/auth";

export const login = async (username: string, password: string) => {
  const response = await axios.post(`${API_URL}/login`, { username, password });
  return response.data;
};

export const signup = async (data: {
  fullName: string;
  username: string;
  email: string;
  phoneNumber: string;
  password: string;
}) => {
  const response = await axios.post(`${API_URL}/signup`, data);
  return response.data;
};
