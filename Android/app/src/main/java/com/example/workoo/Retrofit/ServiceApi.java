package com.example.workoo.Retrofit;

import com.example.workoo.model.BookingHistoryResponseDTO;
import com.example.workoo.model.BookingResponse;
import com.example.workoo.model.LoginTasker;
import com.example.workoo.model.LoginUser;
import com.example.workoo.model.Review;
import com.example.workoo.model.ReviewRequestDTO;
import com.example.workoo.model.ReviewResponse;
import com.example.workoo.model.ReviewResponseDTO;
import com.example.workoo.model.ScheduledTaskResponse;
import com.example.workoo.model.Tasker;
import com.example.workoo.model.TaskerDTO;
import com.example.workoo.model.TaskerDetailsModel;
import com.example.workoo.model.TaskerSearchResponse;
import com.example.workoo.model.User;
import com.example.workoo.model.UserResponse;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
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

    @GET("/user/getrec")
    Call<List<Tasker>> getRecommendation();

    @GET("/api/test")
    Call<List<String>> test();

//    @GET("/api/reviews/{taskerId}")
//    Call<List<Review>> getReviewsByTaskerId(@Path("taskerId") Long taskerId);
    @GET("/api/reviews/{taskerId}")
    Call<List<ReviewResponse>> getReviewsByTaskerId(@Path("taskerId") Long taskerId);

    @GET("/api/users/getUserId")
    Call<Long> getUserId(@Query("phone_number") String phoneNumber);

    @POST("/api/bookings/create")
    Call<BookingResponse> createBooking(
            @Query("userId") Long userId,
            @Query("taskerId") Long taskerId,
            @Query("bookingDate") String bookingDate,
            @Query("bookingTime") String bookingTime,
            @Query("fair") Long fair
    );
    @GET("/api/bookings/{userId}/scheduled")
    Call<List<ScheduledTaskResponse>> getScheduledTasks(@Path("userId") Long userId);

    // POST API for creating a review
//    @POST("/api/reviews/post")
//    Call<ReviewResponseDTO> createReview(@Query("userId") Long userId,
//                                         @Query("taskerId") Long taskerId,
//                                         @Query("reviewDescription") String reviewDescription,
//                                         @Query("rating") float rating);

    @POST("/api/reviews/post")
    Call<ReviewResponseDTO> createReview(@Query("userId") Long userId,
                                         @Query("taskerId") Long taskerId,
                                         @Query("reviewDescription") String reviewDescription,
                                         @Query("rating") float rating);

    // PUT API for marking a booking as complete
    @PUT("/api/bookings/{bookingId}/complete")
    Call<ResponseBody> completeTask(@Path("bookingId") Long bookingId);

    @GET("/api/bookings/{userId}/completed")
    Call<List<BookingHistoryResponseDTO>> getCompletedTasks(@Path("userId") Long userId);

    @GET("/api/taskers/favorite")
    Call<List<TaskerDTO>> getFavoriteTaskers(@Query("userId") Long userId);

    @GET("/api/taskers/{taskerId}")
    Call<TaskerDetailsModel> getTaskerDetails(@Path("taskerId") Long taskerId);

    @GET("/api/taskers/search")
    Call<List<TaskerSearchResponse>> searchTaskers(@Query("skill") String skill);

    @POST("/api/taskers/{taskerId}/favorite")
    Call<Void> addToFavorites(@Path("taskerId") Long taskerId, @Query("userId") Long userId);

    @DELETE("/api/taskers/{taskerId}/favorite")
    Call<Void> removeFromFavorites(@Path("taskerId") Long taskerId, @Query("userId") Long userId);

    @GET("/api/taskers/{taskerId}/favorite/check")
    Call<Boolean> checkFavoriteStatus(@Path("taskerId") Long taskerId, @Query("userId") Long userId);
    @GET("/api/users/{id}")
    Call<UserResponse> getUserDetails(@Path("id") Long id);
}
