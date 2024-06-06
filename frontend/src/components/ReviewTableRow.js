import React, { useContext } from 'react';
import { deleteReviewById } from "../services/ApiService"
import { ReviewContext } from '../context/ReveiwContext';

export default function ReviewTableRow({id,name,grade,content}){


  const {removeReviewById} = useContext(ReviewContext);
  async function deleteReview() {
    try {
      await deleteReviewById(id);
      removeReviewById(id);

    } catch (error) {
      console.error('Error fetching products:', error);
    }
  }

  return (
    <tr>
        <th>{id}</th>
        <td>{name}</td>
        <td>{grade}</td>
        <td>{content}</td>
        <button onClick={deleteReview} >Delete</button>
    </tr>
  );
}