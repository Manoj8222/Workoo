package com.example.workoo.UserHome.Fragments.TaskFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.workoo.MainActivity;
import com.example.workoo.R;

public class RateAndFeedback extends Fragment {
    float rating = 0.0f;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_rate_and_feedback, container, false);
        Button submitBtn = v.findViewById(R.id.submitBtn);

        RatingBar ratingBar = v.findViewById(R.id.ratingBar);


        EditText feedback = v.findViewById(R.id.feedback);
        String feedBackText = String.valueOf(feedback.getText());

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBar.setRating(rating);
            }
        });

        assert getArguments() != null;
        Long taskerId = getArguments().getLong("TASKERID");
        Long bookingId = getArguments().getLong("BOOKINGID");
        Long fair = getArguments().getLong("FAIR");

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                TipsPage tipsPage = new TipsPage();
                Bundle args = new Bundle();
                args.putLong("TASKERID",taskerId);
                args.putLong("BOOKINGID",bookingId);
                args.putLong("FAIR",fair);
                args.putString("FEEDBACK", String.valueOf(feedback.getText()));
                args.putLong("RATING", (long) ratingBar.getRating());
                tipsPage.setArguments(args);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.add(R.id.task_container,tipsPage);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        // Inflate the layout for this fragment
        return v;
    }
}