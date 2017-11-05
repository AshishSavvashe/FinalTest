package com.example.admin.finaltest.retrofit;

import com.example.admin.finaltest.Model.LoginResponse;
import com.example.admin.finaltest.Model.User;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.http.GET;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.Headers;
import retrofit2.http.POST;


/**
 * Created by Admin on 04-11-2017.
 */

public interface WebServices {

    @Headers("Content-Type: application/json")
    @POST("/api/login")
    Call<LoginResponse> getUser(@Body String loginResponse);


    @GET("/api/users?page=2")
    void getAllUser(Callback<String> callback);





}
