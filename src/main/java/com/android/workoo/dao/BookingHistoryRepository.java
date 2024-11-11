package com.android.workoo.dao;

import com.android.workoo.entity.BookingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingHistoryRepository extends JpaRepository<BookingHistory,Long> {
}
