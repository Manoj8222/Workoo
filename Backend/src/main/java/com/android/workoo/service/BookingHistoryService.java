package com.android.workoo.service;

import com.android.workoo.dto.BookingHistoryResponseDTO;
import com.android.workoo.entity.BookingHistory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public interface BookingHistoryService {
    BookingHistory createBooking(Long userId, Long taskerId, String bookingDate, String bookingTime,Long fair);
    Optional<BookingHistory> getBookingById(Long id);

    List<Object[]> getBookingWithTaskerDetails(Long bookingId);

    List<BookingHistoryResponseDTO> getScheduledTasksWithTaskerDetailsByUserId(Long userId);
    List<BookingHistoryResponseDTO> getCompletedTasksWithTaskerDetailsByUserId(Long userId);

    @Transactional
    boolean completeTask(Long bookingId);
}
