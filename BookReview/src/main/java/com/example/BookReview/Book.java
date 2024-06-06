package com.example.BookReview;


import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@Builder @ToString
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String author;
    private String genre;
    private String summary;
    private long review_count;
}
