package com.ecom.payload;

import com.ecom.entities.Book;
import com.ecom.entities.Customer;
import lombok.Data;

@Data

public class BookReviewDto {
    private long reviewId;
    private String reviews;
    private String rating;
    private String reviewDate;
    private String userName;
}
