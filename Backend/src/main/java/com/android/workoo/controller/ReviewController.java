package com.android.workoo.controller;

import com.android.workoo.dto.ReviewRequestDTO;
import com.android.workoo.dto.ReviewResponseDTO;
import com.android.workoo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{taskerId}")
    public ResponseEntity<?> getReviewsByTaskerId(@PathVariable Long taskerId) {
        try {
            // Validate the taskerId
            if (taskerId == null || taskerId <= 0) {
                return ResponseEntity.badRequest().body("Invalid tasker ID. Tasker ID must be a positive number.");
            }

            // Fetch reviews
            List<ReviewResponseDTO> reviews = reviewService.getReviewsByTaskerId(taskerId);

            // Handle case where no reviews are found
            if (reviews == null || reviews.isEmpty()) {
                return ResponseEntity.status(404).body("No reviews found for the specified tasker ID.");
            }

            return ResponseEntity.ok(reviews);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while fetching reviews: " + e.getMessage());
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> createReview(@RequestParam("userId")Long userId,
                                          @RequestParam("taskerId") Long taskerId,
                                          @RequestParam("reviewDescription")String reviewDescription,
                                          @RequestParam("rating")float rating) {
        try {
            // Validate input
            if (taskerId == null || taskerId <= 0) {
                return ResponseEntity.badRequest().body("Invalid tasker ID. Tasker ID must be a positive number.");
            }
            if (userId == null || userId <= 0) {
                return ResponseEntity.badRequest().body("Invalid user ID. User ID must be a positive number.");
            }
            if (rating == 0.0 || rating < 0 || rating > 5) {
                return ResponseEntity.badRequest().body("Invalid rating. Rating must be between 0 and 5.");
            }

            // Check if review already exists
            if (reviewService.reviewExists(userId, taskerId)) {
                return ResponseEntity.badRequest().body("A review for this tasker by this user already exists.");
            }

            // Create review
            ReviewResponseDTO createdReview = reviewService.createReview(userId, taskerId, reviewDescription, rating);
            return ResponseEntity.status(201).body(createdReview);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while creating the review: " + e.getMessage());
        }
    }

}



