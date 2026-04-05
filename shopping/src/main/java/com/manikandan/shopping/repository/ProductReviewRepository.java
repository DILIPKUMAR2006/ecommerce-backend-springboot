package com.manikandan.shopping.repository;

import com.manikandan.shopping.entity.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long>
{

}
