package com.example.admin.finaltest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.finaltest.Constant.SharedPreferencesName;
import com.example.admin.finaltest.Model.AsteriskPasswordTransformationMethod;
import com.example.admin.finaltest.Model.CommonUtil;
import com.example.admin.finaltest.Model.LoginResponse;
import com.example.admin.finaltest.Model.Result;
import com.example.admin.finaltest.Model.User;
import com.example.admin.finaltest.retrofit.RestClient;
import com.example.admin.finaltest.retrofit.WebServices;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.provider.Telephony.Carriers.PASSWORD;
import static com.example.admin.finaltest.R.id.saveLoginCheckBox;


public class LoginActivity extends AppCompatActivity implements   View.OnClickListener, SharedPreferencesName{

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

    /*private void sendNetworkrequest(LoginResponse loginResponse) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        WebServices webservice = retrofit.create(WebServices.class);
        Call<LoginResponse> call = webservice.getUser(loginResponse);

        call.enqueue(new retrofit2.Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                Toast.makeText(LoginActivity.this, "Sucuss"+ response.body().email, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }*/

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


                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);



           // userlogin();

                /*LoginResponse loginResponse = new LoginResponse(
                        etUserName.getText().toString(),
                        etPassword.getText().toString()
                );

                sendNetworkrequest(loginResponse);*/

            break;
        }

    }

    private void userlogin() {

        String email = etUserName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        WebServices service = retrofit.create(WebServices.class);

        Call<Result> call = service.userLogin(email, password);

        call.enqueue(new retrofit2.Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, retrofit2.Response<Result> response) {

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });


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
