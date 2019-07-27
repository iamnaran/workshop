package com.android.workshop.apiservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NaRan on 23,July,2018.
 * Copyright (c) UT Pvt. Ltd. All rights reserved.
 * nrn.panthi@gmail.com
 * MacBook
 **/
public class ApiClient {

    //    private static final String BASE_URL = "https://elibrary.smartgov.app/api/";
    private static final String BASE_URL = " https://picsum.photos/v2/";


//    https://api.mixcloud.com/popular/

    public static final String IMAGE_BASE_URL = "";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.authenticator(new ApiTokenAuthenticator());
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient httpClient = builder.readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new ApiInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS).build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).client(httpClient).build();
        }
        return retrofit;
    }
}
