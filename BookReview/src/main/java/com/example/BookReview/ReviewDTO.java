package com.example.BookReview;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReviewDTO {
    long reviewId;
    String reviewerName;
    String reviewBookGrade;
    long reviewBookId;
    String reviewContent;
}
