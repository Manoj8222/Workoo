package com.example.workoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.se.omapi.Session;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;

import com.example.workoo.Retrofit.RetrofitService;
import com.example.workoo.Retrofit.ServiceApi;
import com.example.workoo.SessionManagement.SessionClass;
import com.example.workoo.SignUp_SIgnIn.IsWorkerOrUser;
import com.example.workoo.SignUp_SIgnIn.UserSignIn;
import com.example.workoo.TaskerHome.TaskerHomePage;
import com.example.workoo.UserHome.UserHome;
import com.example.workoo.model.Tasker;
import com.example.workoo.model.User;

import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static RetrofitService retrofitService;
    boolean isLoggedIn;
    Boolean SessionLogin = false;
    Boolean TaskerSession = false;

    public static String UserPhone ;
    public static Long userId;

    @Override
    protected void onStart() {
        super.onStart();
        SessionClass session = new SessionClass(MainActivity.this);

        ServiceApi s = retrofitService.getRetrofit("/api/users/getUserId/").create(ServiceApi.class);


        int userId = session.getUserSession();
        int taskerId = session.getTaskerSession();

        if (userId != -1) {
            isLoggedIn = true;
            SessionLogin = true;
        } else if (taskerId != -1) {
            isLoggedIn = true;
            TaskerSession = true;
        } else {
            isLoggedIn = false;
            SessionLogin = false;
        }

        UserPhone = session.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofitService =  new RetrofitService();
        SessionClass session = new SessionClass(MainActivity.this);
        ServiceApi s = retrofitService.getRetrofit("/api/users/getUserId/").create(ServiceApi.class);
        s.getUserId(session.getCurrentUser()).enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                if (response.isSuccessful() && response.body() != null) {
                    userId = response.body();
                    Log.d("MainActivity123", "User ID: " + userId);
                } else {
                    Log.e("MainActivity", "Failed to fetch User ID: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Long> call, Throwable throwable) {
                Log.e("MainActivity", "API call failed: " + throwable.getMessage());
            }
        });

        isLoggedIn = getIntent().getBooleanExtra("ISLOGGEDIN",false);
        Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(!isLoggedIn){
                        setContentView(R.layout.fragment_get_started);
                        ImageView i = findViewById(R.id.enteryman);
                        Button b = findViewById(R.id.getstarted);
                        Animation move = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);

                        i.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.image));

                        b.setAnimation(move);

                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(getApplicationContext(), IsWorkerOrUser.class);
                                startActivity(i);
                                finish();
                            }
                        });
                    }else if(TaskerSession){
                        navigateToHome(TaskerHomePage.class);

                    }else if(SessionLogin){
                        navigateToHome(UserHome.class);
                    }
                }
            },1500);
            Animation anim = AnimationUtils.loadAnimation(this,R.anim.scale_up);
            setContentView(R.layout.splash_screen);
        TextView splashText = findViewById(R.id.SplashText);

        Handler h1 = new Handler();
        h1.postDelayed(new Runnable() {
            @Override
            public void run() {
//                splashText.setAnimation(anim);
                splashText.startAnimation(anim);
            }
        },0);



    }

    private void navigateToHome(Class<?> destinationClass) {
        Intent intent = new Intent(getApplicationContext(), destinationClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finishAffinity(); // Closes all activities
        super.onBackPressed();
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }
}