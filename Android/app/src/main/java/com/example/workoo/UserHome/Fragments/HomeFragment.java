package com.example.workoo.UserHome.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.workoo.Adapter.FeatureServiceButtonAdapter;
import com.example.workoo.Adapter.RecomendatioAdapter;
import com.example.workoo.Interface.RecyclerViewInterface;
import com.example.workoo.MainActivity;
import com.example.workoo.R;
import com.example.workoo.Retrofit.ServiceApi;
import com.example.workoo.model.RecomendationCard;
import com.example.workoo.model.Tasker;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements RecyclerViewInterface {
ArrayList<RecomendationCard> list = new ArrayList<>();
ArrayList<Tasker> taskerArrayList = new ArrayList<>();

 ArrayList<String> featureServices = new ArrayList<>(Arrays.asList("Dust-proof AC Cover","AC Cooling Problem","Kitchen Hood Repair", "House Shifting","Mobile Repair","House Cleaning","Tourist Bus Repair","Electrician"));
RecomendatioAdapter adapter;
FeatureServiceButtonAdapter featureServiceButtonAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View i = inflater.inflate(R.layout.fragment_home, container, false);
        ImageSlider imageSlider = i.findViewById(R.id.imageSlider);
        CardView Cleaning,Repairing,Painting,carWash;
        Cleaning = i.findViewById(R.id.cleaningService);
        Repairing = i.findViewById(R.id.Repairing);
        Painting = i.findViewById(R.id.Painting);
        carWash = i.findViewById(R.id.CarWash);
        
        Cleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchQuery("Cleaning");
                Toast.makeText(getContext(), "TOAST", Toast.LENGTH_SHORT).show();
            }
        });

        Repairing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchQuery("Repairing");
                Toast.makeText(getContext(), "TOAST", Toast.LENGTH_SHORT).show();
            }
        });

        Painting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchQuery("Painting");
                Toast.makeText(getContext(), "TOAST", Toast.LENGTH_SHORT).show();
            }
        });

        carWash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchQuery("Car Wash");
                Toast.makeText(getContext(), "TOAST", Toast.LENGTH_SHORT).show();
            }
        });
        

        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.carwash, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.electrician, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.mabile_repair, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels,ScaleTypes.CENTER_CROP);

        ServiceApi serviceApi = MainActivity.retrofitService.getRetrofit("/user/getrec/").create(ServiceApi.class);

        String phone = MainActivity.UserPhone;
        
        


        TextView viewAllServices = i.findViewById(R.id.viewAllServices);

        viewAllServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 AllServices allServices = new AllServices();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                // Replace HomeFragment with SearchFragment
                transaction.add(R.id.homeFragmentContainer, allServices);
                transaction.addToBackStack(null); // Add transaction to backstack for navigation
                transaction.commit();
            }
        });

//        String[] arr = {"Dust-proof AC Cover","AC Cooling Problem","Kitchen Hood Repair", "House Shifting","Washing Machine Repair","Washing","Tourist Bus Repair","Electrician"};


        RecyclerView rv = i.findViewById(R.id.feature_service_rv);
        LinearLayoutManager layoutManager1 = new GridLayoutManager(getContext(),2);
        rv.setLayoutManager(layoutManager1);
        rv.setItemAnimator(new DefaultItemAnimator());

        featureServiceButtonAdapter = new FeatureServiceButtonAdapter(featureServices,getContext());
        rv.setAdapter(featureServiceButtonAdapter);


        //search
        TextView searchView = i.findViewById(R.id.searchView);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchFragment();
            }
        });
        searchView.onWindowFocusChanged(true);
        searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                openSearchFragment();
            }
        });

        // Inflate the layout for this fragment
        return i;
    }
    private void openSearchFragment() {
        SearchFragment searchFragment = new SearchFragment();
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        // Replace HomeFragment with SearchFragment
        transaction.replace(R.id.homeFragmentContainer, searchFragment);
        transaction.addToBackStack(null); // Add transaction to backstack for navigation
        transaction.commit();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recomendationTasker = view.findViewById(R.id.recomendationTaskers);
        //design horizontal layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recomendationTasker.setLayoutManager(layoutManager);
        recomendationTasker.setItemAnimator(new DefaultItemAnimator());

        //Initialize Adapter
        adapter = new RecomendatioAdapter(list,getContext(),this);

        //set adapter to recyclerview adapter
        recomendationTasker.setAdapter(adapter);

        fetchRecommendations();
    }
    private void fetchRecommendations() {
        ServiceApi serviceApi = MainActivity.retrofitService.getRetrofit("/user/getrec/").create(ServiceApi.class);

        serviceApi.getRecommendation().enqueue(new Callback<List<Tasker>>() {

            @Override
            public void onResponse(Call<List<Tasker>> call, Response<List<Tasker>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("RecResponse", "onResponse: "+response.body() );
                    list.clear();
                    for (Tasker tasker : response.body()) {
                        RecomendationCard card = new RecomendationCard(
                                tasker.getTaskerName(),
                                tasker.getSkill(),
                                String.valueOf(tasker.getFair()),
                                tasker.getRating() != null ? tasker.getRating() : 0.0,
                                String.valueOf(tasker.getTotalProject() != null ? tasker.getTotalProject() : 0),
                                R.drawable.solar

                        );
                        Log.e("RecResponse", "onResponse: "+tasker );
                        list.add(card);
                        taskerArrayList.add(tasker);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("API_CALL", "Response unsuccessful: " + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Tasker>> call, Throwable throwable) {
                Log.e("API_CALL", "Failed to fetch recommendations", throwable);

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        openTaskerProfile(position);
    }

    private void openTaskerProfile(int position) {
        TaskerProfile profileFragment = new TaskerProfile();
        Bundle args = new Bundle();
        args.putString("TASKER_NAME",taskerArrayList.get(position).getTaskerName());
        args.putString("TASKER_SKILL",taskerArrayList.get(position).getSkill());
        args.putString("TASKER_LOCATION",taskerArrayList.get(position).getLocation());
        args.putDouble("TASKER_RATING",taskerArrayList.get(position).getRating() == null ? 0.0d : taskerArrayList.get(position).getRating());
        args.putString("TASKER_TOTAL_PROJECTS",String.valueOf(taskerArrayList.get(position).getTotalProject() == null ? 0 : taskerArrayList.get(position).getTotalProject()));
        args.putString("TASKER_DESCRIPTION",taskerArrayList.get(position).getDescription());
        args.putString("TASKER_REVIEWS",taskerArrayList.get(position).getReview());
        args.putString("TASKER_FAIR",String.valueOf(taskerArrayList.get(position).getFair() == null ? "100" : taskerArrayList.get(position).getFair()));
        args.putByteArray("TASKER_IMAGE", taskerArrayList.get(position).getImg());
        args.putLong("TASKER_ID",taskerArrayList.get(position).getId());
// Set arguments to the fragment
        profileFragment.setArguments(args);


        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        // Replace HomeFragment with SearchFragment
        transaction.add(R.id.homeFragmentContainer, profileFragment);
        transaction.addToBackStack(null); // Add transaction to backstack for navigation
        transaction.commit();
    }
    
    void openSearchQuery(String skill){
        SearchFragment searchFragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString("SKILL",skill);
        searchFragment.setArguments(args);
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(R.id.homeFragmentContainer, searchFragment);
        transaction.addToBackStack(null); // Add transaction to backstack for navigation
        transaction.commit();
    }
}