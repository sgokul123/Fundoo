package com.fundoohr.view.activity;

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

import com.fundoohr.callback.ILoginCallback;
import com.fundoohr.model.LoginModel;
import com.fundoohr.viewmodel.LoginViewModel;

import java.util.regex.Pattern;

/**
 * * Purpose:
 * It Is The View Of MVVM Design Pattern.
 * It Is The UI Class Which Hold The UI Elements.
 * It Listens To Action Performed In UI class.
 * It Implements And The Observer Pattern To Listen Changes In The View model.
 * It Holds The View model To Update Its State Of The UI.
 * It is The Activity Which Need To Be Included In Manifest.xml File.
 **/
//LoginActivity that extends AppCompatActivity and implements the ILoginCallback Interface
public class LoginActivity extends AppCompatActivity implements ILoginCallback {
    private static String TAG ="LoginActivity";
    EditText mUsername, mPassword;
    EditText editTextUserName;
    Button mClick;
    String mName;
    String mPswrd;
    String mTimeMill;
    String mLogin;
    String url;

    ProgressDialog mProgressDialog;
    SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = (EditText) findViewById(R.id.email);
        mUsername.setText("admin@bridgelabz.com");
        mPassword = (EditText) findViewById(R.id.password);
        mPassword.setText("Bridge@123");
        mClick = (Button) findViewById(R.id.login);

        mClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* Intent intent = new Intent(LoginActivity.this, AttendanceDetailsActivity.class);
                startActivity(intent);*/

                //Getting the url from Strings file and passing it to ViewModel
                url=getResources().getString(R.string.url);
                mLogin = getResources().getString(R.string.login_url);
                mLogin=url+mLogin;
                mName = mUsername.getText().toString();
                mPswrd = mPassword.getText().toString();

                //Validation of login
                String userName = getResources().getString(R.string.patternMatch);
                boolean userMatch = Pattern.matches(userName, mName);

                if (!mName.equals("") && !mPswrd.equals("") && userMatch && mPswrd.length() > 5) {
                    // passingData(mName, mPswrd, mLogin);
                    internetConnectvity();
                } else {
                    Toast.makeText(LoginActivity.this, "Enter the Correct UserName & Password", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    //Checking Internet Connection
    public void internetConnectvity() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        mProgressDialog = new ProgressDialog(LoginActivity.this);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            mProgressDialog.setMessage("Wait..Login....is in Process");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            LoginViewModel viewModel = new LoginViewModel();
            Log.i("loginActivity in LVM", "onClick: " + mLogin);
            viewModel.validateUserLogin(mName, mPswrd, mLogin, this);

        } else {
            mProgressDialog.dismiss();
            Toast.makeText(LoginActivity.this, "Check Connection...", Toast.LENGTH_SHORT).show();
        }

    }

    //Getting the data back from View model through loginResponse method from ILoginCallback  interface
    @Override
    public void loginResponse(LoginModel loginData, int statuscode) {
        String message = null;
        String token = null;

        mProgressDialog.dismiss();
        if (loginData != null) {
            message = loginData.getMessage();
            token = loginData.getToken();

            Log.i("LoginStatusCode", "dataValidation: " + statuscode);
        }
        if (statuscode == 0) {
            mProgressDialog.dismiss();
            Toast.makeText(LoginActivity.this, "Bad Network... :(", Toast.LENGTH_SHORT).show();
        }

        if (statuscode == 200) {
            Long timeStamp = System.currentTimeMillis();
            mTimeMill = timeStamp.toString();
            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            //Sending the data to next class through sharedprefernces
            mSharedPreferences = getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString("token", token);
            Log.i("login", "loginResponse: " + token);
            editor.commit();
           this.finish();
            Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);


        } else {
            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void closeDialog() {
        mProgressDialog.dismiss();
    }
}


