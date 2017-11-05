package com.example.admin.finaltest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 04-11-2017.
 */

public class User {

    @SerializedName("data")
    @Expose
    private ArrayList<Datum> totalPages = new ArrayList<Datum>();

    public ArrayList<Datum> getTotalPages() {
        return totalPages;
    }
}

