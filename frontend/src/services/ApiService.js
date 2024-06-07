import axios from "axios";

const baseURL = process.env.REACT_APP_API_BASE_URL;

const bookApiUrl = `${baseURL}/books`;
const reviewApiUrl = `${baseURL}/reviews`;

export const getBooks = async () => {
    try {
      const response = await axios.get(bookApiUrl);
      return response.data;
    } catch (error) {
      throw error;
    }
  }
  
  export const createBook = async (book) => {
    try {
      const response = await axios.post(bookApiUrl, book);
      return response.data;
    } catch (error) {
      throw error;
    }
  }
  
  export const getBookById = async (id) => {
    try {
      const response = await axios.get(`${bookApiUrl}/${id}`);
      return response.data;
    } catch (error) {
      throw error;
    }
  }
  
  export const updateBookById = async (id, book) => {
    try {
      const response = await axios.put(`${bookApiUrl}/${id}`, book);
      return response.data;
    } catch (error) {
      throw error;
    }
  }
  
  export const deleteBookById = async (id) => {
    try {
      const response = await axios.delete(`${bookApiUrl}/${id}`);
      return response.data;
    } catch (error) {
      throw error;
    }
  }

  export const searchBooks = async (keyword) => {
    try {
      const response = await axios.get(`${bookApiUrl}/search`, {params: {keyword: encodeURIComponent(keyword)}});
      return response.data;
    } catch (error) {
      console.error('Searching books failed:', error);
      throw error;
    }
  }
  

  export const getReviews = async () => {
    try {
      const response = await axios.get(reviewApiUrl);
      return response.data;
    } catch (error) {
      throw error;
    }
  }
  
  export const createReview= async (review) => {
    try {
      const response = await axios.post(reviewApiUrl, review);
      return response.data;
    } catch (error) {
      throw error;
    }
  }
  
  export const getReviewById = async (id) => {
    try {
      const response = await axios.get(`${reviewApiUrl}/${id}`);
      return response.data;
    } catch (error) {
      throw error;
    }
  }
  
  export const updateReviewById = async (id, review) => {
    try {
      const response = await axios.put(`${reviewApiUrl}/${id}`, review);
      return response.data;
    } catch (error) {
      throw error;
    }
  }
  
  export const deleteReviewById = async (id) => {
    try {
      console.log(id);
      const response = await axios.delete(`${reviewApiUrl}/${id}`);
      return response.data;
    } catch (error) {
      throw error;
    }
  }