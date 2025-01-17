package com.example.workoo.UserHome.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workoo.MainActivity;
import com.example.workoo.R;
import com.example.workoo.Retrofit.ServiceApi;
import com.example.workoo.model.BookingResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewAndConfirm extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_review_and_confirm, container, false);

        ServiceApi serviceApi = MainActivity.retrofitService.getRetrofit("/api/bookings/create/").create(ServiceApi.class);

        Button confirmBookingBtn = v.findViewById(R.id.ConfirmBookingBtn);
        TextView ConfirmTaskerName = v.findViewById(R.id.ConfirmTaskerName);
        TextView BookingDate = v.findViewById(R.id.booking_date);
        TextView BookingTime = v.findViewById(R.id.booking_time);
        ImageView taskerImg = v.findViewById(R.id.taskerImg);
        TextView taskerFair = v.findViewById(R.id.taskerFair);
        TextView taskertotalFair = v.findViewById(R.id.taskertotalFair);

        Long taskerId = getArguments().getLong("TASKERID");
        byte [] image= getArguments().getByteArray("TASKERRIMAGE");
        String name = getArguments().getString("TASKERNAME");
        String Date = getArguments().getString("DATE");
        String Time = getArguments().getString("TIME");
        String Fair = getArguments().getString("FAIR");


        ConfirmTaskerName.setText(name);
        BookingDate.setText(Date);
        BookingTime.setText(Time);
//        taskerImg.setImageResource(image);
        taskerFair.setText(Fair);
        int tot = Integer.parseInt(Fair)+10;
        taskertotalFair.setText(String.valueOf(tot));

        confirmBookingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View v1=LayoutInflater.from(getContext()).inflate(R.layout.confirm_popup_window, null, false);
                final PopupWindow pw = new PopupWindow(v1,ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT, true);
                pw.showAtLocation(requireView().findViewById(R.id.reviewconfirmlayout), Gravity.CENTER, 0, 0);
                Button bookNow = v1.findViewById(R.id.bookNowBtn);

                Call<BookingResponse> call = serviceApi.createBooking(MainActivity.userId, taskerId, Date, Time, Long.valueOf(Fair));




                bookNow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        call.enqueue(new Callback<BookingResponse>() {
                            @Override
                            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    BookingResponse bookingResponse = response.body();
                                    Toast.makeText(getContext(), "Booking Created", Toast.LENGTH_SHORT).show();
                                    // Log or use the response
                                    Log.d("API Response", "Booking ID: " + bookingResponse.getId());
                                    Log.d("API Response", "Task Completed: " + bookingResponse.getTaskCompleted());
                                } else {
                                    Toast.makeText(getContext(), "Not Created", Toast.LENGTH_SHORT).show();
                                    Log.e("API Response", "Failed to create booking: " + response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<BookingResponse> call, Throwable t) {
                                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                                Log.e("API Response", "API call failed: " + t.getMessage());
                            }
                        });
                        pw.dismiss();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.replace(R.id.frameLayout,new TaskFragment());
                        fragmentTransaction.commit();
                    }
                });

            }
        });
        return v;
    }
}