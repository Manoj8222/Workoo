package com.android.workoo.dao;

import com.android.workoo.entity.Tasker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;

@Repository
public interface TaskerRepository extends JpaRepository<Tasker,Long> {

    @Query(value = "SELECT phone_number FROM tasker WHERE phone_number = :phone_number",nativeQuery = true)
    Long checkTaskerPhone(@Param("phone_number") BigInteger phone_number);

    @Query(value = "SELECT password FROM tasker WHERE phone_number = :phone_number",nativeQuery = true)
    String checkTaskerPassword(@Param("phone_number")BigInteger phone_number);

    @Query(value = "SELECT * FROM tasker WHERE phone_number = :phone_number",nativeQuery = true)
    Tasker getTaskerDetailsByPhone(@Param("phone_number")BigInteger phone_number);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO tasker(tasker_name,phone_number,password,img,skill,description,fair,location,rating,review,total_project) VALUES (:tasker_name, :phone_number, :password, :img, :skill, :description, :fair, :location, :rating, :review, :total_project)",nativeQuery = true)
    int registerNewTasker(@Param("tasker_name")String tasker_name,
                          @Param("phone_number")BigInteger phone_number,
                          @Param("password")String password,
                          @Param("img")byte[] img,
                          @Param("skill")String skill,
                          @Param("description")String description,
                          @Param("fair")Long fair,
                          @Param("location")String location,
                          @Param("rating") BigDecimal rating,
                          @Param("review")String review,
                          @Param("total_project")Long total_project);
}
