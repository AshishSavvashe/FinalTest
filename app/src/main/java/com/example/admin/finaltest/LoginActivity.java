package com.example.admin.finaltest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.finaltest.Constant.SharedPreferencesName;
import com.example.admin.finaltest.Model.AsteriskPasswordTransformationMethod;
import com.example.admin.finaltest.Model.CommonUtil;
import com.example.admin.finaltest.Model.InternetConnectionStatus;
import com.example.admin.finaltest.Model.LoginResponse;
import com.example.admin.finaltest.Model.User;
import com.example.admin.finaltest.retrofit.RestClient;
import com.example.admin.finaltest.retrofit.WebServices;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.example.admin.finaltest.Constant.SharedPreferencesName.USERNAME;


public class LoginActivity extends AppCompatActivity implements   View.OnClickListener,Callback<LoginResponse>{

    private EditText etUserName, etPassword;
    private Button btnShowPassword, btnUserLogin;
    private boolean saveLogin, isUserLogin, isSocialLogin = false;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initview();
    }

    private void initview() {

        //EditText Initialization
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnShowPassword = (Button) findViewById(R.id.btnShowPassword);
        btnUserLogin = (Button) findViewById(R.id.btnUserLogin);

        btnShowPassword.setOnClickListener(this);
        btnUserLogin.setOnClickListener(this);

        //Set Filters on EditText
        etUserName.setFilters(new InputFilter[]{CommonUtil.filter});
        etPassword.setFilters(new InputFilter[]{CommonUtil.filter});

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btnShowPassword:
                if (btnShowPassword.getText().equals(getResources().getString(R.string.hide))) {
                    etPassword.setTransformationMethod(new AsteriskPasswordTransformationMethod());
                    btnShowPassword.setText(getResources().getString(R.string.show));
                } else if (btnShowPassword.getText().equals(getResources().getString(R.string.show))) {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    btnShowPassword.setText(getResources().getString(R.string.hide));
                }
                break;

            case R.id.btnUserLogin:
                userlogin();

            break;
        }

    }

    private void userlogin() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestClient.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebServices apiInterface = retrofit.create(WebServices.class);

        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("email", "peter@klaven");
            paramObject.put("pass", "cityslicka");

            Call<LoginResponse> loginCall = apiInterface.getUser(paramObject.toString());
            loginCall.enqueue((Callback<LoginResponse>) this);
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
        Log.d("Error", String.valueOf(response));

        Intent i = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(i);

        Toast.makeText(this, "Login Done", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFailure(Call<LoginResponse> call, Throwable t) {
        Toast.makeText(this, "please Enter Valid Email-Id and Password", Toast.LENGTH_SHORT).show();

    }


    private boolean validateLoginForm(EditText etEmail, EditText etPassword) {

        if (!CommonUtil.validateEmail(LoginActivity.this, etEmail)) {
            return false;
        }
        if (etPassword.getText().toString().trim().isEmpty()) {
            etPassword.setError(getString(R.string.please_enter_password));
            return false;
        }
        return true;

    }



}
