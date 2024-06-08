package com.example.BookReview;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookReivewDAOImpl implements BookReviewDAO{
    private List<BookDTO> bookList;
    private List<ReviewDTO> reviewList;
    public BookReivewDAOImpl(){
        bookList = new ArrayList<>();
        reviewList = new ArrayList<>();
    }

    //책 관련 함수
    @Override
    public int BookCount() { //책의 개수
        return bookList.size();
    }
    @Override
    public List<BookDTO> GetBookData() { //전체 책 데이터베이스 로드-> 메인창.책리스트
        return bookList;
    }
    @Override
    public BookDTO getBookById(long bookId){ //도서 상세정보창에서 id로 도서 불러오기 -> 메인창 검색기능
        return bookList.stream().filter(book -> book.getBookId() == bookId).findFirst().get();
    }
    @Override
    public BookDTO InsertBookData(BookDTO bookDTO) { //새로운 책 생성 -> 책 생성창.생성
        long newBookId = bookList.get( bookList.size() - 1).getBookId() + 1;
        bookDTO.setBookId(newBookId);
        bookList.add(bookDTO);
        return bookDTO;
    }
    @Override
    public BookDTO DeleteBookData(long id) { //데이터베이스에서 해당 id의 책 삭제 -> 메인창.책 삭제
        BookDTO ret = bookList.stream()
                .filter(book -> book.getBookId() == id)
                .findFirst()
                .get();
        bookList.remove(ret);
        return ret;
    }
    @Override
    public BookDTO UpdateBookData(BookDTO updateBook) { //해당 내용을 데이터베이스에 반영 -> 책수정창.수정
        BookDTO updateData = bookList.stream()
                .filter(book -> book.getBookId() == updateBook.getBookId())
                .findFirst()
                .get();
        updateData.setBookTitle(updateData.getBookTitle());
        updateData.setBookAuthor(updateBook.getBookAuthor());
        updateData.setBookGenre(updateBook.getBookGenre());
        updateData.setBookSummary(updateBook.getBookSummary());
        updateData.setBookReviewCount(updateBook.getBookReviewCount());

        return updateData;
    }
    
    //리뷰 관련 함수
    @Override
    public int ReviewCount() { //리뷰데이터베이스의 크기를 구함
        return reviewList.size();
    }
    @Override
    public List<ReviewDTO> GetReviewData(long bookId) { //해당 책의 리뷰 데이터베이스 로드 -> 책 상세정보창.리뷰의 리스트
        List<ReviewDTO> reviewListInBookContents = new ArrayList<ReviewDTO>();

        reviewListInBookContents = reviewList.stream()
                .filter(review -> review.getReviewBookId() == bookId)
                .collect(Collectors.toList());

        return reviewListInBookContents;
    }
    @Override
    public ReviewDTO GetReviewById(long reviewId){ //책 상세정보창.리뷰검색
        return reviewList.stream().filter(review -> review.getReviewId() == reviewId).findFirst().get();
    }
    @Override
    public ReviewDTO InsertReviewData(ReviewDTO reviewDTO) { //새로운 리뷰 생성
        long newReviewId = reviewList.get( reviewList.size() - 1).getReviewId() + 1;
        reviewDTO.setReviewId(newReviewId);
        reviewList.add(reviewDTO);
        return reviewDTO;
    }
    @Override
    public ReviewDTO DeleteReviewData(long reviewId) { //데이터베이스에서 해당 id의 리뷰 삭제
        ReviewDTO deleteReview = reviewList.stream()
                .filter(review -> review.getReviewId() == reviewId)
                .findFirst()
                .get();
        reviewList.remove(deleteReview);
        return deleteReview;
    }
    @Override
    public ReviewDTO UpdateReviewData(ReviewDTO updateReview) { //데이터베이스에서 해당 리뷰객체의 내용으로 수정
        ReviewDTO updateReviewData = reviewList.stream()
                .filter(review -> review.getReviewId() == updateReview.getBookId())
                .findFirst()
                .get();
        updateReviewData.setReviewerName(updateReview.getReviewerName());
        updateReviewData.setReviewBookGrade(updateReview.getReviewBookGrade());
        updateReviewData.setReviewBookId(updateReview.getReviewBookId());
        updateReviewData.setReviewContent(updateReview.getReviewContent());
        return updateReviewData;
    }
}
