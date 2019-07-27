package com.android.workshop.presenters;

import android.util.Log;

import com.android.workshop.apiservices.ApiClient;
import com.android.workshop.apiservices.LoginApiService;
import com.android.workshop.helper.UpasargaCallBack;
import com.android.workshop.models.SampleData;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    private WeakReference<View> view;

    public LoginPresenter(LoginPresenter.View view) {
        this.view = new WeakReference<>(view);
        //initialiseFacebook();
    }

    private LoginPresenter.View getView() throws NullPointerException {
        if (view != null)
            return view.get();
        else
            throw new NullPointerException("View is unavailable");
    }


    public interface View {
        void onResponseSuccess(List<SampleData> sampleData);

    }


    public void requestData() {

        LoginApiService loginApiService = ApiClient.getClient().create(LoginApiService.class);
        loginApiService.requestSampleData().enqueue(new UpasargaCallBack<List<SampleData>>() {
            @Override
            public void onResponse(Call<List<SampleData>> call, Response<List<SampleData>> response) {


                if (response.isSuccessful()){

                    Log.e( "onResponse: ",new GsonBuilder().create().toJson(response));
                    getView().onResponseSuccess(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<SampleData>> call, Throwable t) {

            }
        });
    }





}
