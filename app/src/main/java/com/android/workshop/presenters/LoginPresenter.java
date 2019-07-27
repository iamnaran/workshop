package com.android.workshop.presenters;

import android.util.Log;

import com.android.workshop.apiservices.ApiClient;
import com.android.workshop.apiservices.PhotosApiService;
import com.android.workshop.helper.UpasargaCallBack;
import com.android.workshop.models.Photos;
import com.android.workshop.models.SampleData;
import com.google.gson.GsonBuilder;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class LoginPresenter {

    private WeakReference<View> view;

    public LoginPresenter(LoginPresenter.View view) {
        this.view = new WeakReference<>(view);

    }

    private LoginPresenter.View getView() throws NullPointerException {
        if (view != null)
            return view.get();
        else
            throw new NullPointerException("View is unavailable");
    }


    public interface View {
        void onResponseSuccess(List<Photos> sampleData);

    }





}
