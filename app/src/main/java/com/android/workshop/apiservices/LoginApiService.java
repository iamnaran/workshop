package com.android.workshop.apiservices;


import com.android.workshop.models.SampleData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LoginApiService {

    @GET("list")
    Call<List<SampleData>> requestSampleData();

//    @POST("login")
//    @FormUrlEncoded
//    Call<LoginModel> userLogin(
//            @Field("email") String email,
//            @Field("password") String password,
//            @Field("fcm_token") String fcmToken
//    );



}
