package com.example.workoo.Helpers;

public class StringHelper {
    //set regex expression pattern for phone
    public static  boolean regexPhoneNumberValidationPattern(String phoneNumber){
        String regex = "^[0-9]{10}$";

        if(phoneNumber.matches(regex)){
            return true;
        }else {
            return false;
        }
    }
}
