package com.example.workoo.UserHome.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.workoo.Adapter.TaskCompletedAdapter;
import com.example.workoo.Adapter.TaskViewPagerAdapter;
import com.example.workoo.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class TaskFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    TaskViewPagerAdapter adapter;
    TaskCompletedAdapter completedAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task, container, false);
        tabLayout = v.findViewById(R.id.tabLayout);
        viewPager = v.findViewById(R.id.view_pager_tab_layout);

        adapter = new TaskViewPagerAdapter(requireActivity());
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Scheduled");
                    break;
                case 1:
                    tab.setText("Completed");
                    break;
            }
        }) .attach();

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Objects.requireNonNull(tabLayout.getTabAt(position)).select();
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        ImageView referFriend = v.findViewById(R.id.referFriend);
        referFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentTransaction fr = getFragmentManager().beginTransaction();
//                fr.add(R.id.referFriendContainer,new ReferFriend());
//                fr.commit();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.Scheduled,new ReferFriend());
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return v;
    }
}