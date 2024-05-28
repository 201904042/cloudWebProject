package com.example.BookReview;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class BookDTO {
    long bookId;
    String bookTitle;
    String bookAuthor;
    String bookGenre;
    String bookSummary;
    int bookReviewCount;
}
