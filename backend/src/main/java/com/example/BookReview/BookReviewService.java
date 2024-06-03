package com.example.BookReview;

import org.springframework.stereotype.Service;

import java.util.List;


public interface BookReviewService {
    List<BookDTO> bookList();

    BookDTO findBookById(Long id);

    BookDTO addBook(BookDTO bookDTO);

    void deleteBook(Long id);

    BookDTO updateBook(Long id, BookDTO bookDTO);

    List<BookDTO> searchBooks(String keyword);

    boolean checkDuplicate(String title);

    List<ReviewDTO> ReviewList();

    ReviewDTO findReviewById(Long id);

    ReviewDTO addReview(ReviewDTO reviewDTO);

    void deleteReview(Long id);

    List<ReviewDTO> searchReviews(String keyword);
}
