package com.example.workoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.workoo.Retrofit.RetrofitService;
import com.example.workoo.SignUp_SIgnIn.IsWorkerOrUser;

public class MainActivity extends AppCompatActivity {

    public static RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofitService =  new RetrofitService();
        boolean isLoggedIn = false;
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
                        }
                    });
                }else{
                    setContentView(R.layout.activity_main);
                }
            }
        },2000);
        setContentView(R.layout.splash_screen);

    }
}