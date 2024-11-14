package com.example.workoo.SignUp_SIgnIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.se.omapi.Session;
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

import com.example.workoo.Helpers.StringHelper;
import com.example.workoo.MainActivity;
import com.example.workoo.R;
import com.example.workoo.Retrofit.RetrofitService;
import com.example.workoo.Retrofit.ServiceApi;
import com.example.workoo.SessionManagement.SessionClass;
import com.example.workoo.TaskerHome.TaskerHomePage;
import com.example.workoo.UserHome.UserHome;
import com.example.workoo.model.LoginTasker;
import com.example.workoo.model.LoginUser;
import com.example.workoo.model.Tasker;
import com.example.workoo.model.User;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSignIn extends AppCompatActivity {
    EditText phoneNumber,password;
    String userPassword;
    Long userPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_in);
        phoneNumber = findViewById(R.id.phoneNumber);
        password = findViewById(R.id.password);
        Button Login = findViewById(R.id.LogIn);
        Boolean isTasker = getIntent().getExtras().getBoolean("ISTASKER");

        SessionClass sessionClass = new SessionClass(UserSignIn.this);



        ServiceApi userServiceApi = MainActivity.retrofitService.getRetrofit("/api/user/login/").create(ServiceApi.class);
        ServiceApi taskerServiceApi = MainActivity.retrofitService.getRetrofit("/api/tasker/login/").create(ServiceApi.class);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTasker) {
                    sessionClass.clearSession();
                    TaskerApiCall(taskerServiceApi);
                    LoginTasker tasker = new LoginTasker(BigInteger.valueOf(userPhone), userPassword);
                    sessionClass.saveTaskerSession(tasker);
                } else {
                    sessionClass.clearSession();
                    UserApiCall(userServiceApi);
                    LoginUser user = new LoginUser(BigInteger.valueOf(userPhone), userPassword);
                    sessionClass.saveUserSession(user);
                }
            }
        });




        TextView signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTasker){
                    Intent i = new Intent(getApplicationContext(), TaskerSignUpFirstPage.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(getApplicationContext(), UserSignUp.class);
                    startActivity(i);
                }
            }
        });

        TextView textView = findViewById(R.id.signUp);

        try {
            String text = "Don't have an account? Sign Up here";
            SpannableString spannableString = new SpannableString(text);

            // Locate "Sign Up here" part in the string
            int startIndex = text.indexOf("Sign Up here");
            int endIndex = startIndex + "Sign Up here".length();

            // Apply purple color to "Sign Up here"
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#6750A4")),
                    startIndex, endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            // Apply semi-bold font style to "Sign Up here"
            spannableString.setSpan(new StyleSpan(Typeface.BOLD),
                    startIndex, endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            // Set the styled text to TextView
            textView.setText(spannableString);
        } catch (Exception e) {
            Log.e("TextViewError", "Error applying spannable text", e);
        }


    }

    public boolean validatePhone(){
        String PhoneNumber = phoneNumber.getText().toString().trim();
        if(PhoneNumber.isEmpty()){
            phoneNumber.setError("Phone Number cannot be empty");
            return false;
        } else if (!StringHelper.regexPhoneNumberValidationPattern(PhoneNumber)) {
            phoneNumber.setError("Invalid Phone Number");
            return false;
        }else{
            userPhone = Long.valueOf(PhoneNumber);
            phoneNumber.setError(null);
            return true;
        }
    }

    public boolean validatePassword(){
        String Password = password.getText().toString();
        if(Password.isEmpty()){
            password.setError("Password cannot be empty");
            return false;
        } else{
            userPassword = Password;
            password.setError(null);
            return true;
        }
    }
    //UserApi call
    public void UserApiCall(ServiceApi serviceApi){
        if(validatePhone() && validatePassword()){
            LoginUser loginUser = new LoginUser(new BigInteger(phoneNumber.getText().toString()),password.getText().toString());
            serviceApi.loginUser(loginUser).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful() && response.body() != null){
                        User user = response.body();
//                        Toast.makeText(UserSignIn.this, "User Login Successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), UserHome.class);
                        i.putExtra( "ISLOGGEDIN", true);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();
                    } else if (response.code() == 404) {
                        phoneNumber.setError("Phone Number Does Not Exist");
                    } else if (response.code() == 400) {
                        password.setError("Incorrect Password");
                    } else {
                        Toast.makeText(UserSignIn.this, "UnSuccessful"+loginUser.getPhone_number(), Toast.LENGTH_SHORT).show();
                        try {
                            // Handle error message
                            String errorMessage = response.errorBody().string();
                            Log.e("Login Error", errorMessage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable throwable) {
                    Toast.makeText(UserSignIn.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            validatePassword();
            Toast.makeText(UserSignIn.this, "All Fields Sould be filled", Toast.LENGTH_SHORT).show();
        }
    }


    //Tasker Api call
    public void TaskerApiCall(ServiceApi serviceApi){
        if(validatePhone() && validatePassword()){
            LoginTasker loginTasker = new LoginTasker(new BigInteger(phoneNumber.getText().toString()),password.getText().toString());
            serviceApi.loginTasker(loginTasker).enqueue(new Callback<Tasker>() {
                @Override
                public void onResponse(Call<Tasker> call, Response<Tasker> response) {
                    if(response.isSuccessful() && response.body() != null){
                        Tasker tasker = response.body();
                        Intent i = new Intent(UserSignIn.this, TaskerHomePage.class);
                        i.putExtra("ISLOGGEDIN", true);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();
                    } else if (response.code() == 404) {
                        phoneNumber.setError("Phone Number Does Not Exist");
                    } else if (response.code() == 400) {
                        password.setError("Incorrect Password");
                    } else {
                        Toast.makeText(UserSignIn.this, "UnSuccessful"+loginTasker.getPhone_number(), Toast.LENGTH_SHORT).show();
                        try {
                            // Handle error message
                            String errorMessage = response.errorBody().string();
                            Log.e("Login Error", errorMessage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Tasker> call, Throwable throwable) {
                    Toast.makeText(UserSignIn.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            validatePassword();
            Toast.makeText(UserSignIn.this, "All Fields Sould be filled", Toast.LENGTH_SHORT).show();
        }
    }

}