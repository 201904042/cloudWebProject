package com.example.BookReview;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString
public class BookDTO {
    private long id;
    private String title;
    private String author;
    private String genre;
    private String summary;
    private long review_count;

    public BookDTO(Book book){
         id = book.getId();
         title = book.getTitle();
        author = book.getAuthor();
        genre = book.getGenre();
        summary = book.getSummary();
        review_count = book.getReview_count();
    }
}
