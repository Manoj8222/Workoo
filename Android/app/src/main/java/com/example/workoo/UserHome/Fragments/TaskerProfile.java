package com.example.workoo.UserHome.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.workoo.Adapter.TaskerReviewAdapter;
import com.example.workoo.MainActivity;
import com.example.workoo.R;
import com.example.workoo.Retrofit.ServiceApi;
import com.example.workoo.model.ReviewResponse;
import com.example.workoo.model.TaskerReviewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TaskerProfile extends Fragment {
    ArrayList<TaskerReviewModel> list = new ArrayList<>();
    TaskerReviewAdapter adapter;
    TextView dateSelector,timeSelector;
    boolean dateSelected,timeSelected;
    Button selectDateTime;
    private ServiceApi serviceApi;
    private ArrayList<ReviewResponse> reviewsList = new ArrayList<>();
    private TextView noReview;
    private RecyclerView tasker_Review;
    ImageView noReviewImage;
    private Button hireMe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tasker_profile, container, false);

        tasker_Review = v.findViewById(R.id.tasker_Review);

         serviceApi = MainActivity.retrofitService.getRetrofit("/api/reviews/{taskerId}/").create(ServiceApi.class);

        ImageView taskerProfileImg = v.findViewById(R.id.taskerProfileImg);
         Button hireMe = v.findViewById(R.id.hireMe);

         TextView ViewAll = v.findViewById(R.id.ViewAll);
         taskerProfileImg.setImageResource(R.drawable.profile_ten);

        String taskerName = getArguments().getString("TASKER_NAME");
        String taskerSkill = getArguments().getString("TASKER_SKILL");
        String taskerLocation = getArguments().getString("TASKER_LOCATION");
        String taskerRating = String.valueOf(getArguments().getDouble("TASKER_RATING"));
        String taskerTotalProjects = getArguments().getString("TASKER_TOTAL_PROJECTS");
        String taskerDescription = getArguments().getString("TASKER_DESCRIPTION");
        String taskerReviews = getArguments().getString("TASKER_REVIEWS");
        String taskerFair = getArguments().getString("TASKER_FAIR");

        if(getArguments().getInt("PHOTO") != 0){
            taskerProfileImg.setImageResource(getArguments().getInt("PHOTO"));
        }

        TextView name = v.findViewById(R.id.profileTaskerName);
        TextView skill = v.findViewById(R.id.taskerProfileSkill);
        TextView location = v.findViewById(R.id.taskerProfileLocation);
        TextView rating = v.findViewById(R.id.taskerRating);
        TextView totalProject = v.findViewById(R.id.taskerTotalProject);
        TextView profileDescription = v.findViewById(R.id.taskerProfileDescription);
        TextView fair = v.findViewById(R.id.fair);
        RatingBar ratingBar = v.findViewById(R.id.rating);
        ratingBar.setRating((float) getArguments().getDouble("TASKER_RATING"));
        long taskerId = getArguments().getLong("TASKER_ID");

        ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchReviews(taskerId,false);
            }
        });

        hireMe.setText("Hire "+taskerName.split(" ")[0]);

        fetchReviews(taskerId,true);


        name.setText(taskerName);
        skill.setText(taskerSkill);
        location.setText(taskerLocation);
        rating.setText(taskerRating);
        totalProject.setText(taskerTotalProjects);
        profileDescription.setText(taskerDescription);
