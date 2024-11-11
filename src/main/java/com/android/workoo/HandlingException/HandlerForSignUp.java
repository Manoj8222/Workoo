package com.android.workoo.HandlingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

public class HandlerForSignUp {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        // Log the exception details for debugging
        System.err.println("Data integrity violation: " + ex.getMessage());

        if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            // Customize the message for unique constraint violations
            return new ResponseEntity<>("Duplicate entry for phone number", HttpStatus.CONFLICT);
        }

        // For other data integrity violations
        return new ResponseEntity<>("Data integrity violation", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        // Handle SQL-specific constraint violation
        return new ResponseEntity<>("Duplicate entry for phone number", HttpStatus.CONFLICT);
    }

    // Handle other exceptions if needed
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Log general exceptions
        System.err.println("General error: " + ex.getMessage());
        return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
