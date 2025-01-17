package com.android.workoo.dao;

import com.android.workoo.entity.BookingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingHistoryRepository extends JpaRepository<BookingHistory,Long> {
    @Query("SELECT b.id, b.userId, b.bookingDate, b.bookingTime, " +
            "t.id, t.taskerName, t.img, t.rating, t.totalProject " +
            "FROM BookingHistory b " +
            "JOIN Tasker t ON b.taskerId = t.id " +
            "WHERE b.id = :bookingId")
    List<Object[]> findBookingWithTaskerDetails(@Param("bookingId") Long bookingId);

//    @Query("""
//        SELECT b.id, b.userId, b.bookingDate, b.bookingTime, b.taskCompleted, t.fair,
//               t.id, t.taskerName, t.img, t.rating, t.totalProject
//        FROM BookingHistory b
//        JOIN Tasker t ON b.taskerId = t.id
//        WHERE b.userId = :userId AND b.taskCompleted = false
//    """)
//    List<Object[]> findScheduledTasksWithTaskerDetailsByUserId(@Param("userId") Long userId);

    @Query("""
    SELECT b.id AS bookingId, b.userId AS userId, b.bookingDate AS bookingDate, 
           b.bookingTime AS bookingTime, b.taskCompleted AS taskCompleted, 
           b.fair AS fair, t.id AS taskerId, t.taskerName AS taskerName, 
           t.img AS taskerImage, t.rating AS taskerRating, t.totalProject AS taskerTotalProjects 
    FROM BookingHistory b 
    JOIN Tasker t ON b.taskerId = t.id
    WHERE b.userId = :userId AND b.taskCompleted = false
""")
    List<Object[]> findScheduledTasksWithTaskerDetailsByUserId(@Param("userId") Long userId);


    @Query("""
        SELECT b.id, b.userId, b.bookingDate, b.bookingTime, b.taskCompleted, 
               t.id, t.taskerName, t.img, t.rating, t.totalProject
        FROM BookingHistory b 
        JOIN Tasker t ON b.taskerId = t.id
        WHERE b.userId = :userId AND b.taskCompleted = true
    """)
    List<Object[]> findCompletedTasksWithTaskerDetailsByUserId(@Param("userId") Long userId);

}
