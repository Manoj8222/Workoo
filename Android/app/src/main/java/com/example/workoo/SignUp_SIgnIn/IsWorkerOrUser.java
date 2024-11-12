package com.example.workoo.SignUp_SIgnIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.workoo.R;

public class IsWorkerOrUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is_worker_or_user);
        Button getAJob = findViewById(R.id.getajob);
        getAJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),UserSignIn.class);
                String ISTASKER = "ISTASKER";
                i.putExtra(ISTASKER,true);
                startActivity(i);
            }
        });
        Button hireATasker = findViewById(R.id.hireatasker);
        hireATasker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UserSignIn.class);
                String ISTASKER = "ISTASKER";
                i.putExtra(ISTASKER,false);
                startActivity(i);
            }
        });
    }
}