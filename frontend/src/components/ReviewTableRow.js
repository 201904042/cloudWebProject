import React, { useContext, useState, useRef } from 'react';
import { deleteReviewById, updateReviewById } from "../services/ApiService";
import { ReviewContext } from '../context/ReveiwContext';
import { useNavigate } from 'react-router-dom';

export default function ReviewTableRow({ id, name, grade, content, book_id,fetchData  }) {
  const { removeReviewById } = useContext(ReviewContext);
  const { updateReview } = useContext(ReviewContext);

  const nameRef = useRef();
  const gradeRef = useRef();
  const contentRef = useRef();


  const [isEditing, setIsEditing] = useState(false);

  async function deleteReview() {
    try {
      await deleteReviewById(id);
      removeReviewById(id);
      fetchData();
    } catch (error) {
      console.error('Error deleting review:', error);
    }
  }

  async function editReview() {
    setIsEditing(true);
    fetchData();
  }

  async function saveReview() {
    const updatedReview = {
      id,
      name: nameRef.current.value,
      grade: gradeRef.current.value,
      content: contentRef.current.value,
      book_id
    };

    try {
      await updateReviewById(id, updatedReview);
      updateReview(updatedReview);
      setIsEditing(false);
      fetchData();
    } catch (error) {
      console.error('Error updating review:', error);
    }
  }

  async function cancelEdit() {
    setIsEditing(false);
  }

  return (
    <tr>
      <th>{id}</th>
      <td>{isEditing ? <input type="text" defaultValue={name} ref={nameRef} /> : name}</td>
      <td>{isEditing ? <select defaultValue={grade} ref={gradeRef}>
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
        </select> : grade}</td>
      <td>{isEditing ? <textarea col ="70" rows="10" defaultValue={content} ref={contentRef} /> : content}</td>

      <td>
        {isEditing ? (
          <>
            <button onClick={saveReview}>저장</button>
            <button onClick={cancelEdit}>취소</button>
          </>
        ) : (
            <>
            <button onClick={deleteReview}>삭제</button>
            <button onClick={editReview}>수정</button>
            </>
          
        )}
      </td>
    </tr>
  );
}
