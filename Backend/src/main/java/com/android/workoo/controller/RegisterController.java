package com.android.workoo.controller;

import com.android.workoo.service.TaskerService;
import com.android.workoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    UserService userService;

    @Autowired
    TaskerService taskerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //User Registration
    @PostMapping("/user/register")
    public ResponseEntity registerNewUser(@RequestParam("userName")String userName,
                                          @RequestParam("phoneNumber") BigInteger phoneNumber,
                                          @RequestParam(value = "img",required = false)byte[] img,
                                          @RequestParam("password")String password){
        try{

            System.out.println(userName +" - "+ phoneNumber +" - "+password);

            if(userName.isEmpty() || phoneNumber.toString().isEmpty() || password.isEmpty()){
                return new ResponseEntity<>("Please Complete all Fields", HttpStatus.BAD_REQUEST);
            }
            List<BigInteger> UserPhone = userService.checkUser(phoneNumber);
            if(!UserPhone.isEmpty()){
                return new ResponseEntity<>("User Already Exists",HttpStatus.FOUND);
            }
            //Encrypt the password
            String hashed_password = passwordEncoder.encode(password);

            //Register new user
            int result = userService.registerNewUser(userName,phoneNumber, Optional.ofNullable(img),hashed_password);
            if (result != 1){
                return new ResponseEntity<>("Failed to Register user", HttpStatus.BAD_REQUEST);
            }

        }catch (DataIntegrityViolationException e) {
            // This is optional, as the global handler should handle this, but you can add extra handling here.
            return new ResponseEntity<>("User with this phone number already exists", HttpStatus.CONFLICT);
        }
        System.out.println("Registered");
        return new ResponseEntity<>("User Registered Successfully", HttpStatus.FOUND);
    }

    //Tasker Registration
    @PostMapping("/tasker/register")
    public ResponseEntity registerNewTasker(@RequestParam("tasker_name")String tasker_name,
                                            @RequestParam("phone_number")BigInteger phone_number,
                                            @RequestParam("password")String password,
                                            @RequestParam(value="img",required = false)byte[] img,
                                            @RequestParam("skill")String skill,
                                            @RequestParam("description")String description,
                                            @RequestParam(value = "fair",required = false)Long fair,
                                            @RequestParam("location")String location,
                                            @RequestParam(value = "rating",required = false) BigDecimal rating,
                                            @RequestParam(value = "review",required = false)String review,
                                            @RequestParam(value = "total_project",required = false)Long total_project){
        if(tasker_name.isEmpty() || phone_number.toString().isEmpty() || location.isEmpty() || password.isEmpty() || skill.isEmpty() || description.isEmpty()){
            return new ResponseEntity<>("Please Complete all Fields", HttpStatus.BAD_REQUEST);
        }
        System.out.println(tasker_name+" - "+phone_number+" - "+location+" - "+password+" - "+passwordEncoder+" - "+skill+" - "+description );
        //Encrypt the password
        String hashed_password = passwordEncoder.encode(password);

        //Register new user
        int result = taskerService.registerNewTasker(tasker_name,
                phone_number,
                hashed_password,
                Optional.ofNullable(img),
                skill,
                description,
                Optional.ofNullable(fair),
                location,
                Optional.ofNullable(rating),
                Optional.ofNullable(review),
                Optional.ofNullable(total_project));
        if (result != 1){
            return new ResponseEntity<>("Failed to Register Tasker", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Tasker Registered Successfully", HttpStatus.FOUND);
    }
}
