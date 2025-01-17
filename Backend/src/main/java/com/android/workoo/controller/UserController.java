package com.android.workoo.controller;

import com.android.workoo.entity.User;
import com.android.workoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserDetails(@PathVariable Long id) {
        try {
            User user = userService.getUserDetails(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<?> updateUserName(@PathVariable Long id, @RequestParam String newName) {
        try {
            userService.updateUserName(id, newName);
            return ResponseEntity.ok("User name updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<?> updateUserPassword(@PathVariable Long id, @RequestParam String newPassword) {
        try {
            userService.updateUserPassword(id, newPassword);
            return ResponseEntity.ok("User password updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    @GetMapping("/getUserId")
    public Long getUserId(@RequestParam("phone_number") BigInteger phone){
        return userService.getUserIdByPhone(phone);
    }
}
