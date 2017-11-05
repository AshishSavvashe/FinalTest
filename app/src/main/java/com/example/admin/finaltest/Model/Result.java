package com.example.admin.finaltest.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 05-11-2017.
 */

public class Result {

    @SerializedName("error")
    private Boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("LoginResponse")
    private LoginResponse loginResponse;


    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }
}
