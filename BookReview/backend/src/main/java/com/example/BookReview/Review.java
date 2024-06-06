package com.example.BookReview;


import jakarta.persistence.*;
import lombok.*;
@Getter @Setter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name="review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private float grade;
    private long book_id;
    private String content;
}
