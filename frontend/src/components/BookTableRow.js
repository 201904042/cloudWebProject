import React, { useContext } from 'react';
import { deleteBookById } from "../services/ApiService"
import { BookContext } from '../context/BookContext';
import { NavLink } from "react-router-dom";
export default function BookTableRow({id,title,author,genre,summary}){


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
        <td>{title}</td>
        <td>{author}</td>
        <td>{genre}</td>
        <td>{summary}</td>
        <td>
        <div className="btn-group">
          <NavLink cto={`/${id}`}>View</NavLink>
          <NavLink to={`/${id}/edit`}>Edit</NavLink>
          <button onClick={deleteBook} >Delete</button>
        </div>
      </td>
        
    </tr>
  );
}