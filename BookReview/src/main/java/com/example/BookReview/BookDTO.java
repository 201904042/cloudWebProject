package com.example.BookReview;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class BookDTO {
    long id;
    String title;
    String author;
    String genre;
    String summary;
    int reviewCount;
}
