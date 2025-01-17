package com.example.workoo.UserHome.Fragments.TaskFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workoo.R;

public class TaskInfo extends Fragment {
    TextView taskerName,taskerDesc,taskerFair,totalFair,date,time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_task_info, container, false);
        Button finishTask = v.findViewById(R.id.finishTask);

        taskerName = v.findViewById(R.id.tasker_name);
        taskerDesc = v.findViewById(R.id.taskerDesc);
        taskerFair = v.findViewById(R.id.taskerFair);
        totalFair = v.findViewById(R.id.totalFair);
        date = v.findViewById(R.id.selectedDate);
        time = v.findViewById(R.id.selectedTime);

        taskerName.setText("You’ve booked "+getArguments().getString("NAME"));
        taskerDesc.setText(getArguments().getString("NAME")+" is currently offline and will reach out once available in the...");
        Long taskerId = getArguments().getLong("TASKERID");
        Long bookingId = getArguments().getLong("BOOKINGID");
        int fair = Math.toIntExact(getArguments().getLong("FAIR"));
        taskerFair.setText(String.valueOf(fair));
        totalFair.setText(String.valueOf(fair+10));
        date.setText(getArguments().getString("DATE"));
        time.setText(getArguments().getString("TIME"));

        finishTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RateAndFeedback rateAndFeedback = new RateAndFeedback();
                Bundle args = new Bundle();
                args.putLong("TASKERID",taskerId);
                args.putLong("BOOKINGID",bookingId);
                args.putLong("FAIR",getArguments().getLong("FAIR"));
                rateAndFeedback.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.add(R.id.task_container,rateAndFeedback);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        // Inflate the layout for this fragment
        return v;
    }
}