package com.example.BookReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins="*", allowedHeaders = "*")
public class BookReviewController {
    @Autowired
    private BookReviewService bookReviewService;

    // Book 관련 엔드포인트'
    @GetMapping("/books")
    public List<BookDTO> getAllBooks() {
        return bookReviewService.bookList();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        BookDTO book = bookReviewService.findBookById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/books")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        BookDTO savedBook = bookReviewService.addBook(bookDTO);
        return ResponseEntity.ok(savedBook);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookReviewService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookReviewService.updateBook(id, bookDTO);
        return ResponseEntity.ok(updatedBook);
    }

    @GetMapping("/books/search")
    public List<BookDTO> searchBooks(@RequestParam String keyword) {
        return bookReviewService.searchBooks(keyword);
    }

    @GetMapping("/books/checkDuplicate")
    public ResponseEntity<Boolean> checkDuplicate(@RequestParam String title) {
        boolean isDuplicate = bookReviewService.checkDuplicate(title);
        return ResponseEntity.ok(isDuplicate);
    }

    // Review 관련 엔드포인트
    @GetMapping("/reviews")
    public List<ReviewDTO> getAllReviews() {
        return bookReviewService.ReviewList();
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) {
        ReviewDTO review = bookReviewService.findReviewById(id);
        if (review != null) {
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/reviews")
    public ResponseEntity<ReviewDTO> addReview(@RequestBody ReviewDTO reviewDTO) {
        ReviewDTO savedReview = bookReviewService.addReview(reviewDTO);
        return ResponseEntity.ok(savedReview);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        bookReviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reviews/search")
    public List<ReviewDTO> searchReviews(@RequestParam String keyword) {
        return bookReviewService.searchReviews(keyword);
    }
}
