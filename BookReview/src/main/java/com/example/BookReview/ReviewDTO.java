package com.example.BookReview;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReviewDTO {
    long id;
    String name;
    float grade;
    int bookId;
    String content;
}