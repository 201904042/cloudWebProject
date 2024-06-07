package com.example.BookReview;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReviewDTO {
    private long id;
    private String name;
    private float grade;
    private long book_id;
    private String content;

    public ReviewDTO(Review review){
        id = review.getId();
        name = review.getName();
        grade = review.getGrade();
        book_id = review.getBook_id();
        content = review.getContent();
    }
}
