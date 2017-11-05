package com.example.admin.finaltest.Model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.admin.finaltest.R;

/**
 * Created by Admin on 05-11-2017.
 */

public class InternetConnectionStatus {

    static Context context;
    private static InternetConnectionStatus instance = new InternetConnectionStatus();
    ConnectivityManager connectManager;

    boolean connected = false;

    public static InternetConnectionStatus getInstance(Context ctx) {
        context = ctx;
        return instance;
    }

    public boolean isOnline() {
        try {
            connectManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable()
                    && networkInfo.isConnected();

            if(!connected)
                CommonUtil.showSingleButtonPopup(context,context.getString(R.string.internetConnectionError));
            return connected;

        } catch (Exception e) {
            System.out
                    .println("CheckConnectivity Exception: " + e.getMessage());
            Log.v("connectivity", e.toString());
        }
        if(!connected)
            CommonUtil.showSingleButtonPopup(context,context.getString(R.string.internetConnectionError));
        return connected;
    }
}
