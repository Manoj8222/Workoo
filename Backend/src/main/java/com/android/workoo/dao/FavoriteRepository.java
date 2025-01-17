package com.android.workoo.dao;

import com.android.workoo.entity.Favorite;
import com.android.workoo.entity.Tasker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(Long userId);
    boolean existsByTaskerIdAndUserId(Long taskerId, Long userId);
    void deleteByTaskerIdAndUserId(Long taskerId, Long userId);

}

// Add to TaskerRepository
