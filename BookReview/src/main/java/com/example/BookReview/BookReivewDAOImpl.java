package com.example.BookReview;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookReivewDAOImpl implements BookReviewDAO{
    private List<BookDTO> bookList;
    private List<ReviewDTO> reviewList;
    public BookReivewDAOImpl(){
        bookList = new ArrayList<>();
        reviewList = new ArrayList<>();
    }

    @Override
    public List<BookDTO> GetBookData(){ //메인페이지 -> 책 리스트

        return bookList;
    }
    @Override
    public BookDTO GetBookById(long bookId){ //메인페이지->책 검색
        return  bookList.stream()
                .filter(book ->  book.getBookId() == bookId)
                .findFirst()
                .get();
    }
    @Override
    public BookDTO InsertBook(BookDTO book) { //책 생성페이지 -> 책 생성
        long id = bookList.get( bookList.size() - 1).getBookId() + 1;
        book.setBookId(id);
        bookList.add(book);
        return book;
    }

    @Override
    public BookDTO DeleteBook(long deleteBookId) { //메인페이지 -> 책 제거
        BookDTO deleteBook = bookList.stream()
                .filter(person -> person.getBookId() == deleteBookId)
                .findFirst()
                .get();
        bookList.remove(deleteBook);
        return deleteBook;
    }

    @Override
    public BookDTO UpdateBook(BookDTO _updateBook) { //책 수정창 -> 책 수정
        BookDTO updateBook = bookList.stream()
                .filter(book -> book.getBookId() == _updateBook.getBookId())
                .findFirst()
                .get();
        updateBook.setBookTitle(_updateBook.getBookTitle());
        updateBook.setBookAuthor(_updateBook.getBookAuthor());
        updateBook.setBookGenre(_updateBook.getBookGenre());
        updateBook.setBookSummary(_updateBook.getBookSummary());
        updateBook.setBookReviewCount(_updateBook.getBookReviewCount());
        return updateBook;
    }

    @Override
    public List<ReviewDTO> GetReviewByBookId(long bookId){ //책상세페이지 -> 리뷰리스트
        List<ReviewDTO> ReviewWithBookId = new ArrayList<>();

        ReviewWithBookId =  reviewList.stream()
                .filter(review -> review.getReviewBookId() == bookId)
                .collect(Collectors.toList());
        return ReviewWithBookId;
    }
    @Override
    public ReviewDTO InsertReview(ReviewDTO newReview) { //리뷰생성창 -> 리뷰 생성
        long id = reviewList.get( reviewList.size() - 1).getReviewId() + 1;
        newReview.setReviewId(id);
        reviewList.add(newReview);
        return newReview;
    }

    @Override
    public ReviewDTO DeleteReview(long deleteReviewId) { //책상세페이지 -> 리뷰 삭제
        ReviewDTO deleteReview = reviewList.stream()
                .filter(review -> review.getReviewId() == deleteReviewId)
                .findFirst()
                .get();
        reviewList.remove(deleteReview);
        return deleteReview;
    }

    @Override
    public ReviewDTO UpdateReview(ReviewDTO _updateReview) { //리뷰수정창 -> 리뷰 수정
        ReviewDTO updateReview = reviewList.stream()
                .filter(review -> review.getReviewId() == _updateReview.getReviewId())
                .findFirst()
                .get();
        updateReview.setReviewerName(_updateReview.getReviewerName());
        updateReview.setReviewBookGrade(_updateReview.getReviewBookGrade());
        updateReview.setReviewBookId(_updateReview.getReviewBookId());
        updateReview.setReviewContent(_updateReview.getReviewContent());
        return updateReview;
    }
}
