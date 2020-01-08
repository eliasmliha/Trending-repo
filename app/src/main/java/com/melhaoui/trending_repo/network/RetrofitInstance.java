package com.melhaoui.trending_repo.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static Retrofit retrofit;
    public static RetrofitInstance instance;
    public static final String BASE_URL = "https://api.github.com/";

    public RetrofitInstance() {
        retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static synchronized RetrofitInstance getInstance() {

        if (instance == null) {
            instance = new RetrofitInstance();
        }
        return instance;
    }
    public GetDataService getApi() {
        return retrofit.create(GetDataService.class);
    }

}
