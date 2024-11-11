package com.android.workoo.dao;

import com.android.workoo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

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

}
