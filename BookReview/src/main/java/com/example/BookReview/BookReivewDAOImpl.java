package com.example.BookReview;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class BookReivewDAOImpl implements BookReviewDAO{
    private List<BookDTO> bookList;
    private List<ReviewDTO> reviewList;
    public BookReivewDAOImpl(){
        bookList = new ArrayList<>();
        reviewList = new ArrayList<>();
    }




}
