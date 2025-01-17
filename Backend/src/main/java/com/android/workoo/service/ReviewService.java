package com.android.workoo.service;

import com.android.workoo.dto.ReviewRequestDTO;
import com.android.workoo.dto.ReviewResponseDTO;
import com.android.workoo.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ReviewService {
    List<ReviewResponseDTO>  getReviewsByTaskerId(Long taskerId);

    ReviewResponseDTO createReview(Long userId, Long taskerId,String reviewDescription,float review);


    boolean reviewExists(Long userId, Long taskerId);
}