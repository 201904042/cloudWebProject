package com.example.BookReview;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class BookReviewDAOImpl implements BookReviewDAO{
    private List<BookDTO> bookList;
    private List<ReviewDTO> reviewList;
    public BookReviewDAOImpl(){
        bookList = new ArrayList<>();
        reviewList = new ArrayList<>();
        bookList.add(new BookDTO(0,"testTitle","testAuthor","testGenre","testSummary",0));
        bookList.add(new BookDTO(1,"testTitle","testAuthor","testGenre","testSummary",0));

        reviewList.add(new ReviewDTO(0,"testname",5,0,"test"));
        reviewList.add(new ReviewDTO(1,"testname1",2,0,"test"));
        reviewList.add(new ReviewDTO(2,"testname2",3,1,"test"));
        reviewList.add(new ReviewDTO(3,"testname3",4,1,"test"));
    }

    @Override
    public List<BookDTO> GetBookData(){ //메인페이지 -> 책 리스트
        return bookList;
    }
    @Override
    public BookDTO GetBookById(long bookId){ //메인페이지->책 검색
        return  bookList.stream()
                .filter(book ->  book.getId() == bookId)
                .findFirst()
                .orElse(null);
    }
    @Override
    public BookDTO InsertBook(BookDTO book) { //책 생성페이지 -> 책 생성
        long id = bookList.get( bookList.size() - 1).getId() + 1;
        book.setId(id);
        bookList.add(book);
        return book;
    }

    @Override
    public BookDTO DeleteBook(long deleteBookId) { //메인페이지 -> 책 제거
        BookDTO deleteBook = bookList.stream()
                .filter(book -> book.getId() == deleteBookId)
                .findFirst()
                .get();
        reviewList.removeIf(review -> review.getBookId() == deleteBookId);
        bookList.remove(deleteBook);
        return deleteBook;
    }

    @Override
    public BookDTO UpdateBook(BookDTO _updateBook) { //책 수정창 -> 책 수정
        BookDTO updateBook = bookList.stream()
                .filter(book -> book.getId() == _updateBook.getId())
                .findFirst()
                .get();
        updateBook.setTitle(_updateBook.getTitle());
        updateBook.setAuthor(_updateBook.getAuthor());
        updateBook.setGenre(_updateBook.getGenre());
        updateBook.setSummary(_updateBook.getSummary());
        updateBook.setReviewCount(_updateBook.getReviewCount());
        return updateBook;
    }

    @Override
    public List<ReviewDTO> GetReview(){ //책상세페이지 -> 리뷰리스트
        return reviewList;
    }
    @Override
    public List<ReviewDTO> GetReviewByBookId(long bookId){ //책상세페이지 -> 리뷰리스트
        List<ReviewDTO> ReviewWithBookId = new ArrayList<>();

        ReviewWithBookId =  reviewList.stream()
                .filter(review -> review.getBookId() == bookId)
                .collect(Collectors.toList());
        return ReviewWithBookId;
    }
    @Override
    public ReviewDTO InsertReview(ReviewDTO newReview) { //리뷰생성창 -> 리뷰 생성
        long id = reviewList.get( reviewList.size() - 1).getId() + 1;
        newReview.setId(id);
        reviewList.add(newReview);
        return newReview;
    }

    @Override
    public ReviewDTO DeleteReview(long deleteReviewId) { //책상세페이지 -> 리뷰 삭제
        ReviewDTO deleteReview = reviewList.stream()
                .filter(review -> review.getId() == deleteReviewId)
                .findFirst()
                .get();
        reviewList.remove(deleteReview);
        return deleteReview;
    }

    @Override
    public ReviewDTO UpdateReview(ReviewDTO _updateReview) { //리뷰수정창 -> 리뷰 수정
        ReviewDTO updateReview = reviewList.stream()
                .filter(review -> review.getId() == _updateReview.getId())
                .findFirst()
                .get();
        updateReview.setName(_updateReview.getName());
        updateReview.setGrade(_updateReview.getGrade());
        updateReview.setBookId(_updateReview.getBookId());
        updateReview.setContent(_updateReview.getContent());
        return updateReview;
    }
}
