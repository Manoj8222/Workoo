package com.android.workoo.dao;

import com.android.workoo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "SELECT r.id AS reviewId, u.user_name AS userName, u.img AS profilePhoto, " +
            "r.date, r.rating, r.review_description AS reviewDescription " +
            "FROM review r " +
            "JOIN user u ON r.user_id = u.id " +
            "WHERE r.tasker_id = :taskerId", nativeQuery = true)
    List<Object[]> findReviewsByTaskerIdNative(@Param("taskerId") Long taskerId);

    boolean existsByUserIdAndTaskerId(Long userId, Long taskerId);
}
