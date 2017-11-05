package com.example.admin.finaltest.retrofit;

import com.example.admin.finaltest.Model.LoginResponse;
import com.example.admin.finaltest.Model.StringConverter;

import retrofit.RestAdapter;
import retrofit2.Call;

/**
 * Created by Admin on 04-11-2017.
 */

public class RestClient {

    private static WebServices webServices = null;


    public static final String BASE_URL = "https://reqres.in/";


    private RestClient() {
    }


    public static WebServices getWebServices() {
        if (webServices == null) {
            webServices = new RestAdapter.Builder()
                    .setEndpoint(BASE_URL)
                    .setConverter(new StringConverter())
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build()
                    .create(WebServices.class);

        }
        return webServices;
    }


}
