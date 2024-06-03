package com.example.BookReview;


import jakarta.persistence.*;
import lombok.*;
@Getter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name="review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private float grade;
    private int book_id;
    private String content;
}
