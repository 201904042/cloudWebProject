import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { BrowserRouter as Router, Routes, Route, NavLink, useParams, 
  useNavigate, useSearchParams } from 'react-router-dom';

const BookList = () => {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const response = await axios.get('http://localhost:8080/books');
        setBooks(response.data);
      } catch (error) {
        console.error('Error fetching books:', error);
      }
    };

    fetchBooks();
  }, []);


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
          {(
            books.map(book => (
              <tr key={book.id}>
                <td style={{ textAlign: 'center' }}>{book.id}</td>
                <td style={{ textAlign: 'left', paddingLeft: '10px' }}>{book.title}</td>
                <td style={{ textAlign: 'left', paddingLeft: '10px' }}>{book.author}</td>
                <td style={{ textAlign: 'left', paddingLeft: '10px' }}>{book.genre}</td>
                <td style={{ textAlign: 'left', paddingLeft: '10px' }}>{book.summary}</td>
                <td style={{ textAlign: 'center' }}>{book.review_count}</td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
};

  
export default BookList;
