package com.example.workoo.UserHome.Fragments.TaskFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.workoo.MainActivity;
import com.example.workoo.R;
import com.example.workoo.Retrofit.ServiceApi;
import com.example.workoo.UserHome.Fragments.HomeFragment;
import com.example.workoo.UserHome.UserHome;
import com.example.workoo.model.ReviewRequestDTO;
import com.example.workoo.model.ReviewResponseDTO;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TipsPage extends Fragment {
    ServiceApi apiService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tips_page, container, false);

        apiService = MainActivity.retrofitService.getRetrofit("/").create(ServiceApi.class);

        RadioGroup radioGroup = v.findViewById(R.id.tip_options);
        Button applyButton = v.findViewById(R.id.applyBtn);

        Long taskerId = getArguments().getLong("TASKERID");
        Long bookingId = getArguments().getLong("BOOKINGID");
        Long userId = MainActivity.userId;
        Long fair = getArguments().getLong("FAIR");
        String feedBack = getArguments().getString("FEEDBACK");
        float rating = getArguments().getLong("RATING");
        applyButton.setText("Apply to total ₹" + (fair));
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int selectedTip = 0;

            // Use if-else statements to handle the selection
            if (checkedId == R.id.no_tips) {
                selectedTip = 0;
            } else if (checkedId == R.id.tip_10) {
                selectedTip = 10;
            } else if (checkedId == R.id.tip_20) {
                selectedTip = 20;
            } else if (checkedId == R.id.tip_30) {
                selectedTip = 30;
            } else if (checkedId == R.id.tip_40) {
                selectedTip = 40;
            }

            // Update the button text
            String totalText = "Apply to total ₹" + (fair + selectedTip);
            applyButton.setText(totalText);
        });


        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                Intent i = new Intent(getContext(), UserHome.class);
                startActivity(i);
                postReview(userId, taskerId, feedBack, rating);
                markTaskAsComplete(bookingId);
            }
        });

        return v;
    }

    private void postReview(Long userId, Long taskerId, String reviewDescription, float rating) {
        ReviewRequestDTO reviewRequestDTO = new ReviewRequestDTO(userId, taskerId, reviewDescription, rating);

        apiService.createReview(userId, taskerId, reviewDescription, rating).enqueue(new Callback<ReviewResponseDTO>() {
            @Override
            public void onResponse(Call<ReviewResponseDTO> call, Response<ReviewResponseDTO> response) {
                if (response.isSuccessful()) {
                    ReviewResponseDTO review = response.body();
                    Toast.makeText(getContext(), "Review submitted successfully!", Toast.LENGTH_LONG).show();
                } else {
                    Log.e("REVIEWRESPONSE", "onResponse: "+response.body() );
                    Toast.makeText(getContext(), "Failed to submit review: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ReviewResponseDTO> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void markTaskAsComplete(Long bookingId) {
        apiService.completeTask(bookingId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Task marked as completed successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to complete task: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}