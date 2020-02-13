package com.example.prueba.Interface;

import android.content.Context;
import android.widget.Toast;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://10.22.3.29:3000";
    private static  RetrofitClient mInstance;
    private static Retrofit retrofit;

    private RetrofitClient() {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

    }
    public static synchronized RetrofitClient getInstance(){
        if(mInstance == null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }
    public APIPerumotor getApi(){
        return retrofit.create(APIPerumotor.class);
    }
}
