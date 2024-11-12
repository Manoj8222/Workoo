package com.example.workoo.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private Retrofit retrofit;
    private static final String BASE_URL = "http://192.168.29.65:8080";

    public RetrofitService(){
        initializeRetrofit("");
    }
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    private void initializeRetrofit(String dynamicPath) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL + dynamicPath)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
    public Retrofit getRetrofit(String dynamicPath) {
        initializeRetrofit(dynamicPath);
        return retrofit;
    }
}
