package com.android.workoo.controller;

import com.android.workoo.dto.BookingHistoryResponseDTO;
import com.android.workoo.entity.BookingHistory;
import com.android.workoo.service.BookingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingHistoryController {

    @Autowired
    private BookingHistoryService bookingHistoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(
            @RequestParam Long userId,
            @RequestParam Long taskerId,
            @RequestParam String bookingDate,
            @RequestParam String bookingTime,
            @RequestParam Long fair) {
        try {
            if (userId == null || taskerId == null || bookingDate == null || bookingTime == null) {
                return ResponseEntity.badRequest().body("Missing required parameters.");
            }

            BookingHistory booking = bookingHistoryService.createBooking(userId, taskerId, bookingDate, bookingTime,fair);
            return ResponseEntity.ok(booking);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while creating the booking: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest().body("Invalid booking ID.");
            }

            List<Object[]> result = bookingHistoryService.getBookingWithTaskerDetails(id);

            if (result.isEmpty()) {
                return ResponseEntity.status(404).body("Booking not found for the given ID.");
            }

            // Map the result to a structured response
            Object[] data = result.get(0);

            Map<String, Object> response = new HashMap<>();
            response.put("bookingId", data[0]);
            response.put("userId", data[1]);
            response.put("bookingDate", data[2]);
            response.put("bookingTime", data[3]);
            response.put("taskerId", data[4]);
            response.put("taskerName", data[5]);
            response.put("taskerImage", data[6]); // This will be a byte array
            response.put("taskerRating", data[7]);
            response.put("totalProjects", data[8]);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while fetching booking details: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}/scheduled")
    public ResponseEntity<?> getScheduledTasks(@PathVariable Long userId) {
        try {
            if (userId == null || userId <= 0) {
                return ResponseEntity.badRequest().body("Invalid user ID.");
            }

            List<BookingHistoryResponseDTO> scheduledTasks = bookingHistoryService.getScheduledTasksWithTaskerDetailsByUserId(userId);

            if (scheduledTasks.isEmpty()) {
                return ResponseEntity.status(404).body("No scheduled tasks found for the given user ID.");
            }

            return ResponseEntity.ok(scheduledTasks);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while fetching scheduled tasks: " + e.getLocalizedMessage());
        }
    }

    @GetMapping("/{userId}/completed")
    public ResponseEntity<?> getCompletedTasks(@PathVariable Long userId) {
//        try {
//            if (userId == null || userId <= 0) {
//                return ResponseEntity.badRequest().body("Invalid user ID.");
//            }

            List<BookingHistoryResponseDTO> completedTasks = bookingHistoryService.getCompletedTasksWithTaskerDetailsByUserId(userId);

//            if (completedTasks.isEmpty()) {
//                return ResponseEntity.status(404).body("No completed tasks found for the given user ID.");
//            }

            return ResponseEntity.ok(completedTasks);

//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("An error occurred while fetching completed tasks: " + e.getMessage());
//        }
    }

    @PutMapping("/{bookingId}/complete")
    public ResponseEntity<?> completeTask(@PathVariable Long bookingId) {
        try {
            if (bookingId == null || bookingId <= 0) {
                return ResponseEntity.badRequest().body("Invalid booking ID.");
            }

            boolean success = bookingHistoryService.completeTask(bookingId);
            if (success) {
                return ResponseEntity.ok("Task marked as completed successfully.");
            } else {
                return ResponseEntity.status(400).body("Unable to mark task as completed.");
            }

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while completing the task: " + e.getMessage());
        }
    }
}

