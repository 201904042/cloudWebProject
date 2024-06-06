import React, { createContext, useState } from 'react';

export const BookContext = createContext();

export const BookListProvider = ({ children }) => {
  const [books, setBooks] = useState([]);
  const [book, setBook] = useState({});

  const updateBooks = (books) => {
    setBooks(books)
  }

  const addBook = (book) => {
    setBooks([... books, book]);
  }

  const updateBook = (book) => {
    setBook(book);
  }

  const removeBookById = (id) => {
    const newBooks = books.filter((book) => book.id !== id);
    setBooks(newBooks);
  }

  return  <BookContext.Provider value={{ books, book, updateBooks, updateBook, removeBookById, addBook }}>
      {children}
    </BookContext.Provider>
  
}