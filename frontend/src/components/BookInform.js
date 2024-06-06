import React, { useContext, useEffect, useState, useRef } from 'react';
import { getBookById, getReviews, createReview } from "../services/ApiService";
import { BookContext } from '../context/BookContext';
import { useParams, useNavigate } from 'react-router-dom';
import ReviewTableRow from './ReviewTableRow';
import { ReviewContext } from '../context/ReveiwContext';


export default function BookInform() {
  const { id } = useParams();
  const { book, updateBook } = useContext(BookContext);
  const { reviews, updateReviews } = useContext(ReviewContext);
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();
  const [showReviewForm, setShowReviewForm] = useState(false);

  async function fetchData() {
    try {
      const book = await getBookById(id);
      updateBook(book);
      const reviews = await getReviews();
      updateReviews(reviews);
    } catch (error) {
      console.error('error', error);
    }
  }

  useEffect(() => {
    fetchData();
  }, []);

  const handleCreateReview = () => {
    setShowReviewForm(prev => !prev);
  };

  const { addReview } = useContext(ReviewContext);

  const nameRef = useRef();
  const gradeRef = useRef();
  const contentRef = useRef();

  async function add(target) {
    target.preventDefault();

    if (
      !nameRef.current.value ||
      !gradeRef.current.value ||
      !contentRef.current.value
    ) {
      setErrorMessage("모든 필드를 입력하세요.");
      return;
    }

    try {
      const newReview = {
        name: nameRef.current.value,
        grade: gradeRef.current.value,
        content: contentRef.current.value,
        book_id: id
      };

      console.log(newReview);

      const response = await createReview(newReview);
      console.log(response);
      addReview(response);

      // Fetch updated book information
      fetchData();

      setShowReviewForm(false);

    } catch (error) {
      console.error('Error', error);
    }
  }

  function goToMain() {
    navigate(`/book`);
  }

  function goToUpdate() {
    navigate(`/book/update/${id}`);
  }

  return (
    <div className="book-inform-container">
      <h2>책의 정보</h2>
      <button onClick={goToMain}>취소</button>
      <button onClick={goToUpdate}>수정</button>
      <hr/>
      <div className="book-details">
        <p>Title : {book.title}</p>
        <p>Author: {book.author}</p>
        <p>Genre: {book.genre}</p>
        <p>Summary: {book.summary}</p>
        <p>Review Count: {book.review_count}</p>
      </div>
      <hr/>
      <h2>리뷰 목록</h2>
      <button onClick={handleCreateReview}>새 리뷰 작성하기</button>
      {showReviewForm && (
        <div className="review-form">
          이름
          <input 
            type="text" 
            name="name" 
            ref={nameRef} 
            placeholder="Enter name"
          /><br />
          점수
          <select name="grade" ref={gradeRef}>
            <option value="0">0</option>
            <option value="0.5">0.5</option>
            <option value="1">1</option>
            <option value="1.5">1.5</option>
            <option value="2">2</option>
            <option value="2.5">2.5</option>
            <option value="3">3</option>
            <option value="3.5">3.5</option>
            <option value="4">4</option>
            <option value="4.5">4.5</option>
            <option value="5">5</option>
          </select><br />
          코멘트
          <textarea 
            type="text" 
            name="content" 
            ref={contentRef} 
            placeholder="Enter Comment"
          /><br />
          <button onClick={add} type="submit">생성</button>
          <button onClick={handleCreateReview} type="submit">취소</button>
        </div>
      )}
      {errorMessage && <p className="error-message">{errorMessage}</p>}
      <hr/>
      <table className="reviews-table">
        <thead>
          <tr>
            <th>id</th>
            <th>이름(닉네임)</th>
            <th>점수</th>
            <th>리뷰</th>
          </tr>
        </thead>
        <tbody>
          {reviews.map(review => <ReviewTableRow key={review.id} {...review} fetchData={fetchData} />)}
        </tbody>
      </table>
    </div>
  );
}
