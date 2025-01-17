package com.android.workoo.service;

import com.android.workoo.dao.ReviewRepository;
import com.android.workoo.dto.ReviewRequestDTO;
import com.android.workoo.dto.ReviewResponseDTO;
import com.android.workoo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    @Override
    public List<ReviewResponseDTO> getReviewsByTaskerId(Long taskerId) {
        List<Object[]> results = reviewRepository.findReviewsByTaskerIdNative(taskerId);

        return results.stream().map(row -> new ReviewResponseDTO(
                (Long) row[0],  // reviewId
                (String) row[1],                   // userName
                (String) row[2],                   // profilePhoto
                ((Timestamp) row[3]).toLocalDateTime(), // date
                ((BigDecimal) row[4]).doubleValue(),   // rating
                (String) row[5]                    // reviewDescription
        )).collect(Collectors.toList());
    }

    @Override
    public ReviewResponseDTO createReview(Long userId, Long taskerId,String reviewDescription,float rating) {
        Review review = new Review();
        review.setUserId(userId);
        review.setTaskerId(taskerId);
        review.setReviewDescription(reviewDescription);
        review.setRating(rating);
        review.setDate(String.valueOf(LocalDateTime.now()));

        Review savedReview = reviewRepository.save(review);

        // Convert to DTO for response
        ReviewResponseDTO responseDTO = new ReviewResponseDTO();
        responseDTO.setId(savedReview.getId());
//        responseDTO.setUserId(savedReview.getUserId());
//        responseDTO.setTaskerId(savedReview.getTaskerId());
        responseDTO.setReviewDescription(savedReview.getReviewDescription());
        responseDTO.setRating((double) savedReview.getRating());
        responseDTO.setDate(LocalDateTime.parse(savedReview.getDate()));

        return responseDTO;
    }
    @Override
    public boolean reviewExists(Long userId, Long taskerId) {
        // Implementation depends on your data access layer
        // This is just a placeholder example
        return reviewRepository.existsByUserIdAndTaskerId(userId, taskerId);
    }

//    @Override
//    public List<Review> getReviewsByTaskerId(Long taskerId) {
//        if (taskerId == null || taskerId <= 0) {
//            throw new IllegalArgumentException("Tasker ID must be a positive number.");
//        }
//
//        List<Review> reviews = reviewRepository.findByTaskerId(taskerId);
//
//        if (reviews.isEmpty()) {
//            throw new ReviewNotFoundException("No reviews found for Tasker ID: " + taskerId);
//        }
//
//        return reviews;
//    }
}