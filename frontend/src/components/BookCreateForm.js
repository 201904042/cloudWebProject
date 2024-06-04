import React, {useContext, useRef} from "react";
import { createBook } from "../services/ApiService";
import { BookContext } from "../context/BookContext";
import {useNavigate} from'react-router-dom';

export default function BookCreateForm(){
    const {addBook} = useContext(BookContext);
    const navigate = useNavigate();
    const titleRef= useRef();
    const authorRef= useRef();
    const genreRef= useRef();
    const summaryRef= useRef();

    async function add(target) {
        target.preventDefault();
    
        try {
    
          const newBook = {
            title: titleRef.current.value,
            author: authorRef.current.value,
            genre: genreRef.current.value,
            summary:summaryRef.current.value
          };
    
          console.log(newBook)
    
          const response = await createBook(newBook);
          console.log(response)
          addBook(response);
          navigate(`/`);
    
        } catch (error) {
          console.error('Error', error);
        }
      }
    


    return(<div><h1>책 등록</h1><hr/>
    <div>
        제목<input 
        type = "text" 
        name="title" 
        placeholder="Enter Title"
        ref ={titleRef}
        /><br/>
        저자
        <input 
        type = "text" 
        name="author" 
        ref ={authorRef}
        placeholder="Enter Author"
        /><br/>
        장르
        <input 
        type = "text" 
        name="genre"
        ref ={genreRef}
        placeholder="Enter Genre"
        /><br/>
        줄거리
        <textarea 
        type = "text" 
        name="summary"
        ref ={summaryRef}
        placeholder="Enter Summary"
        /><br/>
      </div>

      <button onClick={add} type="submit">생성</button>
  </div>
    
            
    );
}

