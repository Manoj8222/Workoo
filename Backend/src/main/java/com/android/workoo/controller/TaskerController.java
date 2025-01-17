package com.android.workoo.controller;

import com.android.workoo.entity.Tasker;
import com.android.workoo.model.TaskerDTO;
import com.android.workoo.model.TaskerFavoriteDTO;
import com.android.workoo.service.TaskerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taskers")
public class TaskerController {

    @Autowired
    private TaskerService taskerService;

    @GetMapping("/search")
    public ResponseEntity<?> searchTaskers(
            @RequestParam(required = false) String skill,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer minFair,
            @RequestParam(required = false) Integer maxFair) {
        try {
            // Validate minFair and maxFair
            if (minFair != null && maxFair != null && minFair > maxFair) {
                return ResponseEntity.badRequest().body("Minimum fair cannot be greater than maximum fair.");
            }

            // Search for taskers
            List<Tasker> taskers = taskerService.searchTaskers(skill, city, location, minFair, maxFair);

            // Check if the result is empty
            if (taskers.isEmpty()) {
                return ResponseEntity.status(404).body("No taskers found matching the provided criteria.");
            }

            return ResponseEntity.ok(taskers);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while searching for taskers: " + e.getMessage());
        }
    }
    @GetMapping("/favorite")
    public ResponseEntity<List<TaskerFavoriteDTO>> getTaskers(@RequestParam Long userId) {
        return ResponseEntity.ok(taskerService.getFavoriteTaskers(userId));
    }
    @PostMapping("/{taskerId}/favorite")
    public ResponseEntity<Void> addToFavorites(@PathVariable Long taskerId, @RequestParam Long userId) {
        taskerService.addToFavorites(taskerId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{taskerId}")
    public ResponseEntity<TaskerDTO> getTaskerById(@PathVariable Long taskerId) {
        return ResponseEntity.ok(taskerService.getTaskerById(taskerId));
    }

    @DeleteMapping("/{taskerId}/favorite")
    public ResponseEntity<Void> removeFromFavorites(@PathVariable Long taskerId, @RequestParam Long userId) {
        taskerService.removeFromFavorites(taskerId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{taskerId}/favorite/check")
    public ResponseEntity<Boolean> checkFavoriteStatus(
            @PathVariable Long taskerId,
            @RequestParam Long userId) {
        return ResponseEntity.ok(taskerService.checkFavoriteStatus(taskerId, userId));
    }
}
