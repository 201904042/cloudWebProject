package com.example.BookReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookReviewServiceJPA implements BookReviewService{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<BookDTO> bookList() {
        return bookRepository.findAll().stream()
                .map(this::convertToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO findBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(this::convertToBookDTO).orElse(null);
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = convertToBookEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return convertToBookDTO(savedBook);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDTO> searchBooks(String keyword) {
        return bookRepository.findByTitleContainingOrAuthorContaining(keyword, keyword).stream()
                .map(this::convertToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkDuplicate(String title) {
        return bookRepository.findByTitleContainingOrAuthorContaining(title, title).size() > 0;
    }

    private BookDTO convertToBookDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(),book.getSummary(), book.getReview_count());
    }

    private Book convertToBookEntity(BookDTO bookDTO) {
        return new Book(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getGenre(), bookDTO.getSummary(), bookDTO.getReview_count());
    }

    @Override
    public List<ReviewDTO> ReviewList() {
        return reviewRepository.findAll().stream()
                .map(this::convertToReviewDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO findReviewById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        return review.map(this::convertToReviewDTO).orElse(null);
    }

    @Override
    public ReviewDTO addReview(ReviewDTO reviewDTO) {
        Review review = convertToReviewEntity(reviewDTO);
        Review savedReview = reviewRepository.save(review);
        return convertToReviewDTO(savedReview);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<ReviewDTO> searchReviews(String keyword) {
        return reviewRepository.findByContentContainingOrNameContaining(keyword, keyword).stream()
                .map(this::convertToReviewDTO)
                .collect(Collectors.toList());
    }

    private ReviewDTO convertToReviewDTO(Review review) {
        return new ReviewDTO(review.getId(),review.getName(),review.getGrade(),review.getBook_id(),review.getContent());
    }

    private Review convertToReviewEntity(ReviewDTO reviewDTO) {
        return new Review(reviewDTO.getId(),reviewDTO.getName(),reviewDTO.getGrade(),reviewDTO.getBook_id(),reviewDTO.getContent());
    }
}
