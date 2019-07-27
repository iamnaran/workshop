package com.android.workshop.apiservices;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by NaRan on 23,July,2018.
 * Copyright (c) UT Pvt. Ltd. All rights reserved.
 * nrn.panthi@gmail.com
 * MacBook
 **/
public class ApiInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
//        if (Utilities.getLoginResponse() == null || TextUtils.isEmpty(Utilities.getLoginResponse().getUserDetails().getToken())) {
//            return chain.proceed(request);
//        }
        request = request.newBuilder()
//                .addHeader("Authorization", Utilities.getLoginResponse().getUserDetails().getToken())
                .addHeader("Accept", "Accept: application/x.school.v1+json")
                .header("Cache-Control", String.format("max-age=%d", 50000))
                .build();

        Response response = chain.proceed(request);

//        if (response.code() == 403) {
//        do session out work
//        }

        return response;
    }
}

