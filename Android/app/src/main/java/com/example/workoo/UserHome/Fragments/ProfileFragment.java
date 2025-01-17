package com.example.workoo.UserHome.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workoo.MainActivity;
import com.example.workoo.R;
import com.example.workoo.Retrofit.ServiceApi;
import com.example.workoo.SessionManagement.SessionClass;
import com.example.workoo.model.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    ServiceApi serviceApi;
    Long userId ;
    TextView logOutBtn, UserName,userPhone;
    ConstraintLayout changePassword;
    ImageView profile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serviceApi = MainActivity.retrofitService.getRetrofit("/").create(ServiceApi.class);
        userId = MainActivity.userId;
        loadUserDetails();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        logOutBtn = v.findViewById(R.id.logOutBtn);
        changePassword = v.findViewById(R.id.changePassword);
        UserName = v.findViewById(R.id.UserName);
        userPhone = v.findViewById(R.id.UserPhone);
        profile = v.findViewById(R.id.profileImage);

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionClass.clearSession();
                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);
            }
        });

        return v;
    }

    private void loadUserDetails() {
        serviceApi.getUserDetails(userId).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserResponse user = response.body();
                    updateUI(user);
                } else {
                    Toast.makeText(requireContext(), "Failed to load profile", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(UserResponse user) {
        UserName.setText(user.getUserName());
        userPhone.setText(String.valueOf(user.getPhoneNumber()));
//        location.setText(user.getLocation());

//        if (user.getImg() != null) {
//            Glide.with(this)
//                    .load(user.getImg())
//                    .placeholder(R.drawable.default_profile)
//                    .error(R.drawable.default_profile)
//                    .circleCrop()
//                    .into(profileImage);
//        }
    }
}