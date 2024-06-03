import React, { useState } from 'react';
import axios from 'axios';

const BookCreate = () => {
  const [title, setTitle] = useState('');
  const [author, setAuthor] = useState('');
  const [genre, setGenre] = useState('');
  const [summary, setSummary] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);

    try {
      // API를 호출하여 새 책 생성
      const response = await axios.post('http://localhost:8080/books', {
        title,
        author,
        genre,
        summary,
      });

      console.log('New book created:', response.data);

      // 책 생성 후, 필요한 작업 수행 (예: 책 목록 페이지로 이동)
    } catch (error) {
      console.error('Error creating book:', error);
    }

    setLoading(false);
  };

  

  return (
    <div>
      <h1>Create New Book</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Title:
          <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} />
        </label>
        <label>
          Author:
          <input type="text" value={author} onChange={(e) => setAuthor(e.target.value)} />
        </label>
        <label>
          Genre:
          <input type="text" value={genre} onChange={(e) => setGenre(e.target.value)} />
        </label>
        <label>
          Summary:
          <textarea value={summary} onChange={(e) => setSummary(e.target.value)} />
        </label>
        <button type="submit" disabled={loading}>Create</button>
      </form>
    </div>
  );
};

export default BookCreate;
