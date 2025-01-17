package com.example.workoo.UserHome.Fragments;

import android.os.Bundle;

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

import com.example.workoo.Adapter.MyTaskerFavoriteAdapter;
import com.example.workoo.Interface.RecyclerViewInterface;
import com.example.workoo.MainActivity;
import com.example.workoo.R;
import com.example.workoo.Retrofit.ServiceApi;
import com.example.workoo.model.FavoriteModel;
import com.example.workoo.model.TaskerDTO;
import com.example.workoo.model.TaskerDetailsModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyTaskerFragment extends Fragment implements RecyclerViewInterface {
    ArrayList<FavoriteModel> list = new ArrayList<>();
    MyTaskerFavoriteAdapter adapter;
    List<TaskerDTO> taskers;
    ServiceApi serviceApi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_tasker, container, false);
        RecyclerView rv = v.findViewById(R.id.favorite_RecyclerView);
        serviceApi = MainActivity.retrofitService.getRetrofit("/").create(ServiceApi.class);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());

        // Initialize adapter with empty list
        list = new ArrayList<>();
        adapter = new MyTaskerFavoriteAdapter(list, getContext(), this);
        rv.setAdapter(adapter);

        // Fetch favorite taskers
        getFavoriteTaskers();

        return v;
    }

    private void getFavoriteTaskers() {
        Long userId = MainActivity.userId; // Implement this to get logged in user ID

        Call<List<TaskerDTO>> call = serviceApi.getFavoriteTaskers(userId);
        call.enqueue(new Callback<List<TaskerDTO>>() {
            @Override
            public void onResponse(Call<List<TaskerDTO>> call, Response<List<TaskerDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    taskers = response.body();
                    list.clear();

                    for (TaskerDTO tasker : taskers) {
                        FavoriteModel model = new FavoriteModel(
                                tasker.getTaskerName(),
                                tasker.getRating() != null ? tasker.getRating() : 0f,
                                tasker.getTotalProject() != null ? tasker.getTotalProject() : 0,
                                0, // Set default value for totalReview
                                R.drawable.profile // Set default profile image
                        );
                        list.add(model);
                    }
                    adapter.notifyDataSetChanged();
                }else{
                    Log.e("FavoriteResponse", "onResponse: "+response );
                }
            }

            @Override
            public void onFailure(Call<List<TaskerDTO>> call, Throwable t) {
                Log.e("FavoriteResponse", "onResponse: "+t.getMessage() );
                // Handle error
                Toast.makeText(getContext(), "Failed To Fetch The Favorite Taskers", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onItemClick(int position) {
        Long taskerId = taskers.get(position).getId();
        openTaskerProfile(taskerId);
    }

    private void openTaskerProfile(Long taskerId) {
        Call<TaskerDetailsModel> call = serviceApi.getTaskerDetails(taskerId);
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



















//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_my_tasker, container, false);
//        RecyclerView rv = v.findViewById(R.id.favorite_RecyclerView);
//
//        list.add(new FavoriteModel("Manoj",4.5f,10,2,R.drawable.profile));
//        list.add(new FavoriteModel("Manoj",4.5f,9,2,R.drawable.profile));
//        list.add(new FavoriteModel("Manoj",4.5f,8,2,R.drawable.profile));
//        list.add(new FavoriteModel("Manoj",4.5f,7,2,R.drawable.profile));
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false);
//        rv.setLayoutManager(layoutManager);
//        rv.setItemAnimator(new DefaultItemAnimator());
//        adapter = new MyTaskerFavoriteAdapter(list,getContext());
//        rv.setAdapter(adapter);
//
//        return v;
//    }