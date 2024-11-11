package com.android.workoo.service;

import com.android.workoo.entity.Tasker;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

@Service
public interface TaskerService {

    //Check Tasker Phone
    public Long checkTasker(BigInteger phone_number);

    //check Tasker password
    String checkPassword(BigInteger phone_number);

    public Tasker getTaskerDetailsByPhone(BigInteger phone_number);

    int registerNewTasker(String taskerName, BigInteger phoneNumber, String hashedPassword, Optional<byte[]> img, String skill, String description, Optional<Long> fair, String location, Optional<BigDecimal> rating, Optional<String> review, Optional<Long> totalProject);
}

