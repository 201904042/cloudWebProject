package com.example.BookReview;

import org.springframework.beans.factory.annotation.Autowired;

public class BookReviewServiceImpl implements BookReviewService{
    @Autowired
    private BookReviewDAO bookReviewDao;

}
