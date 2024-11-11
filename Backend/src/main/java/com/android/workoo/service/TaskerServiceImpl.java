package com.android.workoo.service;

import com.android.workoo.dao.TaskerRepository;
import com.android.workoo.entity.Tasker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
@Service
public class TaskerServiceImpl implements TaskerService{
    @Autowired
    TaskerRepository taskerRepository;


    @Override
    public Long checkTasker(BigInteger phone_number) {
        return taskerRepository.checkTaskerPhone(phone_number);
    }

    @Override
    public String checkPassword(BigInteger phone_number) {
        return taskerRepository.checkTaskerPassword(phone_number);
    }

    @Override
    public Tasker getTaskerDetailsByPhone(BigInteger phone_number) {
        return taskerRepository.getTaskerDetailsByPhone(phone_number);
    }

    @Override
    public int registerNewTasker(String taskerName, BigInteger phoneNumber, String password, Optional<byte[]> img, String skill, String description, Optional<Long> fair, String location, Optional<BigDecimal> rating, Optional<String> review, Optional<Long> totalProject) {
        return taskerRepository.registerNewTasker(taskerName,phoneNumber,password, img.orElse(null),skill,description,fair.orElse(null),location,rating.orElse(null),review.orElse(null), totalProject.orElse(null) );
    }
}
