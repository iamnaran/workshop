package com.android.workshop.utils;

import android.content.Intent;
import android.content.SharedPreferences;

import com.android.workshop.constants.AppConstants;
import com.android.workshop.constants.SharedPrefConstants;
import com.android.workshop.helper.UpasargaApplication;
import com.google.gson.GsonBuilder;

public class Utilities {

    /*
     * SHARED PREFERENCES UTILITIES
     * */

    /**
     * @return Login Response
     */


    public static void saveSessionExpiredData(boolean status) {

        SharedPreferences.Editor editor = UpasargaApplication.getSharedPreference().edit();
        editor.putBoolean(AppConstants.IS_SESSION_EXPIRED, status);
        editor.apply();

    }


    public static boolean isSessionExpired() {

        boolean isLogin = UpasargaApplication.getSharedPreference().getBoolean(AppConstants.IS_SESSION_EXPIRED, false);
        if (isLogin) {
            return true;
        }
        return false;

    }

//    public static void clearAndLogout() {
//        saveSessionExpiredData(false);
//        UpasargaApplication.getAppContext().startActivity(new Intent(UpasargaApplication.getAppContext(), SplashScreenActivity.class));
//
//    }

//    public static void saveLoginResponse(LoginModel loginResponse) {
//        String json = new GsonBuilder().create().toJson(loginResponse);
//        SharedPreferences.Editor editor = UpasargaApplication.getSharedPreference().edit();
//        editor.putString(SharedPrefConstants.LOGIN_RESPONSE, json);
//        editor.putBoolean(SharedPrefConstants.IS_FIRST_LOGIN, true);
//        editor.apply();
//    }
//
//    public static LoginModel getLoginResponse() {
//        String savedUserResponse = UpasargaApplication.getSharedPreference().getString(SharedPrefConstants.LOGIN_RESPONSE, null);
//        return new GsonBuilder().create().fromJson(savedUserResponse, LoginModel.class);
//    }

}

