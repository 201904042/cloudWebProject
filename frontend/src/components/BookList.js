import React, { useContext, useEffect, useRef, useState } from 'react';
import { getBooks, searchBooks } from "../services/ApiService"
import BookTableRow from './BookTableRow.js';
import { BookContext } from '../context/BookContext';
import {NavLink} from "react-router-dom";
import './bookList.css';

export default function BookList(){
  const { books, updateBooks} = useContext(BookContext);

  const searchRef= useRef();
  const [errorMessage, setErrorMessage] = useState("");

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

  async function handleReset() {
    try{
      const books = await getBooks();
      updateBooks(books);
      setErrorMessage("");

      // 아래 2줄은 권장되지 않는 코드인 듯하나 간략한 다른 방법을 모르겠습니다...
      if (searchRef.current.value)
        searchRef.current.value = "";

    }catch(error){
      console.error('error',error);
    }
  }

  async function handleSearch(target) {
    target.preventDefault();
    if (!searchRef.current.value) {
      setErrorMessage("검색어를 입력해주세요.");
      return;
     }
     setErrorMessage("");

     try {
      const response = await searchBooks(searchRef.current.value);
      updateBooks(response);
      console.log(response);
    } catch(error) {
      console.error('Error',error);
    }
  }


  return (
    
    <div>
      <h1>Book List</h1>

      <div className="search">
        <input
        type="text"
        ref={searchRef}></input>
        <button
        type="button"
        onClick={handleReset}>전체 보기</button>
        <button
        type="button"
        onClick={handleSearch}>검색</button>
        <span className='error'>{errorMessage}</span>
      </div>

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

  
