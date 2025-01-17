package com.example.workoo.UserHome.Fragments.TaskFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.workoo.Adapter.TaskCompletedAdapter;
import com.example.workoo.Interface.RecyclerViewInterface;
import com.example.workoo.MainActivity;
import com.example.workoo.R;
import com.example.workoo.Retrofit.ServiceApi;
import com.example.workoo.UserHome.Fragments.TaskerProfile;
import com.example.workoo.model.BookingHistoryResponseDTO;
import com.example.workoo.model.TaskCompleteModel;
import com.example.workoo.model.TaskerDetailsModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CompletedFragment extends Fragment implements RecyclerViewInterface {
    ArrayList<TaskCompleteModel> taskCompleteList = new ArrayList<>();
    TaskCompletedAdapter completedAdapter;
    ServiceApi serviceApi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        serviceApi = MainActivity.retrofitService.getRetrofit("/").create(ServiceApi.class);
        View v = inflater.inflate(R.layout.fragment_completed, container, false);
        RecyclerView completedRv = v.findViewById(R.id.completedRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false);
        completedRv.setLayoutManager(layoutManager);
        completedRv.setItemAnimator(new DefaultItemAnimator());
        completedAdapter = new TaskCompletedAdapter(taskCompleteList,getContext(),this);
        completedRv.setAdapter(completedAdapter);

        fetchCompletedTasks(MainActivity.userId);

        // Inflate the layout for this fragment
        return v;
    }

    private void fetchCompletedTasks(Long userId) {
        ServiceApi serviceApi = MainActivity.retrofitService.getRetrofit("/").create(ServiceApi.class);
        Call<List<BookingHistoryResponseDTO>> call = serviceApi.getCompletedTasks(userId);

        call.enqueue(new Callback<List<BookingHistoryResponseDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<BookingHistoryResponseDTO>> call, @NonNull Response<List<BookingHistoryResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<BookingHistoryResponseDTO> tasks = response.body();
                    for (BookingHistoryResponseDTO task : tasks) {
                        taskCompleteList.add(new TaskCompleteModel(
                                task.getTaskerId().intValue(),
                                task.getTaskerName(),
                                task.getBookingDate(),
                                task.getBookingTime(),
                                R.drawable.profile_one // Replace with image logic if needed
                        ));
                    }
                    completedAdapter.notifyDataSetChanged();
                } else {
                    Log.e("CompletedFragment", "onResponse: "+response );
                    Toast.makeText(requireContext(), "No completed tasks found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<BookingHistoryResponseDTO>> call, @NonNull Throwable t) {
                Toast.makeText(requireContext(), "Failed to fetch completed tasks: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        int taskerId = taskCompleteList.get(position).getTaskerId();
        openTaskerProfile(taskerId);
    }

    private void openTaskerProfile(int taskerId) {
        Call<TaskerDetailsModel> call = serviceApi.getTaskerDetails((long) taskerId);
        call.enqueue(new Callback<TaskerDetailsModel>() {

            @Override
            public void onResponse(Call<TaskerDetailsModel> call, Response<TaskerDetailsModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    TaskerDetailsModel tasker = response.body();
                    TaskerProfile taskerProfile = new TaskerProfile();
                    Bundle args = new Bundle();
                    args.putString("TASKER_NAME", tasker.getTaskerName());
                    args.putString("TASKER_SKILL", tasker.getSkill());
                    args.putString("TASKER_LOCATION", tasker.getLocation());
                    args.putDouble("TASKER_RATING", tasker.getRating() != null ? tasker.getRating() : 0.0d);
                    args.putString("TASKER_TOTAL_PROJECTS", String.valueOf(tasker.getTotalProject() != null ? tasker.getTotalProject() : 0));
                    args.putString("TASKER_DESCRIPTION", tasker.getDescription());
                    args.putString("TASKER_REVIEWS", tasker.getReview());
                    args.putString("TASKER_FAIR", String.valueOf(tasker.getFair() != null ? tasker.getFair() : "100"));
                    args.putByteArray("TASKER_IMAGE", tasker.getImg());
                    args.putLong("TASKER_ID", tasker.getId());


                    taskerProfile.setArguments(args);


                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    // Replace HomeFragment with SearchFragment
                    transaction.add(R.id.task_container, taskerProfile);
                    transaction.addToBackStack(null); // Add transaction to backstack for navigation
                    transaction.commit();
                }else {
                    Toast.makeText(getContext(), "Can't get tasker details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TaskerDetailsModel> call, Throwable t) {
                // Handle error
                Toast.makeText(requireContext(), "Failed to load tasker details", Toast.LENGTH_SHORT).show();
            }
        });
    }

}