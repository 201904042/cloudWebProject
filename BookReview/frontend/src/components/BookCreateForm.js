import React, { useContext, useRef, useState } from "react";
import { createBook } from "../services/ApiService";
import { BookContext } from "../context/BookContext";
import { useNavigate } from "react-router-dom";
import "./BookCreateForm.css";

export default function BookCreateForm() {
  const { addBook } = useContext(BookContext);
  const navigate = useNavigate();
  const titleRef = useRef();
  const authorRef = useRef();
  const genreRef = useRef();
  const summaryRef = useRef();
  const [errorMessage, setErrorMessage] = useState("");

  async function add(target) {
    target.preventDefault();

    if (
      !titleRef.current.value ||
      !authorRef.current.value ||
      !genreRef.current.value ||
      !summaryRef.current.value
    ) {
      setErrorMessage("모든 필드를 입력하세요.");
      return;
    }

    try {
      const newBook = {
        title: titleRef.current.value,
        author: authorRef.current.value,
        genre: genreRef.current.value,
        summary: summaryRef.current.value,
      };

      console.log(newBook);

      const response = await createBook(newBook);
      console.log(response);
      addBook(response);
      navigate(`/`);
    } catch (error) {
      console.error("Error", error);
    }
  }
  function goToMain() {
    navigate(`/`);
  }

  return (
    <div className="create-form">
      <div className="header-container">
        <h1>책 등록</h1>
        {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
        <div className="main-btn">
          <button onClick={goToMain}>Main</button>
        </div>
      </div>
      <hr />
      <div>
        제목
        <input
          type="text"
          name="title"
          placeholder="Enter Title"
          ref={titleRef}
        />
        저자
        <input
          type="text"
          name="author"
          ref={authorRef}
          placeholder="Enter Author"
        />
        장르
        <input
          type="text"
          name="genre"
          ref={genreRef}
          placeholder="Enter Genre"
        />
        줄거리
        <textarea
          type="text"
          name="summary"
          ref={summaryRef}
          placeholder="Enter Summary"
        />
      </div>

      <button onClick={add} type="submit">
        생성
      </button>
    </div>
  );
}
