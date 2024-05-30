package com.example.BookReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookReviewController {
    @Autowired
    BookReviewService bookReviewService;

    @GetMapping("/bookReview/book")
    public List<BookDTO> GetBookData(){
        return bookReviewService.GetBookData();
    }
    @GetMapping("/bookReview/book/{id}")
    public BookDTO GetBookById(@PathVariable long bookId){
        return bookReviewService.GetBookById(bookId);
    }
    @PostMapping("/bookReview/book")
    public BookDTO InsertBook(@RequestBody BookDTO book){
        return bookReviewService.InsertBook(book);
    }
    @DeleteMapping("/bookReview/book")
    public BookDTO DeleteBook(@PathVariable long deleteBookId){
        return bookReviewService.DeleteBook(deleteBookId);
    }
    @PutMapping("/bookReview/book")
    public BookDTO UpdateBook(@RequestBody BookDTO _updateBook){
        return bookReviewService.UpdateBook(_updateBook);
    }
    @GetMapping("/bookReview/review/{id}")
    public List<ReviewDTO> GetReviewByBookId(@PathVariable long bookId){
        return bookReviewService.GetReviewByBookId(bookId);
    }
    @PostMapping("/bookReview/review")
    public ReviewDTO InsertReview(@RequestBody ReviewDTO newReview){
        return bookReviewService.InsertReview(newReview);
    }
    @DeleteMapping("/bookReview/review")
    public ReviewDTO DeleteReview(@PathVariable long deleteReviewId){
        return bookReviewService.DeleteReview(deleteReviewId);
    }
    @PutMapping("/bookReview/review")
    public ReviewDTO UpdateReview(@RequestBody ReviewDTO _updateReview){
        return bookReviewService.UpdateReview(_updateReview);
    }
}
