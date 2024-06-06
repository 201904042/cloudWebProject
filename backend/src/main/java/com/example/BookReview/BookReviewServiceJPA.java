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
    public List<BookDTO> bookList() { //책 리스트 생성
        return bookRepository.findAll().stream()
                .map(this::convertToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO findBookById(Long id) { //주어진 id를 기반으로 책 검색
        Optional<Book> book = bookRepository.findById(id);
        return book.map(this::convertToBookDTO).orElse(null);
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) { //데이터베이스에 책 추가
        Book book = convertToBookEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return convertToBookDTO(savedBook);
    }

    @Override
    public void deleteBook(Long id) { //책 아이디 기반으로 데이터베이스에서 책 제거
        bookRepository.deleteById(id);
    }
    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO){ //책의 id를 기반으로 해당 책 데이터를 찾고 입력된 정보로 업데이트
        Optional<Book> updateBook = bookRepository.findById(id);
        Book bookToUpdate = updateBook.get();

        bookToUpdate.setTitle(bookDTO.getTitle());
        bookToUpdate.setAuthor(bookDTO.getAuthor());
        bookToUpdate.setGenre(bookDTO.getGenre());
        bookToUpdate.setSummary(bookDTO.getSummary());

        Book updatedBook = bookRepository.save(bookToUpdate);
        return convertToBookDTO(updatedBook);
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

    //리뷰
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
        Optional<Book> optionalBook = bookRepository.findById(reviewDTO.getBook_id());
        Book book = optionalBook.get();
        book.setReview_count(book.getReview_count()+1);
        bookRepository.save(book);

        Review review = convertToReviewEntity(reviewDTO);
        Review savedReview = reviewRepository.save(review);
        return convertToReviewDTO(savedReview);
    }

    @Override
    public void deleteReview(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()) {
            Review review = reviewOptional.get();
            reviewRepository.deleteById(id); // 리뷰 삭제

            // 해당 책의 review_count 감소
            Long bookId = review.getBook_id();
            Book book = bookRepository.findById(bookId).orElse(null);
            if (book != null) {
                long currentReviewCount = book.getReview_count();
                book.setReview_count(currentReviewCount - 1);
                bookRepository.save(book); // 책 정보 업데이트
            }
        }
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
