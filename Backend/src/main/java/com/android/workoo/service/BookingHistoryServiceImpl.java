package com.android.workoo.service;

import com.android.workoo.dao.BookingHistoryRepository;
import com.android.workoo.dto.BookingHistoryResponseDTO;
import com.android.workoo.entity.BookingHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookingHistoryServiceImpl implements BookingHistoryService {

    @Autowired
    private BookingHistoryRepository bookingHistoryRepository;

    @Override
    public BookingHistory createBooking(Long userId, Long taskerId, String bookingDate, String bookingTime,Long fair) {
        BookingHistory booking = new BookingHistory();
        booking.setUserId(userId);
        booking.setTaskerId(taskerId);
        booking.setBookingDate(bookingDate);
        booking.setBookingTime(bookingTime);
        booking.setFair(fair);

        return bookingHistoryRepository.save(booking);
    }

    @Override
    public Optional<BookingHistory> getBookingById(Long id) {
        return bookingHistoryRepository.findById(id);
    }

    @Override
    public List<Object[]> getBookingWithTaskerDetails(Long bookingId) {
        return bookingHistoryRepository.findBookingWithTaskerDetails(bookingId);
    }

    @Override
    public List<BookingHistoryResponseDTO> getScheduledTasksWithTaskerDetailsByUserId(Long userId) {
        List<Object[]> results = bookingHistoryRepository.findScheduledTasksWithTaskerDetailsByUserId(userId);
        return transformResults(results);
    }

    @Override
    public List<BookingHistoryResponseDTO> getCompletedTasksWithTaskerDetailsByUserId(Long userId) {
        List<Object[]> results = bookingHistoryRepository.findCompletedTasksWithTaskerDetailsByUserId(userId);
        return transformResults1(results);
    }

    private List<BookingHistoryResponseDTO> transformResults(List<Object[]> results) {
        List<BookingHistoryResponseDTO> responseList = new ArrayList<>();
        for (Object[] row : results) {
            BookingHistoryResponseDTO dto = new BookingHistoryResponseDTO();
            dto.setBookingId((Long) row[0]);
            dto.setUserId((Long) row[1]);
            dto.setBookingDate((String) row[2]);
            dto.setBookingTime((String) row[3]);
            dto.setTaskCompleted((Boolean) row[4]);
            dto.setFair(((Long)row[5])); // Handle null and type conversion
            dto.setTaskerId((Long) row[6]);
            dto.setTaskerName((String) row[7]);
            dto.setTaskerImage((String) row[8]); // Assuming image is stored as a string (URL or Base64)
            dto.setTaskerRating((BigDecimal) row[9]);
            dto.setTaskerTotalProjects((Long) row[10]);

            responseList.add(dto);
        }
        return responseList;
    }

    @Override
    @Transactional
    public boolean completeTask(Long bookingId) {
        // Find the booking by ID
        BookingHistory booking = bookingHistoryRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + bookingId));

        // Check if the task is already completed
        if (booking.isTaskCompleted()) {
            throw new RuntimeException("Task is already completed.");
        }

        // Mark the task as completed
        booking.setTaskCompleted(true);
        bookingHistoryRepository.save(booking);

        return true;
    }

    private List<BookingHistoryResponseDTO> transformResults1(List<Object[]> results) {
        List<BookingHistoryResponseDTO> responseList = new ArrayList<>();
        for (Object[] row : results) {

            BookingHistoryResponseDTO dto = new BookingHistoryResponseDTO();
            dto.setBookingId((Long) row[0]);
            dto.setUserId((Long) row[1]);
            dto.setBookingDate((String) row[2]);
            dto.setBookingTime((String) row[3]);
            dto.setTaskCompleted((Boolean) row[4]);
//            dto.setFair(((Long)row[5])); // Handle null and type conversion
            dto.setTaskerId((Long) row[5]);
            dto.setTaskerName((String) row[6]);
            dto.setTaskerImage((String) row[7]); // Assuming image is stored as a string (URL or Base64)
            dto.setTaskerRating((BigDecimal) row[8]);
            dto.setTaskerTotalProjects((Long) row[9]);

            responseList.add(dto);
        }
        return responseList;
    }


}
