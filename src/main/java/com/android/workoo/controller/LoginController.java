package com.android.workoo.controller;

import com.android.workoo.entity.Tasker;
import com.android.workoo.entity.User;
import com.android.workoo.model.LoginTasker;
import com.android.workoo.model.LoginUser;
import com.android.workoo.service.TaskerService;
import com.android.workoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class LoginController {
    List<String> stringList = Arrays.asList("Hello", "World");

    @Autowired
    UserService userService;
    @Autowired
    TaskerService taskerService;

    //User Login
    @PostMapping("/user/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginUser loginUser){

        //Get user Phone
        List<BigInteger> userPhone = userService.checkUser(loginUser.getPhone_number());
        System.out.println(loginUser+"-------------------------------------------------------------");
        System.out.println(userPhone+"-------------------------------------------------------------");
        System.out.println(loginUser.getPassword()+"-------------------------------------------------------------");
        //check if email is empty
        if(userPhone.isEmpty()){
            return new ResponseEntity<>("Phone Number does not exist", HttpStatus.NOT_FOUND);
        }

        //Get hashed user password
        String hashed_password = userService.checkPassword(loginUser.getPhone_number());

//        //Validate User password
        if(!BCrypt.checkpw(loginUser.getPassword(),hashed_password)){
            return new ResponseEntity<>("Incorrect Password", HttpStatus.BAD_REQUEST);
        }

        //Set User Object
        User user1 = userService.getUserDetailsByPhone(loginUser.getPhone_number());

        return new ResponseEntity<>(user1,HttpStatus.OK);
    }

    //Tasker Login
    @PostMapping("/tasker/login")
    public ResponseEntity<?> authenticateTasker(@RequestBody LoginTasker loginTasker){
        //Get Tasker Phone
        Long taskerPhone = taskerService.checkTasker(loginTasker.getPhone_number());
        System.out.println(loginTasker+"-------------------------------------------------------------");
        System.out.println(taskerPhone+"-------------------------------------------------------------");
        System.out.println(loginTasker.getPassword()+"-------------------------------------------------------------");
        //check if email is empty
        if(taskerPhone == null){
            return new ResponseEntity<>("Phone Number does not exist", HttpStatus.NOT_FOUND);
        }

        //Get hashed Tasker Password
        String hashed_password = taskerService.checkPassword(loginTasker.getPhone_number());

        //Validate tasker password
        if(!BCrypt.checkpw(loginTasker.getPassword(),hashed_password)){
            return new ResponseEntity<>("Incorrect Password", HttpStatus.BAD_REQUEST);
        }
        Tasker tasker1 = taskerService.getTaskerDetailsByPhone(loginTasker.getPhone_number());
        return  new ResponseEntity<>(tasker1,HttpStatus.OK);

    }

    @GetMapping("/test")
    public List<String> test(){
        System.out.println("called");
        return stringList;
    }
}
