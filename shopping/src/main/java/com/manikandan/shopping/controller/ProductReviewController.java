package com.manikandan.shopping.controller;

import com.manikandan.shopping.dto.ProductReviewDto;
import com.manikandan.shopping.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products/reviews")
public class ProductReviewController
{
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody @Valid ProductReviewDto reviewDto)
    {
        productService.addReview(reviewDto);
        // Accept review data from request body and validate it
        return ResponseEntity.status(HttpStatus.CREATED).body("Review added");
        // Return HTTP response with 201 (Created) status and success message
    }
}