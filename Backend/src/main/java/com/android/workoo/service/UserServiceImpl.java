package com.android.workoo.service;

import com.android.workoo.dao.UserRepository;
import com.android.workoo.entity.Tasker;
import com.android.workoo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int registerNewUser(String userName, BigInteger phoneNumber, Optional<byte[]> img,String password) {
        return userRepository.registerNewUser(userName,phoneNumber,img.orElse(null),password);
    }

    //check user with phone number
    @Override
    public List<BigInteger> checkUser(BigInteger phone_number) {
        return userRepository.checkUserPhone(phone_number);
    }
    //check user password
    @Override
    public String checkPassword(BigInteger phone_number) {
        return userRepository.checkUserPassword(phone_number);
    }

    //get user details by phone number
    @Override
    public User getUserDetailsByPhone(BigInteger phone_number){
        return userRepository.getUserDetailsByPhone(phone_number);
    }

    @Override
    public List<Tasker> getRecommendation() {
        return userRepository.getRecommendation();
    }

    @Override
    public List<String> getRandomSkills() {
        return userRepository.getRandomSkills();
    }


    @Override
    public User getUserDetails(Long id) {
        return userRepository.findById(BigInteger.valueOf(id)).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void updateUserName(Long id, String newName) {
        User user = userRepository.findById(BigInteger.valueOf(id)).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUserName(newName);
        userRepository.save(user);
    }

    @Override
    public void updateUserPassword(Long id, String newPassword) {
        User user = userRepository.findById(BigInteger.valueOf(id))
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        if(verifyPassword(newPassword,passwordEncoder.encode(newPassword))){
            userRepository.save(user);
        }

    }

    @Override
    public Long getUserIdByPhone(BigInteger phone) {
        User u = userRepository.getUserDetailsByPhone(phone);
        return u.getId();
    }

    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