//        totalProject.setText(taskerReviews);
        fair.setText(taskerFair);

        // Retrieve and decode the image byte array
        byte[] imageBytes = getArguments().getByteArray("TASKER_IMAGE");
        if (imageBytes != null) {
            Bitmap taskerImageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            //TODO
            ImageView taskerImageView = v.findViewById(R.id.taskerProfileImg);
            taskerImageView.setImageBitmap(taskerImageBitmap);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false);
        tasker_Review.setLayoutManager(layoutManager);
        tasker_Review.setItemAnimator(new DefaultItemAnimator());
        adapter = new TaskerReviewAdapter(reviewsList,getContext());
        tasker_Review.setAdapter(adapter);

        // Clear focus to avoid keyboard staying open
        View currentFocus = requireActivity().getCurrentFocus();
        if (currentFocus != null) {
            currentFocus.clearFocus();
        }

        noReview = v.findViewById(R.id.noReview);
        noReviewImage = v.findViewById(R.id.noReviewImage);

        hireMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
                View view = LayoutInflater.from(getContext()).inflate(R.layout.tasker_bottom_sheet_dailog, null);
                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();

                // Expand the bottom sheet dialog
                bottomSheetDialog.setOnShowListener(dialog -> {
                    BottomSheetBehavior<View> behavior = BottomSheetBehavior.from((View) view.getParent());
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                });

                selectDateTime = view.findViewById(R.id.selectDateTime);
                dateSelector = view.findViewById(R.id.selectedDate);
                timeSelector = view.findViewById(R.id.selectedTime);

                dateSelector.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDateSelector();
                    }
                });

                timeSelector.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showTimeSelector();
                    }
                });
                selectDateTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       if(selectDateTime.getText() == "Continue"){

                           Bundle args = new Bundle();

                           args.putLong("TASKERID",taskerId);
                           args.putString("TASKERNAME",taskerName);
                           args.putByteArray("TASKERIMAGE",imageBytes);
                           args.putString("DATE", (String) dateSelector.getText());
                           args.putString("TIME",(String) timeSelector.getText());
                           args.putString("FAIR",taskerFair);
                           TaskDetails taskDetails = new TaskDetails();
                           taskDetails.setArguments(args);
                           FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                           transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                           // Replace HomeFragment with SearchFragment
                           transaction.replace(R.id.homeFragmentContainer, taskDetails);
                           transaction.addToBackStack(null); // Add transaction to backstack for navigation
                           transaction.commit();
                           bottomSheetDialog.hide();
                       }
                    }
                });

            }

        });

        return v;
    }
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = requireActivity().getCurrentFocus();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private  void showDateSelector(){

        Calendar mCalendar = Calendar.getInstance();
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int dayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getContext(),R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                DateTimeFormatter dayOfWeekFormatter
                        = DateTimeFormatter.ofPattern("EEEE", Locale.US);

                LocalDate date = LocalDate.of(
                        year,month+1,dayOfMonth);

                dateSelected = true;
                if(dateSelected && timeSelected){
                    selectDateTime.setText("Continue");
                }else if(timeSelected){
                    selectDateTime.setText("Select Date");
                } else if (dateSelected) {
                    selectDateTime.setText("Select Time");
                }

                dateSelector.setText(date.format(dayOfWeekFormatter)+"  -  "+String.valueOf(dayOfMonth)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year));
            }
        }, year, month, dayOfMonth);
        dialog.getDatePicker().setMinDate(mCalendar.getTimeInMillis());
        dialog.show();
    }
    private  void showTimeSelector(){
        Calendar mCalendar = Calendar.getInstance();
        int hourOfTheDay = mCalendar.HOUR;
        int minuteOfTheDay = mCalendar.MINUTE;

        TimePickerDialog dialog = new TimePickerDialog(getContext(),R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                Time time = new Time(hourOfDay, minute, 0);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
                String s = simpleDateFormat.format(time);
                timeSelector.setText(s);

                timeSelected = true;
                if(dateSelected && timeSelected){
                    selectDateTime.setText("Continue");
                }else if(timeSelected){
                    selectDateTime.setText("Select Date");
                } else if (dateSelected) {
                    selectDateTime.setText("Select Time");
                }
            }
        }, hourOfTheDay, minuteOfTheDay, false);

        dialog.show();
    }


    private void fetchReviews(long taskerId, Boolean restricted ) {

        Call<List<ReviewResponse>> call = serviceApi.getReviewsByTaskerId(taskerId);
        call.enqueue(new Callback<List<ReviewResponse>>() {
            @Override
            public void onResponse(Call<List<ReviewResponse>> call, Response<List<ReviewResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    reviewsList.clear();
//
                    if(restricted ){
                        for ( ReviewResponse r : response.body()) {
                            if(reviewsList.size() < 3){
                                reviewsList.add(r);
                            }else{
                                break;
                            }
                        }
                    }else {
                        reviewsList.addAll(response.body());
                    }

                    if(reviewsList.size() == 0){
                         noReview.setVisibility(View.VISIBLE);
                         tasker_Review.setVisibility(View.GONE);
                        noReviewImage.setVisibility(View.VISIBLE);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    noReview.setVisibility(View.VISIBLE);
                    tasker_Review.setVisibility(View.GONE);
                    noReviewImage.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<ReviewResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}