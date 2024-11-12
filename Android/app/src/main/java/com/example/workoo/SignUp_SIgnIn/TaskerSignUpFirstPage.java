package com.example.workoo.SignUp_SIgnIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.fonts.FontFamily;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workoo.Helpers.StringHelper;
import com.example.workoo.R;

import java.util.Objects;

public class TaskerSignUpFirstPage extends AppCompatActivity {
    String TaskerName,taskerPassword,taskerReEnterPassword,TaskerPhone;
    Boolean isPasswordSame = false;
    EditText name,  phoneNumber,password, reEnterPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasker_sign_up_first_page);
        TextView SignIn = findViewById(R.id.SignIn);

        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phoneNumber);
        password  = findViewById(R.id.password);
        reEnterPassword = findViewById(R.id.reEnterPassword);

//        name.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                TaskerName = s.toString().trim();
//            }
//        });
//
//        phoneNumber.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                TaskerPhone = s.toString().trim();
//            }
//        });
//        password.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                taskerPassword = s.toString();
//                isPasswordSame = Objects.equals(taskerPassword, taskerReEnterPassword);
//            }
//        });
//
//        reEnterPassword.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                taskerReEnterPassword = s.toString();
//                isPasswordSame = Objects.equals(taskerPassword, taskerReEnterPassword);
//            }
//        });

        Button NextButton = findViewById(R.id.NextButton);
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(processFormFields()){
                    validatePhone();
                    validatePassRePass();
                }else{
                    Intent i = new Intent(getApplicationContext(), TaskerSignUpSecondPage.class);
                    i.putExtra("NAME", TaskerName);
                    i.putExtra("PHONENUMBER", TaskerPhone);
                    i.putExtra("PASSWORD", taskerPassword);
                    i.putExtra("REENTERPASSWORD", taskerReEnterPassword);
                    startActivity(i);
                }
                


//                Toast.makeText(TaskerSignUpFirstPage.this, "-----"+(isPasswordSame)+"-----", Toast.LENGTH_SHORT).show();
            }
        });

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UserSignIn.class);
                String ISTASKER = "ISTASKER";
                i.putExtra(ISTASKER,true);
                startActivity(i);
            }
        });


        //change the color of the Sigin
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
        //end of change color


    }

    //validate name
    public boolean validateName(){
        String Name  = name.getText().toString().trim();
        if(Name.isEmpty()){
            name.setError("Name Cannot be empty");
            return false;
        }else{
            TaskerName = Name;
            name.setError(null);
            return true;
        }
    }

    //validate TaskerPhone
    public boolean validatePhone(){
        String PhoneNumber = phoneNumber.getText().toString().trim();
        if(PhoneNumber.isEmpty()){
            phoneNumber.setError("Phone Number cannot be empty");
            return false;
        } else if (!StringHelper.regexPhoneNumberValidationPattern(PhoneNumber)) {
            phoneNumber.setError("Invalid Phone Number");
            return false;
        }else{
            TaskerPhone = PhoneNumber;
            phoneNumber.setError(null);
            return true;
        }
    }

    //validate password and confirm password
    public boolean validatePassRePass(){
        String Password = password.getText().toString();
        String reEnterPass = reEnterPassword.getText().toString();

        if(Password.isEmpty() || reEnterPass.isEmpty()){
            password.setError("Password cannot be empty");
            reEnterPassword.setError("Confirm password cannot be empty");
            return false;
        } else if (!Password.equals(reEnterPass)) {
            password.setError("Password Not Matched");
            reEnterPassword.setError("Password Not Matched");
            return false;
        } else{
            taskerPassword = Password;
            taskerReEnterPassword = reEnterPass;
            password.setError(null);
            reEnterPassword.setError(null);
            return true;
        }
    }

    //process form fields
    public Boolean processFormFields(){
        if(!validateName() || !validatePhone() || !validatePassRePass()){
            return true;
        }else {
            return false;
        }
    }
}