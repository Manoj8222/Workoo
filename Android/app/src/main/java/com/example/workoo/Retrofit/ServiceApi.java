package com.example.workoo.Retrofit;

import com.example.workoo.model.LoginTasker;
import com.example.workoo.model.LoginUser;
import com.example.workoo.model.Tasker;
import com.example.workoo.model.User;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {

    @POST("/api/user/login")
    Call<User> loginUser(@Body LoginUser loginUser);

    // Tasker Login
    @POST("/api/tasker/login")
    Call<Tasker> loginTasker(@Body LoginTasker loginTasker);

    // User Registration
    @POST("/api/user/register")
    Call<String> registerUser(@Query("userName") String userName,
                              @Query("phoneNumber") Long phoneNumber,
                              @Query("img") byte[] img,
                              @Query("password") String password);

    // Tasker Registration
    @POST("/api/tasker/register")
    Call<String> registerTasker(@Query("tasker_name") String taskerName,
                                @Query("phone_number") Long phoneNumber,
                                @Query("password") String password,
                                @Query("img") byte[] img,
                                @Query("skill") String skill,
                                @Query("description") String description,
                                @Query("fair") Long fair,
                                @Query("location") String location,
                                @Query("rating") BigDecimal rating,
                                @Query("review") String review,
                                @Query("total_project") Long totalProject);

    @GET("/api/test")
    Call<List<String>> test();
}
