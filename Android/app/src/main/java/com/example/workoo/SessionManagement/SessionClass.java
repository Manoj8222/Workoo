package com.example.workoo.SessionManagement;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.workoo.MainActivity;
import com.example.workoo.model.LoginTasker;
import com.example.workoo.model.LoginUser;
import com.example.workoo.model.Tasker;
import com.example.workoo.model.User;

public class SessionClass {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String SHARED_PREF_NAME = "session";
    private static final String USER_SESSION_KEY = "user_session";
    private static final String TASKER_SESSION_KEY = "tasker_session";

    public SessionClass(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public void saveUserSession(LoginUser user){
        int id = (int) (Math.random()*100000);
        editor.putInt(USER_SESSION_KEY,id);
        editor.apply();
    }
    public void saveTaskerSession(LoginTasker tasker){
        int id = (int) (Math.random()*100000);
        editor.putInt(TASKER_SESSION_KEY,id);
        editor.apply();
    }
    public int getUserSession(){

        return sharedPreferences.getInt(USER_SESSION_KEY,-1);
    }
    public int getTaskerSession(){
        return sharedPreferences.getInt(TASKER_SESSION_KEY,-1);
    }
    public void clearSession() {
        editor.clear().apply();
    }
}
