package com.android.workoo.controller;

import com.android.workoo.dao.UserRepository;
import com.android.workoo.entity.Tasker;
import com.android.workoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserHomeController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/getrec")
    public List<Tasker> getRecommendation(){
        System.out.println("Get Recommendation called ------------ ");
        List<Tasker> list;
        list = userService.getRecommendation();
        return list;
    }

    @GetMapping("/getskill")
    public List<String> getRandomSkill(){
        System.out.println("");
        return userService.getRandomSkills();
    }
}
