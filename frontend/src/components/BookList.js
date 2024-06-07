import React, { useContext, useEffect } from 'react';
import { getBooks } from "../services/ApiService"
import BookTableRow from './BookTableRow.js';
import { BookContext } from '../context/BookContext';
import {NavLink} from "react-router-dom";
import './bookList.css';

export default function BookList(){
  const { books, updateBooks} = useContext(BookContext);

  useEffect(()=>{
    async function fetchData(){
      try{
        const books = await getBooks();
        updateBooks(books);

        
      }catch(error){
        console.error('error',error);
      }
    }
    fetchData();
  },[]);


  return (
    
    <div>
      <h1>Book List</h1>

      <table className="book-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Genre</th>
            <th>Summary</th>
            <th>Review Count</th>
          </tr>
        </thead>
        <tbody>
          {books.map(book=><BookTableRow key={book.id}{...book}/>)}
        </tbody>
      </table>
      <div>
        <NavLink to="/newBook">Add</NavLink>
      </div>
      </div>
  );
}

  
