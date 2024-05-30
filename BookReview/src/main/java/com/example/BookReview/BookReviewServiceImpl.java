package com.example.BookReview;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookReviewServiceImpl implements BookReviewService{
    @Autowired
    private BookReviewDAO bookReviewDAO;
    @Override
    public List<BookDTO> GetBookData(){
        return bookReviewDAO.GetBookData();
    }
    @Override
    public BookDTO GetBookById(long bookId){
        return bookReviewDAO.GetBookById(bookId);
    }
    @Override
    public BookDTO InsertBook(BookDTO book){
        return bookReviewDAO.InsertBook(book);
    }
    @Override
    public BookDTO DeleteBook(long deleteBookId){
        return bookReviewDAO.DeleteBook(deleteBookId);
    }
    @Override
    public BookDTO UpdateBook(BookDTO _updateBook){
        return bookReviewDAO.UpdateBook(_updateBook);
    }

    @Override
    public List<ReviewDTO> GetReview(){
        return bookReviewDAO.GetReview();
    }
    @Override
    public List<ReviewDTO> GetReviewByBookId(long bookId){
        return bookReviewDAO.GetReviewByBookId(bookId);
    }
    @Override
    public ReviewDTO InsertReview(ReviewDTO newReview){
        return bookReviewDAO.InsertReview(newReview);
    }
    @Override
    public ReviewDTO DeleteReview(long deleteReviewId){
        return bookReviewDAO.DeleteReview(deleteReviewId);
    }
    @Override
    public ReviewDTO UpdateReview(ReviewDTO _updateReview){
        return bookReviewDAO.UpdateReview(_updateReview);
    }
}
