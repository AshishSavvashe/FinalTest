package com.example.admin.finaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.admin.finaltest.Adapter.UserdataAdapter;
import com.example.admin.finaltest.Model.CommonUtil;
import com.example.admin.finaltest.Model.Datum;
import com.example.admin.finaltest.Model.ProgressDialog;
import com.example.admin.finaltest.Model.User;
import com.example.admin.finaltest.retrofit.RestClient;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {



    private RecyclerView mRecyclerView,mServiceRecyclerView;


    private UserdataAdapter userdataAdapter;
    private ArrayList<Datum> userlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }

    private void initview() {

        mServiceRecyclerView = (RecyclerView) findViewById(R.id.mServiceRecyclerView);
        mServiceRecyclerView.setHasFixedSize(true);
        mServiceRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));

        apigetuserdat();
    }

    private void apigetuserdat() {

        ProgressDialog.showProgressDialog(MainActivity.this,getResources().getString(R.string.loading));

        RestClient.getWebServices().getAllUser(new Callback<String>(){

            @Override
            public void success(String s, Response response) {
                ProgressDialog.dismissProgressDialog();
                userlist = new ArrayList<>();
                User mUser = new Gson().fromJson(s, User.class);
                userlist =  mUser.getTotalPages();
                userdataAdapter = new UserdataAdapter(MainActivity.this, userlist);
                mServiceRecyclerView.setAdapter(userdataAdapter);
                mServiceRecyclerView.setVisibility(View.VISIBLE);

            }

            @Override
            public void failure(RetrofitError error) {
                ProgressDialog.dismissProgressDialog();

            }
        });
    }




}
