package com.android.workoo.service;

import com.android.workoo.entity.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
@Service
public interface UserService {
    int registerNewUser(String userName, BigInteger phoneNumber, Optional<byte[]> img, String password);

    //Check User Phone
    public List<BigInteger> checkUser(BigInteger phone_number);

    //check user password
    String checkPassword(BigInteger phone_number);
    public User getUserDetailsByPhone(BigInteger phone_number);
}
