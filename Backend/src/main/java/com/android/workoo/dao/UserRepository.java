package com.android.workoo.dao;

import com.android.workoo.entity.Tasker;
import com.android.workoo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,BigInteger> {

    @Query(value = "SELECT phone_number FROM user WHERE phone_number = :phone_number",nativeQuery = true)
    List<BigInteger> checkUserPhone(@Param("phone_number") BigInteger phone_number);

    @Query(value = "SELECT password FROM user WHERE phone_number = :phone_number",nativeQuery = true)
    String checkUserPassword(@Param("phone_number")BigInteger phone_number);

    @Query(value = "SELECT * FROM user WHERE phone_number = :phone_number",nativeQuery = true)
    User getUserDetailsByPhone(@Param("phone_number")BigInteger phone_number);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user(user_name, phone_number, img, password) VALUES (:user_name, :phone_number, COALESCE(:img, NULL), :password)",nativeQuery = true)
    int registerNewUser(@Param("user_name")String user_name,
                        @Param("phone_number")BigInteger phone_number,
                        @Param("img") byte[] img,
                        @Param("password")String password);

//    @Query(value = "SELECT * FROM tasker ORDER BY RANDOM() LIMIT 5", nativeQuery = true)
//    List<Tasker> getRecommendation();
// Using JPQL
//    @Query(value = "SELECT * FROM tasker ORDER BY RAND() LIMIT 5", nativeQuery = true)
    @Query(value = "SELECT t FROM Tasker t ORDER BY FUNCTION('RAND') LIMIT 5", nativeQuery = false)
    List<Tasker> getRecommendation();

    @Query(value = "SELECT DISTINCT t.skill FROM tasker t ORDER BY RAND() LIMIT 10", nativeQuery = true)
    List<String> getRandomSkills();

//    String getUserIdByPhoneNumber(@Param("phone")BigInteger phone);

//    Optional<User> findByPhoneNumber(String phoneNumber);
}
