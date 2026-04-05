package com.manikandan.shopping.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ProductReviewDto
{
    @NotNull(message = "Product Id is required")
    private Long productId;
    @NotBlank(message = "Comment is not blank")
    private String comment;
    @NotNull(message = "rating is required")
    private Double rating;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
