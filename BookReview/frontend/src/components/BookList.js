import React, { useContext, useEffect,useState } from 'react';
import { getBooks,searchBooks } from "../services/ApiService"
import BookTableRow from './BookTableRow.js';
import { BookContext } from '../context/BookContext';
import {NavLink} from "react-router-dom";
import '../css/bookList.css'
import { useNavigate } from 'react-router-dom';

export default function BookList(){
  const { books, updateBooks} = useContext(BookContext);
  const [keyword, setKeyword] = useState("");
  const [searchResults, setSearchResults] = useState(null);

  const navigate = useNavigate();
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

  function goToNewBook(){
    navigate("/newBook")
  }

  const handleSearch = async () => {
    try {
      const results = await searchBooks(keyword);
      setSearchResults(results);
    } catch (error) {
      console.error("error", error);
    }
  };

  return (
    <div className="book-list">
      <div className="header-container">
        <h1>Book List</h1>
        <div className="search-container">
          <input
            type="text"
            placeholder="Search by title or author"
            value={keyword}
            onChange={(e) => setKeyword(e.target.value)}
          />
          <button onClick={handleSearch}>Search</button>
        </div>
      </div>
      {searchResults !== null && searchResults.length === 0 && (
        <div className="no-results">없습니다.</div>
      )}
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
          {(searchResults !== null ? searchResults : books).map((book) => (
            <BookTableRow key={book.id} {...book} />
          ))}
        </tbody>
      </table>
      <div>
        <NavLink className="btn btn-primary" to="/newBook">
          <input type="button" value="ADD" />
        </NavLink>
      </div>
    </div>
  );
}

  
