package com.example.admin.finaltest.retrofit;

import com.example.admin.finaltest.Model.LoginResponse;
import com.example.admin.finaltest.Model.Result;
import com.example.admin.finaltest.Model.User;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;

/**
 * Created by Admin on 04-11-2017.
 */

public interface WebServices {


    @POST("/api/login")
    Call<LoginResponse> getUser();

    @GET("/api/users?page=2")
    void getAllUser(Callback<String> callback);


    @FormUrlEncoded
    @POST("/api/login")
    Call<Result> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );


}
