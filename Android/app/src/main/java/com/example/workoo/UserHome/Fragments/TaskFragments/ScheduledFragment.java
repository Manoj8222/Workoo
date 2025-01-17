package com.example.workoo.UserHome.Fragments.TaskFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.workoo.Adapter.MyTaskerFavoriteAdapter;
import com.example.workoo.Adapter.ScheduledCardAdapter;
import com.example.workoo.Interface.RecyclerViewInterface;
import com.example.workoo.MainActivity;
import com.example.workoo.R;
import com.example.workoo.Retrofit.ServiceApi;
import com.example.workoo.UserHome.Fragments.ReferFriend;
import com.example.workoo.UserHome.Fragments.TaskerProfile;
import com.example.workoo.model.Scheduled;
import com.example.workoo.model.ScheduledTaskResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScheduledFragment extends Fragment implements RecyclerViewInterface {
    ScheduledCardAdapter adapter;
    ServiceApi serviceApi;
    ArrayList<Scheduled> list = new ArrayList<>();
    List<ScheduledTaskResponse> tasks;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_scheduled, container, false);

         serviceApi = MainActivity.retrofitService.getRetrofit("/api/bookings/").create(ServiceApi.class);
        RecyclerView rv = v.findViewById(R.id.scheduledList);

//
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new ScheduledCardAdapter(getContext(),list,this);
        rv.setAdapter(adapter);

        fetchScheduledTasks(MainActivity.userId);


        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onItemClick(int position) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        TaskInfo taskInfo = new TaskInfo();
        Bundle args = new Bundle();
        args.putLong("BOOKINGID",tasks.get(position).getBookingId());
        args.putString("NAME",list.get(position).getTaskerName().split(" ")[0]);
        args.putString("DATE",tasks.get(position).getBookingDate());
        args.putString("TIME",tasks.get(position).getBookingTime());
        args.putLong("FAIR",tasks.get(position).getFair());
        args.putLong("TASKERID",tasks.get(position).getTaskerId());
        taskInfo.setArguments(args);

        fragmentTransaction.add(R.id.task_container,taskInfo);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void fetchScheduledTasks(Long userId) {
        serviceApi.getScheduledTasks(userId).enqueue(new Callback<List<ScheduledTaskResponse>>() {
            @Override
            public void onResponse(retrofit2.Call<List<ScheduledTaskResponse>> call, Response<List<ScheduledTaskResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tasks = response.body();

                    // Save the response locally
                    saveScheduledTasks(getContext(), tasks);

                    // Map API response to ScheduledModel
                    for (ScheduledTaskResponse task : tasks) {
                        Scheduled model = new Scheduled(
                                R.drawable.profile_one, // Replace with task.getTaskerImage() if available
                                task.getTaskerName(),
                                task.getTaskerRating() != null ? task.getTaskerRating() : 0.0f,
                                Math.toIntExact(task.getTaskerTotalProjects() != null ? task.getTaskerTotalProjects() : 0),
                                Math.toIntExact(task.getTaskerTotalProjects() != null ? task.getTaskerTotalProjects() : 0)
                        );
                        list.add(model);
                    }

                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Failed to fetch tasks.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ScheduledTaskResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void saveScheduledTasks(Context context, List<ScheduledTaskResponse> tasks) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ScheduledTasks", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(tasks); // Convert list to JSON string
        editor.putString("tasks", json);
        editor.apply();
    }

}