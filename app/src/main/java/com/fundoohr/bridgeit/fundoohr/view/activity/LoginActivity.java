package com.fundoohr.bridgeit.fundoohr.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.fundoohr.bridgeit.fundoohr.R;

import com.fundoohr.bridgeit.fundoohr.callback.TokenableInterface;
import com.fundoohr.bridgeit.fundoohr.model.LoginModel;
import com.fundoohr.bridgeit.fundoohr.viewmodel.LoginViewModel;
/**
 *
**/
//LoginActivity that extends AppCompatActivity and implements the TokenableInterface Interface
public class LoginActivity extends AppCompatActivity implements TokenableInterface {
    EditText mUsername, mPassword;
    Button mClick;
    String name;
    String pswrd;
    String mTimeMill;

    ProgressDialog mProgressDialog;
    SharedPreferences mSharedPreferences;


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
                //Checking the internet Connectivity and progress dailog box
                ConnectivityManager connectivityManager
                        = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                mProgressDialog = new ProgressDialog(LoginActivity.this);
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    String mLogin = getResources().getString(R.string.login_url);
                    Log.i("loginActivity", "onClick: " + mLogin);
                    mProgressDialog.setMessage("Wait..Login....is in Process");
                    mProgressDialog.setCancelable(false);
                    mProgressDialog.show();
                    name = mUsername.getText().toString();
                    pswrd = mPassword.getText().toString();
                    passingData(name, pswrd, mLogin);

                } else {
                    mProgressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Wait While...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Passing the data to viewModel to the method CheckData
    private void passingData(String name, String pswrd, String s) {
        LoginViewModel viewModel = new LoginViewModel();
        Log.i("loginActivity in LVM", "onClick: " + s);
        viewModel.checkData(name, pswrd, s, this);
    }

    //Getting the data back from View model through getLoginDat method from TokenableInterface  interface
    @Override
    public void getLoginData(LoginModel loginData) {
        mProgressDialog.dismiss();


        String message = loginData.getMessage();
        String token = loginData.getToken();
        int status = loginData.getStatus();

        if (status == 200) {
            Long timeStamp = System.currentTimeMillis();
            mTimeMill = timeStamp.toString();
            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            //Sending the data to next class through sharedprefernces
            mSharedPreferences = getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString("token", token);
            editor.commit();
            Intent intent = new Intent(LoginActivity.this, DashBoard.class);
            startActivity(intent);


        } else {
            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

        }

    }

}


