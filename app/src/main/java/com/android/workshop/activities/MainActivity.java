package com.android.workshop.activities;import androidx.appcompat.app.AppCompatActivity;import android.os.Bundle;import android.util.Log;import com.android.workshop.R;import com.android.workshop.models.SampleData;import com.android.workshop.presenters.LoginPresenter;import java.util.List;public class MainActivity extends AppCompatActivity implements LoginPresenter.View {    private LoginPresenter loginPresenter;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);        loginPresenter = new LoginPresenter(this);        loginPresenter.requestData();    }    @Override    public void onResponseSuccess(List<SampleData> sampleData) {    }}