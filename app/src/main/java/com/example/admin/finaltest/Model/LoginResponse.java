package com.example.admin.finaltest.Model;

/**
 * Created by Admin on 04-11-2017.
 */

public class LoginResponse {

    public String email;
    public String password;


    public LoginResponse(String email, String password) {
        this.email = email;
        this.password = password;
    }
}