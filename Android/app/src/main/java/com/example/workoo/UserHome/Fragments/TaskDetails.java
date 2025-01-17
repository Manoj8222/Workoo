package com.example.workoo.UserHome.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workoo.R;

public class TaskDetails extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_task_details, container, false);

        Button ReviewBtn = v.findViewById(R.id.ReviewTaskerBtn);
        ImageView taskerImage = v.findViewById(R.id.taskerProfile);
        TextView taskerDetailsName = v.findViewById(R.id.taskerDetailsName);
//        TODO
//        taskerImage.setImageResource(getArguments().getByte("TASKERIMAGE"));
        Long taskerId = getArguments().getLong("TASKERID");
        byte [] image= getArguments().getByteArray("TASKERRIMAGE");
        String name = getArguments().getString("TASKERNAME");
        taskerDetailsName.setText(name);
        String Date = getArguments().getString("DATE");
        String Time = getArguments().getString("TIME");
        String Fair = getArguments().getString("FAIR");
        EditText messageForTasker = v.findViewById(R.id.messageForTasker);
        ReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReviewAndConfirm taskDetails = new ReviewAndConfirm();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                Bundle args = new Bundle();
                args.putLong("TASKERID",taskerId);
                args.putString("TASKERNAME",name);
                args.putByteArray("TASKERIMAGE",image);
                args.putString("DATE", Date);
                args.putString("TIME",Time);
                args.putString("FAIR",Fair);
                taskDetails.setArguments(args);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                // Replace HomeFragment with SearchFragment
                transaction.replace(R.id.taskDetailFragment, taskDetails);
                transaction.addToBackStack(null); // Add transaction to backstack for navigation
                transaction.commit();
            }
        });

        return v;
    }
}