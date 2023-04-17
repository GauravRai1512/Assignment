package com.assignment.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.review.persistence.productreviewdb.Review;

public interface ReviewRepository extends JpaRepository<Review, String> {
	
	Review findReviewByProductID(@Param("productID") String productID);
	
	@Transactional
    @Modifying
	void deleteByProductID(@Param("productID") String productID);

}
