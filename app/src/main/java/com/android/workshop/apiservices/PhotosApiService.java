package com.android.workshop.apiservices;


import com.android.workshop.models.Photos;
import com.android.workshop.models.SampleData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotosApiService {

    @GET("list")
    Call<List<Photos>> requestSampleData();







}
