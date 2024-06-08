import React, { useContext, useRef, useState } from "react";
import { createBook } from "../services/ApiService";
import { BookContext } from "../context/BookContext";
import { useNavigate } from 'react-router-dom';
import '../css/Form.css'

export default function BookCreateForm() {
  const { addBook } = useContext(BookContext);
  const navigate = useNavigate();
  const titleRef = useRef();
  const authorRef = useRef();
  const [genre, setGenre] = useState("소설");
  const [customGenre, setCustomGenre] = useState("");
  const summaryRef = useRef();
  const [errorMessage, setErrorMessage] = useState("");

  async function add(target) {
    target.preventDefault();
    if (
      !titleRef.current.value ||
      !authorRef.current.value ||
      (!customGenre && genre === "기타") ||
      !summaryRef.current.value
    ) {
      setErrorMessage("모든 필드를 입력하세요.");
      return;
    }

    try {
      const newBook = {
        title: titleRef.current.value,
        author: authorRef.current.value,
        genre: genre === "기타" ? customGenre : genre,
        summary: summaryRef.current.value
      };

      console.log(newBook);

      const response = await createBook(newBook);
      console.log(response);
      addBook(response);
      navigate(`/book`);

    } catch (error) {
      console.error('Error', error);
    }
  }

  function goToMain() {
    navigate(`/book`);
  }

  function handleGenreChange(event) {
    const selectedGenre = event.target.value;
    if (selectedGenre === "기타") {
      setGenre("기타");
      setCustomGenre(""); // Reset custom genre input
    } else {
      setGenre(selectedGenre);
    }
  }

  return (
    <div className="create-form">
      <div className="header-container">
        <h1>책 등록</h1>
        {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
        <div className="main-btn">
          <button onClick={goToMain}>취소</button>
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
        <select
          name="genre"
          value={genre}
          onChange={handleGenreChange}
        >
          <option value="소설">소설</option>
          <option value="판타지">판타지</option>
          <option value="SF">SF</option>
          <option value="미스터리">미스터리</option>
          <option value="스릴러">스릴러</option>
          <option value="역사">역사</option>
          <option value="자기계발">자기계발</option>
          <option value="철학">철학</option>
          <option value="종교">종교</option>
          <option value="예술">예술</option>
          <option value="만화">만화</option>
          <option value="비즈니스">비즈니스</option>
          <option value="기타">기타</option>
        </select>
        {genre === "기타" && (
          <input
            type="text"
            name="customGenre"
            value={customGenre}
            onChange={(e) => setCustomGenre(e.target.value)}
            placeholder="기타 장르 입력"
          />
        )}
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
