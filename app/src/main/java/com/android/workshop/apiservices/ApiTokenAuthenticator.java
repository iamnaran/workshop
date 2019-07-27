package com.android.workshop.apiservices;


import okhttp3.Authenticator;

/**
 * Created by NaRan on 23,July,2018.
 * Copyright (c) UT Pvt. Ltd. All rights reserved.
 * nrn.panthi@gmail.com
 * MacBook
 **/
public class ApiTokenAuthenticator  {


//    implements Authenticator

//    @Nullable
//    @Override
//    public Request authenticate(@NonNull Route route, @NonNull Response response) throws IOException {
//        TokenRefreshApiService tokenRefreshService = ApiClient.getClient().create(TokenRefreshApiService.class);
//        Call<RefreshTokenModel> call = tokenRefreshService.refreshToken();
//        retrofit2.Response<RefreshTokenModel> tokenResponse = call.execute();
//
//
//        assert tokenResponse.body() != null;
//        if (tokenResponse.isSuccessful() && tokenResponse.body().getStatus()) {
//            RefreshTokenModel tokenRefreshModel = tokenResponse.body();
//            assert tokenRefreshModel != null;
//            Utilities.saveUserTokenLogin(tokenRefreshModel.getToken());
//
//            return response.request().newBuilder()
//                    .addHeader("Authorization", tokenRefreshModel.getToken())
//                    .addHeader("Accept", "Accept: application/x.school.v1+json")
//                    .build();
//        }
//        return null;
//
//    }


}

