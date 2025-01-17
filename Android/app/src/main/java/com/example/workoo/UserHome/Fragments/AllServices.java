package com.example.workoo.UserHome.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.workoo.Adapter.AllServicesAdapter;
import com.example.workoo.Adapter.TaskCompletedAdapter;
import com.example.workoo.Interface.RecyclerViewInterface;
import com.example.workoo.MainActivity;
import com.example.workoo.R;
import com.example.workoo.Retrofit.ServiceApi;
import com.example.workoo.model.AllService;

import java.util.ArrayList;

public class AllServices extends Fragment implements RecyclerViewInterface {
    AllServicesAdapter adapter;
    ArrayList<AllService> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        serviceApi = MainActivity.retrofitService.getRetrofit("/").create(ServiceApi.class);
        View v = inflater.inflate(R.layout.fragment_all_services, container, false);

        RecyclerView allServiceRV = v.findViewById(R.id.allServiceRV);

        list.add(new AllService(R.drawable.baseline_chair_24,"Furniture Assembly"));
        list.add(new AllService(R.drawable.baseline_tv_24,"TV Mounting"));
        list.add(new AllService(R.drawable.baseline_computer_24,"Computer Repair"));
        list.add(new AllService(R.drawable.baseline_maps_home_work_24,"House Cleaning"));
        list.add(new AllService(R.drawable.baseline_grass_24,"Gardening"));
        list.add(new AllService(R.drawable.baseline_carpenter_24,"Carpentry"));
        list.add(new AllService(R.drawable.baseline_car_repair_24,"Car Repair"));
        list.add(new AllService(R.drawable.baseline_local_car_wash_24,"Car Wash"));
        list.add(new AllService(R.drawable.baseline_support_agent_24,"IT Support"));
        list.add(new AllService(R.drawable.baseline_fitness_center_24,"Fitness Trainer"));
        list.add(new AllService(R.drawable.baseline_security_24,"Security Services"));
        list.add(new AllService(R.drawable.baseline_local_laundry_service_24,"Laundry Services"));

        LinearLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        allServiceRV.setLayoutManager(layoutManager);
        allServiceRV.setItemAnimator(new DefaultItemAnimator());
        adapter = new AllServicesAdapter(getContext(),this,list);
        allServiceRV.setAdapter(adapter);


        return v;
    }

    @Override
    public void onItemClick(int position) {
        SearchFragment searchFragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString("SKILL",list.get(position).getName());
        searchFragment.setArguments(args);
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.allServiceFragment, searchFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null); // Add transaction to backstack for navigation
        transaction.commit();
    }
}