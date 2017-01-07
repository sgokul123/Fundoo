package com.example.bridgeit.fundoo.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.bridgeit.fundoo.R;

import com.example.bridgeit.fundoo.callback.Tokenable;
import com.example.bridgeit.fundoo.model.LoginModel;
import com.example.bridgeit.fundoo.viewmodel.LoginViewModel;


import java.util.ArrayList;



public class LoginActivity extends AppCompatActivity implements Tokenable {
    EditText mUsername, mPassword;
    Button mClick;
    String name;
    String pswrd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mClick = (Button) findViewById(R.id.login);

        mClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = mUsername.getText().toString();
                pswrd = mPassword.getText().toString();
                /*RequestParams params = new RequestParams();
                params.put("emailId", name);
                params.put("password", pswrd);*/
               /* invokesWS(params);*/
                passingData(name, pswrd);


                String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBicmlkZ2VsYWJ6LmNvbSIsImlhdCI6MTQ4Mzc5MDg0OCwiZXhwIjoxNDg1MDAwNDQ4fQ.rp7wszUwIUbliFJKd2J945Xbj7n0GkrlF1w7Cy1XHE0";
            }


        });
    }

    private void passingData(String name, String pswrd) {
        LoginViewModel viewModel = new LoginViewModel();
        viewModel.checkData(name, pswrd, this);
    }


    @Override
    public void getLoginData(LoginModel loginData) {
        String message = loginData.getMessage();
        loginData.getToken();
        int status = loginData.getStatus();
        if (status == 200) {
            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, DashBoard.class);
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

        }
    }
}


