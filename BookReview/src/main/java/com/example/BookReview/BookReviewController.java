package com.example.BookReview;

import org.springframework.beans.factory.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookReviewController {
    @Autowired
    BookReviewService bookReviewService;

    @GetMapping("/bookReview/book")
    public String GetBookData(Model model){
        List<BookDTO> books = bookReviewService.GetBookData();
        model.addAttribute("books",books);
        return "book_list";
    }

    @GetMapping("/bookReview/book/")
    public String GetBookById(@RequestParam("_bookId") long bookId, Model model){
        BookDTO book = bookReviewService.GetBookById(bookId);
        if(book!=null){
            List<BookDTO> books = new ArrayList<>();
            books.add(book);
            model.addAttribute("books", books);
            return "book_list";
        }
        return "book_list";
    }
    @PostMapping("/bookReview/book")
    public BookDTO InsertBook(@RequestBody BookDTO book){
        return bookReviewService.InsertBook(book);
    }
    @DeleteMapping("/bookReview/book/{deleteBookId}")
    public BookDTO DeleteBook(@PathVariable long deleteBookId){
        return bookReviewService.DeleteBook(deleteBookId);
    }
    @PutMapping("/bookReview/book")
    public BookDTO UpdateBook(@RequestBody BookDTO _updateBook){
        return bookReviewService.UpdateBook(_updateBook);
    }

    @GetMapping("/bookReview/review")
    public List<ReviewDTO> GetReview(){
        return bookReviewService.GetReview();
    }
    @GetMapping("/bookReview/review/{bookId}")
    public List<ReviewDTO> GetReviewByBookId(@PathVariable long bookId){
        return bookReviewService.GetReviewByBookId(bookId);
    }
    @PostMapping("/bookReview/review")
    public ReviewDTO InsertReview(@RequestBody ReviewDTO newReview){
        return bookReviewService.InsertReview(newReview);
    }
    @DeleteMapping("/bookReview/review/{deleteReviewId}")
    public ReviewDTO DeleteReview(@PathVariable long deleteReviewId){
        return bookReviewService.DeleteReview(deleteReviewId);
    }
    @PutMapping("/bookReview/review")
    public ReviewDTO UpdateReview(@RequestBody ReviewDTO _updateReview){
        return bookReviewService.UpdateReview(_updateReview);
    }
}
