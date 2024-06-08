import { keyboard } from "@testing-library/user-event/dist/keyboard";
import axios from "axios";

const baseURL = process.env.REACT_APP_API_BASE_URL;

const apiUrl = `${baseURL}/books`;

export const getBooks = async () => {
  try {
    const response = await axios.get(apiUrl);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const createBook = async (book) => {
  try {
    const response = await axios.post(apiUrl, book);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const getBookById = async (id) => {
  try {
    const response = await axios.get(`${apiUrl}/${id}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const updateBookById = async (id, book) => {
  try {
    const response = await axios.put(`${apiUrl}/${id}`, book);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const deleteBookById = async (id) => {
  try {
    const response = await axios.delete(`${apiUrl}/${id}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const searchBooks = async (keyword) => {
  try {
    const response = await axios.get(`${apiUrl}/search?keyword=${keyword}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};
