package com.android.workshop.apiservices;


import com.android.workshop.models.Photos;
import com.android.workshop.models.SampleData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginApiService {

    @GET("list")
    Call<List<Photos>> requestPhotos();



//
//    @POST("list")
//    @FormUrlEncoded
//    Call<List<Photos>> login(String);
}
