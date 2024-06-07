import React, { useContext, useState, useRef } from 'react';
import { deleteReviewById, updateReviewById } from "../services/ApiService"
import { ReviewContext } from '../context/ReveiwContext';
import './reviewList.css';

export default function ReviewList({id, review}){

  const nameRef = useRef();
  const gradeRef = useRef();
  const contentRef = useRef();
  const [errorMessage, setErrorMessage] = useState("");
  const [getReview, setReview] = useState(review);

  const {removeReviewById} = useContext(ReviewContext);
  const {updateReview} = useContext(ReviewContext);

  async function handleDeleteReview() {
    try {
      await deleteReviewById(id);
      removeReviewById(id);
    } catch (error) {
      console.error('Error fetching products:', error);
    }
  }

  const [showReviewEditForm, setShowReviewEditForm] = useState(false);
  async function handleEditReview() {
    console.log("edit show");
    setShowReviewEditForm(true);
  }
  async function handleCancelEditReview() {
    console.log("edit cancel");
    setShowReviewEditForm(false);
  }
  async function handleConfirmEditReview(target) {
    target.preventDefault();
    if (
      !nameRef.current.value ||
      !gradeRef.current.value ||
      !contentRef.current.value
    ) {
      setErrorMessage("모든 필드를 작성해주세요.");
      return;
    }
    
    try {
      const update = {
        name: nameRef.current.value,
        grade: gradeRef.current.value,
        content: contentRef.current.value
      };
      console.log(update)
      setErrorMessage("");

      const response = await updateReviewById(id, update);
      console.log(response);
      updateReview(response);
      setReview(response);
      setShowReviewEditForm(false);

    } catch (error) {
      console.error('Error', error);
    }
  }

  return ( <div className='review'>
      { showReviewEditForm ?
      
      <div>
        <input
        className='name'
        type='text'
        defaultValue={getReview.name}
        ref={nameRef}
        ></input>
        <select defaultValue={getReview.grade} ref={gradeRef}>
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
        </select>
        <span className='error'>{errorMessage}</span>
        <span className='button'>
          <button onClick={handleCancelEditReview} className='button'>취소</button>
          <button onClick={handleConfirmEditReview} className='button'>수정 완료</button>
        </span>
        <br/>
        <textarea
        className='content'
        type="text"
        defaultValue={getReview.content}
        ref={contentRef}
        placeholder="Edit Comment"
        ></textarea>
      </div>
      
      :  <div>
      <span className='name'>{getReview.name}</span>
      <span className='grade'>{Array.from({length: getReview.grade}).map(()=>"⭐")}</span>
      <span className='button'>
        <button onClick={handleDeleteReview} className='button'>삭제</button>
        <button onClick={handleEditReview} className='button'>수정</button>
      </span>
        <br/>
      <div className='content'>{getReview.content}</div>
  </div>
    }
     </div>
  );
}