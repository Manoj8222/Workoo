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

import com.example.workoo.Helpers.StringHelper;
import com.example.workoo.MainActivity;
import com.example.workoo.R;
import com.example.workoo.Retrofit.ServiceApi;
import com.example.workoo.UserHome.UserHome;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSignUp extends AppCompatActivity {
    String UserName,password,userReEnterPassword;
    byte[] image;
    Long phoneNumber;
    EditText name,userPhoneNumber,user_password,userReEnterPassworrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        TextView signIn = findViewById(R.id.SignIn);

        ServiceApi userSignUp = MainActivity.retrofitService.getRetrofit("/api/user/register/").create(ServiceApi.class);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UserSignIn.class);
                String ISTASKER = "ISTASKER";
                i.putExtra(ISTASKER,false);
                startActivity(i);
            }
        });
        
        Button signUp = findViewById(R.id.SignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(processFormFields()){
                    userSignUp.registerUser(UserName,phoneNumber,image,password).enqueue(new Callback<String>() {

                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                                Toast.makeText(UserSignUp.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                                // Proceed to the next activity
                                Intent i = new Intent(getApplicationContext(), UserHome.class);
                                startActivity(i);
                                finish();
                            } else if (response.code() == 409 || response.code() == 302 || response.code() == 303) {
                                userPhoneNumber.setError("User Already Exists, Please Login");
                            } else {
                                Toast.makeText(UserSignUp.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                                if (response.errorBody() != null) {
                                    try {
                                        String errorMessage = response.errorBody().string();
                                        Log.e("User Signup Error", errorMessage);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }


                        @Override
                        public void onFailure(Call<String> call, Throwable throwable) {
                            Toast.makeText(UserSignUp.this, "Failed", Toast.LENGTH_SHORT).show();
                            Log.e("On failure", "onFailure: "+throwable.getMessage() );
                        }
                    });
                }else {
                    validatePhone();
                    validatePassword();
                    Toast.makeText(UserSignUp.this, "All Fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });

        name = findViewById(R.id.name);
        userPhoneNumber = findViewById(R.id.phoneNumber);
        user_password = findViewById(R.id.password);
        userReEnterPassworrd = findViewById(R.id.reEnterPassword);

        //Changing the Text color
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
    }
    public boolean validatePhone(){
        String PhoneNumber = userPhoneNumber.getText().toString().trim();
        if(PhoneNumber.isEmpty()){
            userPhoneNumber.setError("Phone Number cannot be empty");
            return false;
        } else if (!StringHelper.regexPhoneNumberValidationPattern(PhoneNumber)) {
            userPhoneNumber.setError("Invalid Phone Number");
            return false;
        }else{
            phoneNumber = Long.valueOf(PhoneNumber);
            userPhoneNumber.setError(null);
            return true;
        }
    }

    public boolean validatePassword(){
        String Password = user_password.getText().toString();
        String reEnterPassword = userReEnterPassworrd.getText().toString();
        if(Password.isEmpty() || reEnterPassword.isEmpty()){
            user_password.setError("Password cannot be empty");
            userReEnterPassworrd.setError("Confirm password cannot be empty");
            return false;
        } else if (!Password.equals(reEnterPassword)) {
            user_password.setError("Password Not Matched");
            userReEnterPassworrd.setError("Password Not Matched");
            return false;
        } else{
            password = Password;
            userReEnterPassword = reEnterPassword;
            user_password.setError(null);
            userReEnterPassworrd.setError(null);
            return true;
        }
    }

    public boolean validateName(){
        String Name = name.getText().toString().trim();
        if(Name.isEmpty()){
            name.setError("Name Cannot be Empty");
            return false;
        }else{
            UserName = Name;
            name.setError(null);
            return true;
        }
    }
    
    public boolean processFormFields(){
        if(validateName() && validatePassword() && validatePhone()){
            return true;
        }else{
            return false;
        }
    }
}