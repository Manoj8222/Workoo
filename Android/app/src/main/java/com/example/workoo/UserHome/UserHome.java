package com.example.workoo.UserHome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.workoo.MainActivity;
import com.example.workoo.R;
import com.example.workoo.SessionManagement.SessionClass;
import com.example.workoo.UserHome.Fragments.HomeFragment;
import com.example.workoo.UserHome.Fragments.MyTaskerFragment;
import com.example.workoo.UserHome.Fragments.ProfileFragment;
import com.example.workoo.UserHome.Fragments.TaskFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserHome extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        bottomNavigationView = findViewById(R.id.bottomNavView);
        frameLayout = findViewById(R.id.frameLayout);

//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.navHome:
//                        return true;
//                    case R.id.navMyTasker:
//                        return true;
//                    case R.id.navTask:
//                        return true;
//                    case R.id.navProfile:
//                        return true;
//                }
//                return false;
//            }
//        });

//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                int itemId = item.getItemId();
//                item.setCheckable(true);
//
//                if(itemId == R.id.navHome){
//
//                    loadFragment(new HomeFragment(),false);
//
//                } else if (itemId == R.id.navMyTasker) {
//
//                    loadFragment(new MyTaskerFragment(),false);
//
//                }else if(itemId == R.id.navTask){
//                    loadFragment(new TaskFragment(),false);
//
//                }else{
//                    loadFragment(new ProfileFragment(),false);
//                }
//
//                return false;
//            }
//        });
//        loadFragment(new HomeFragment(),true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

//                if(item.getItemId() == 0){
//                    itemId = item.getItemId();
//                }else{
//                    itemId = R.id.navHome;
//                }


                if (itemId == R.id.navHome) {
                    loadFragment(new HomeFragment(), false);
                } else if (itemId == R.id.navMyTasker) {
                    loadFragment(new MyTaskerFragment(), false);
                } else if (itemId == R.id.navTask) {
                    loadFragment(new TaskFragment(), false);
                } else if (itemId == R.id.navProfile) {
                    loadFragment(new ProfileFragment(), false);
                } else {
                    return false;
                }

                return true; // Notify that the event was handled
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.navHome);

    }

    private void loadFragment(Fragment fragment,boolean isAppInitialized){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(isAppInitialized){
            fragmentTransaction.add(R.id.frameLayout,fragment);
        }else {
            fragmentTransaction.replace(R.id.frameLayout,fragment);
        }
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
}