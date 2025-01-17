package com.example.workoo.UserHome.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workoo.Adapter.SearchTaskerListAdapter;
import com.example.workoo.Interface.FavoriteActionListener;
import com.example.workoo.Interface.RecyclerViewInterface;
import com.example.workoo.MainActivity;
import com.example.workoo.R;
import com.example.workoo.Retrofit.ServiceApi;
import com.example.workoo.model.SearchTaskerList;
import com.example.workoo.model.TaskerSearchResponse;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment implements RecyclerViewInterface, FavoriteActionListener {

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ConstraintLayout serviceListCount;
    ArrayList<TaskerSearchResponse> taskerList;
    SearchTaskerListAdapter taskerListAdapter;
    RecyclerView ServiceTaskerList;
    ServiceApi serviceApi;
    Long userId = MainActivity.userId;
    TextView taskerSkill,taskerItemCount;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        taskerListAdapter.setFavoriteActionListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] servicesList = {"Plumbing", "Electrician", "House Cleaning", "Gardening", "Carpentry", "Appliance Repair", "Painting", "Pest Control",
                "Security Services", "Babysitting", "Elderly Care", "Car Wash", "Laundry Services", "Interior Design", "Mobile Repair",
                "IT Support", "Fitness Trainer", "Event Planning", "Photography", "Pet Grooming", "Tutoring", "Vehicle Repair",
                "Cooking Services", "Massage Therapy", "Yoga Instructor", "Home Automation Setup", "Courier Services", "Home Sanitization",
                "Party Decoration", "Hairdressing", "Makeup Artist", "Shoe Repair", "Landscaping", "Computer Repair", "Networking Setup",
                "Solar Panel Installation", "Roof Repair", "Furniture Assembly", "Tile Installation","Coding", "Window Cleaning"};

        View v = inflater.inflate(R.layout.fragment_search, container, false);
        serviceApi = MainActivity.retrofitService.getRetrofit("/").create(ServiceApi.class);
        SearchView searchInput = v.findViewById(R.id.searchView);
        listView = v.findViewById(R.id.searchServiceList);

        taskerSkill = v.findViewById(R.id.taskerSkill);
        taskerItemCount = v.findViewById(R.id.taskerItemCount);

        serviceListCount = v.findViewById(R.id.servicelListCount);
        ServiceTaskerList = v.findViewById(R.id.ServiceTaskerList);

        // Initially hide the list
        listView.setVisibility(View.GONE);

        // Automatically focus the search bar and show the keyboard
        searchInput.requestFocus();
        searchInput.post(() -> {
            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
        });

        arrayAdapter = new ArrayAdapter<>(requireActivity(), R.layout.service_list, servicesList);
        listView.setAdapter(arrayAdapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = ( (TextView) view ).getText().toString();
//                Toast.makeText(getContext(), ""+selectedItem, Toast.LENGTH_SHORT).show();
//                searchInput.setQuery(selectedItem,false);
//
////                listView.setVisibility(View.GONE);
////                performActionBasedOnSelection(selectedItem);
////                ServiceTaskerList.setVisibility(View.VISIBLE);
//            }
//        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedSkill = ((TextView) view).getText().toString();
                searchInput.setQuery(selectedSkill, false);
                Log.e("SearchResponse", "onItemClick: "+selectedSkill );
                // Make API call to search taskers
                CallSearchApi(selectedSkill);

            }
        });


        if(getArguments() != null){
//            searchInput.setQuery(getArguments().getString("SKILL"),true);
            String selectedSkill = getArguments().getString("SKILL");
            searchInput.setQuery(selectedSkill, false);
            Log.e("SearchResponse", "onItemClick: "+selectedSkill );
            // Make API call to search taskers
            CallSearchApi(selectedSkill);
        }


        searchInput.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    // Hide the list if search is empty
                    listView.setVisibility(View.GONE);
                    onSearchFieldCleared();
                } else {
                    // Show the list and filter items
                    listView.setVisibility(View.VISIBLE);
                    arrayAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });
        if (taskerList == null) {
            taskerList = new ArrayList<>();
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        ServiceTaskerList.setLayoutManager(layoutManager);
        ServiceTaskerList.setItemAnimator(new DefaultItemAnimator());

        taskerListAdapter = new SearchTaskerListAdapter(taskerList,getContext(),this);
        ServiceTaskerList.setAdapter(taskerListAdapter);



        return v;
    }
    private void performActionBasedOnSelection(String selectedItem) {
            serviceListCount.setVisibility(View.VISIBLE);
            hideKeyboard();
    }

    private void onSearchFieldCleared() {
        // Example task: Hide the list and display a toast
        serviceListCount.setVisibility(View.GONE);
        ServiceTaskerList.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(int position) {
        openTaskerProfile(position);
    }

    private void openTaskerProfile(int position) {
        TaskerProfile profileFragment = new TaskerProfile();
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                TaskerSearchResponse t = taskerList.get(position);

        Bundle args = new Bundle();
        args.putInt("PHOTO",R.drawable.profile_two);
        if(position == 0){
//            args.taskerImage.setImageResource(R.drawable.profile_two);
            args.putInt("PHOTO",R.drawable.profile_two);
        } else if (position == 1) {
            args.putInt("PHOTO",R.drawable.profile_three);
        } else if (position == 2) {
            args.putInt("PHOTO",R.drawable.profile_one);
        } else if (position == 3) {
            args.putInt("PHOTO",R.drawable.profile_four);
        } else if (position == 4) {
            args.putInt("PHOTO",R.drawable.profile_five);
        } else if (position == 5) {
            args.putInt("PHOTO",R.drawable.profile_six);
        } else if (position == 6) {
            args.putInt("PHOTO",R.drawable.profile_seven);
        } else if (position == 7) {
            args.putInt("PHOTO",R.drawable.profile_eight);
        } else if (position == 8) {
            args.putInt("PHOTO",R.drawable.profile_nine);
        } else if (position == 9) {
            args.putInt("PHOTO",R.drawable.profile_ten);
        } else if (position == 10) {
            args.putInt("PHOTO",R.drawable.profile_eleven);
        } else if (position == 11) {
            args.putInt("PHOTO",R.drawable.profile_twelve);
        } else if (position == 12) {
            args.putInt("PHOTO",R.drawable.profile_therteen);
        } else if (position == 13) {
            args.putInt("PHOTO",R.drawable.profile_fourteenj);
        } else if (position == 14) {
            args.putInt("PHOTO",R.drawable.profile_fifteen);
        }
        args.putString("TASKER_NAME",taskerList.get(position).getTaskerName());
        args.putString("TASKER_SKILL",taskerList.get(position).getSkill());
        args.putString("TASKER_LOCATION",taskerList.get(position).getLocation());
        args.putDouble("TASKER_RATING",taskerList.get(position).getRating() == null ? 0.0d : taskerList.get(position).getRating());
        args.putString("TASKER_TOTAL_PROJECTS",String.valueOf(taskerList.get(position).getTotalProject() == null ? 0 : taskerList.get(position).getTotalProject()));
        args.putString("TASKER_DESCRIPTION",taskerList.get(position).getDescription());
        args.putString("TASKER_REVIEWS",taskerList.get(position).getReview());
        args.putString("TASKER_FAIR",String.valueOf(taskerList.get(position).getFair() == null ? "100" : taskerList.get(position).getFair()));
        args.putByteArray("TASKER_IMAGE", taskerList.get(position).getImg());
        args.putLong("TASKER_ID",taskerList.get(position).getId());
        // Set arguments to the fragment
        profileFragment.setArguments(args);
//         Replace HomeFragment with SearchFragment
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(R.id.SearchFragment, profileFragment);
        transaction.addToBackStack(null); // Add transaction to backstack for navigation
        transaction.commit();
    }
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = requireActivity().getCurrentFocus();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    void CallSearchApi(String selectedSkill){
        Call<List<TaskerSearchResponse>> call = serviceApi.searchTaskers(selectedSkill);
        call.enqueue(new Callback<List<TaskerSearchResponse>>() {
            @Override
            public void onResponse(Call<List<TaskerSearchResponse>> call, Response<List<TaskerSearchResponse>> response) {
                taskerSkill.setText(selectedSkill);

                Log.e("SearchResponse", "onResponse: "+response.body() );
                if (response.isSuccessful() && response.body() != null) {
                    List<TaskerSearchResponse> taskers = response.body();
                    taskerList.clear();
                    taskerList.addAll(taskers);
                    listView.setVisibility(View.GONE);
                    serviceListCount.setVisibility(View.VISIBLE);
                performActionBasedOnSelection(selectedSkill);
                ServiceTaskerList.setVisibility(View.VISIBLE);
                String taskerSize = String.valueOf(taskerList.size());
                    taskerItemCount.setText(taskerSize);
                }else{
                    listView.setVisibility(View.VISIBLE);
                    arrayAdapter.getFilter().filter(selectedSkill);
                    Log.e("SearchResponse", "onError: "+response.message() );
                    Toast.makeText(getContext(), "Don't have "+selectedSkill+" Taskers ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<TaskerSearchResponse>> call, Throwable t) {
                Log.e("Error Response", "onFailure: "+t.getMessage() );
                Toast.makeText(getContext(), "Failed to fetch taskers", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onAddToFavorite(Long taskerId, int position) {
        serviceApi.addToFavorites(taskerId, userId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    taskerListAdapter.updateFavoriteStatus(position, true);
                } else {
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(requireContext(), "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRemoveFromFavorite(Long taskerId, int position) {
        serviceApi.removeFromFavorites(taskerId, userId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    taskerListAdapter.updateFavoriteStatus(position, false);
                } else {
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(requireContext(), "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
