package com.example.workoo.SignUp_SIgnIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workoo.MainActivity;
import com.example.workoo.R;
import com.example.workoo.Retrofit.ServiceApi;
import com.example.workoo.TaskerHome.TaskerHomePage;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskerSignUpSecondPage extends AppCompatActivity {
    String TSkill, Description,TLocation;
    Long fair = null;
    String review = null;
    BigDecimal rating = null;
    Long total_project = null;
    EditText skill, describeYourself,location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasker_sign_up_second_page);
        String name = getIntent().getStringExtra("NAME");
        String phoneNumber = getIntent().getStringExtra("PHONENUMBER");
        String password = getIntent().getStringExtra("PASSWORD");
        String reEnterPassword = getIntent().getStringExtra("REENTERPASSWORD");

        byte [] img = null;
        skill = findViewById(R.id.skill);
        describeYourself = findViewById(R.id.describeYourself);
        location = findViewById(R.id.location);

        ServiceApi taskerSignUpApi = MainActivity.retrofitService.getRetrofit("/api/tasker/register/").create(ServiceApi.class);

        Button Submit = findViewById(R.id.Submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(processFormField()){
                    validateDescription();
                    validateLocation();
                }else {

                    taskerSignUpApi.registerTasker(name, Long.valueOf(phoneNumber),password, img,TSkill,Description,fair,TLocation,rating,review,total_project).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.isSuccessful() && response.body() != null){
                                Toast.makeText(TaskerSignUpSecondPage.this, "Successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), TaskerHomePage.class);
                                startActivity(i);
                                finish();
                                Toast.makeText(TaskerSignUpSecondPage.this, "Successful", Toast.LENGTH_SHORT).show();
                            }else if(response.code() == 409){
                                Toast.makeText(TaskerSignUpSecondPage.this, "Tasker Already Exist, Please Login", Toast.LENGTH_SHORT).show();
                            } else if (response.code() == 302) {
                                Toast.makeText(TaskerSignUpSecondPage.this, "Tasker Already Exist, Please Login", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(TaskerSignUpSecondPage.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                                try {
                                    // Handle error message
                                    String errorMessage = response.errorBody().string();
                                    Log.e("Tasker Signup Error", errorMessage);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable throwable) {
                            Toast.makeText(TaskerSignUpSecondPage.this, "Failed to Register", Toast.LENGTH_SHORT).show();
                        }
                    });
                    

                }
            }
        });


        //Changing the color of SignIn button
        TextView textView = findViewById(R.id.SignIn);

        try {
            String text = "Already have an account? Sign in";
            SpannableString spannableString = new SpannableString(text);

            // Locate "Sign in" part in the string
            int startIndex = text.indexOf("Sign in");
            int endIndex = startIndex + "Sign in".length();

            // Apply purple color to "Sign in"
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#6750A4")),
                    startIndex, endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            // Apply semi-bold font style to "Sign in"
            spannableString.setSpan(new StyleSpan(Typeface.BOLD),
                    startIndex, endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            // Set the styled text to TextView
            textView.setText(spannableString);
        } catch (Exception e) {
            Log.e("TextViewError", "Error applying spannable text", e);
        }
        //end
    }

    //Validate Skill
    public boolean validateSkill(){
        String Skill = skill.getText().toString().trim();
        TSkill = Skill;
        if(Skill.isEmpty()){
            skill.setError("Skill cannot be Empty");
            return false;
        }else {
            skill.setError(null);
            return true;
        }
    }

    //Validate Skill
    public boolean validateDescription(){
        String description = describeYourself.getText().toString().trim();
        Description = description;
        if(description.isEmpty()){
            describeYourself.setError("Please Describe Yourself");
            return false;
        }else {
            describeYourself.setError(null);
            return true;
        }
    }

    public boolean validateLocation(){
        String Location = location.getText().toString().trim();
        TLocation = Location;
        if(Location.isEmpty()){
            location.setError("Location cannot be Empty");
            return false;
        }else {
            location.setError(null);
            return true;
        }
    }
    public boolean processFormField(){
        if(!validateSkill() || !validateDescription() || !validateLocation()){
            return true;
        }else {
            return false;
        }
    }
}