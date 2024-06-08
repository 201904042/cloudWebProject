import React, { createContext, useState } from 'react';

export const ReviewContext = createContext();

export const ReviewListProvider = ({ children }) => {
  const [reviews, setReviews] = useState([]);
  const [review, setReview] = useState({});

  const updateReviews = (reviews) => {
    setReviews(reviews)
  }

  const addReview = (review) => {
    setReviews([... reviews, review]);
  }

  const updateReview = (review) => {
    setReview(review);
  }

  const removeReviewById = (id) => {
    const newReviews = reviews.filter((review) => review.id !== id);
    setReviews(newReviews);
  }

  return  <ReviewContext.Provider value={{reviews, review, updateReviews, updateReview, removeReviewById, addReview }}>
      {children}
    </ReviewContext.Provider>
  
}