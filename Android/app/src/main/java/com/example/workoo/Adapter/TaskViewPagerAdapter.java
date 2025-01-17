package com.example.workoo.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.workoo.UserHome.Fragments.TaskFragments.CompletedFragment;
import com.example.workoo.UserHome.Fragments.TaskFragments.ScheduledFragment;

public class TaskViewPagerAdapter extends FragmentStateAdapter {
    public TaskViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position){
           case 0:
               return new ScheduledFragment();
           case 1:
               return new CompletedFragment();
           default:
               throw new IllegalArgumentException("Invalid position: " + position);
       }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
