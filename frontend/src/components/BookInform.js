import React, { useContext, useEffect,useState,useRef } from 'react';
import { getBookById, getReviews,createReview } from "../services/ApiService"
import { BookContext } from '../context/BookContext';
import { useParams, useHistory, Navigate } from 'react-router-dom';
import ReviewTableRow from './ReviewTableRow'
import { ReviewContext } from '../context/ReveiwContext';
import {useNavigate} from'react-router-dom';

export default function BookInform(){

  const { id } = useParams();
  const {book,updateBook} = useContext(BookContext);
  const { reviews, updateReviews} = useContext(ReviewContext);
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  useEffect(()=>{
    async function fetchData(){
      try{
        const book = await getBookById(id);
        updateBook(book);
        const reviews = await getReviews();
        updateReviews(reviews);
        
      }catch(error){
        console.error('error',error);
      }
    }
    fetchData();
  },[]);
  const handleCreateReview = () => {
    setShowReviewForm(true);
  };

  const {addReview} = useContext(ReviewContext);

  const nameRef= useRef();
  const gradeRef= useRef();
  const contentRef= useRef();

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
  
        console.log(newReview)
  
        const response = await createReview(newReview);
        console.log(response)
        addReview(response);
        navigate(`/book/${id}`);
  
      } catch (error) {
        console.error('Error', error);
      }
    }
 
  const [showReviewForm, setShowReviewForm] = useState(false);
  return (
    <div>
        <h2>책의 정보</h2>
        <hr/>
        <div>
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
        <div>
          이름
          <input 
            type="text" 
            name="name" 
            ref={nameRef} 
            placeholder="Enter name"
          /><br />
          점수
          <input 
            type="text" 
            name="grade" 
            ref={gradeRef} 
            placeholder="Enter grade"
          /><br />
          코멘트
          <textarea 
            type="text" 
            name="content" 
            ref={contentRef} 
            placeholder="Enter Comment"
          /><br />
          <button onClick={add} type="submit">생성</button>
        </div>
      )}
      {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}

        <hr/>
        <table className="reviews-table"></table>
        <thead>
            <tr>
                <th>id</th>
                <th>이름</th>
                <th>점수</th>
                <th>리뷰</th>
            </tr>
        </thead>
        <tbody>
            {reviews.map(review=><ReviewTableRow key={review.id}{...review}/>)}
        </tbody>
    </div>
  );
}