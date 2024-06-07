import React, { useContext } from 'react';
import { deleteBookById } from "../services/ApiService"
import { BookContext } from '../context/BookContext';
import { NavLink } from "react-router-dom";
export default function BookTableRow({id,title,author,genre,summary,review_count}){


  const {removeBookById} = useContext(BookContext);
  async function deleteBook() {
    try {
      await deleteBookById(id);
      removeBookById(id);

    } catch (error) {
      console.error('Error fetching products:', error);
    }
  }

  return (
    <tr>
        <th>{id}</th>
        <td><NavLink to={`/Book/${id}`}>{title}</NavLink></td>
        <td>{author}</td>
        <td>{genre}</td>
        <td>{summary}</td>
        <td>{review_count}</td>
        <td><button onClick={deleteBook} >Delete</button></td>
        
    </tr>
  );
}