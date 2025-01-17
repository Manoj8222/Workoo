package com.example.workoo.SignUp_SIgnIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.workoo.MainActivity;
import com.example.workoo.R;
import com.example.workoo.Retrofit.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IsWorkerOrUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is_worker_or_user);
        Button getAJob = findViewById(R.id.getajob);
        MainActivity m = new MainActivity();

        ServiceApi userServiceApi = MainActivity.retrofitService.getRetrofit("/api/test/").create(ServiceApi.class);

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
    //testing purpose
    public void userApiCall(ServiceApi serviceApi){
        serviceApi.test().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Toast.makeText(IsWorkerOrUser.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable throwable) {
                Toast.makeText(IsWorkerOrUser.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(this, "Api Called", Toast.LENGTH_SHORT).show();
    }
}