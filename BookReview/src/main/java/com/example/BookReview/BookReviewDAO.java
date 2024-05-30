package com.example.BookReview;

import java.util.List;

public interface BookReviewDAO {
    List<BookDTO> GetBookData();

    BookDTO GetBookById(long bookId);

    BookDTO InsertBook(BookDTO book);

    BookDTO DeleteBook(long deleteBookId);

    BookDTO UpdateBook(BookDTO _updateBook);

    List<ReviewDTO> GetReviewByBookId(long bookId);

    ReviewDTO InsertReview(ReviewDTO newReview);

    ReviewDTO DeleteReview(long deleteReviewId);

    ReviewDTO UpdateReview(ReviewDTO _updateReview);
}
