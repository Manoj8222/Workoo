package com.android.workoo.service;

import com.android.workoo.dao.UserRepository;
import com.android.workoo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

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

}
