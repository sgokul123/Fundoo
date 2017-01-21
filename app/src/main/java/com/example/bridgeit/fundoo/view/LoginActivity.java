package com.example.bridgeit.fundoo.view;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.example.bridgeit.fundoo.viewmodel.EnggFragViewModel;
import com.example.bridgeit.fundoo.viewmodel.LoginViewModel;


import java.util.ArrayList;


//LoginActivity that extends AppCompatActivity and implements the Tokenable Interface
public class LoginActivity extends AppCompatActivity implements Tokenable {
    EditText mUsername, mPassword;
    Button mClick;
    String name;
    String pswrd;
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
                //progress dailog box
                mProgressDialog = new ProgressDialog(LoginActivity.this);
                mProgressDialog.setMessage("Wait..Login....is in Process");
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
                name = mUsername.getText().toString();
                pswrd = mPassword.getText().toString();
                passingData(name, pswrd);

            }
        });
    }

    //Passing the data to viewModel to the method CheckData
    private void passingData(String name, String pswrd) {
        LoginViewModel viewModel = new LoginViewModel();
        viewModel.checkData(name, pswrd, this);
    }

    //Getting the dat back from View model through getLoginDat method from Tokenable  interface
    @Override
    public void getLoginData(LoginModel loginData) {
        mProgressDialog.dismiss();


        String message = loginData.getMessage();
        String token = loginData.getToken();
        int status = loginData.getStatus();

        if (status == 200) {
            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            //Sending the data to next class through sharedprefernces
            mSharedPreferences = getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor= mSharedPreferences.edit();
            SharedPreferences.Editor editor1=mSharedPreferences.edit();
            editor.putString("token", token);
            editor.commit();
            Intent intent = new Intent(LoginActivity.this, DashBoard.class);
            startActivity(intent);

           // mSharedPreferences.getString(token,null);*/


        } else {
            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

        }
        //After geeting the token Value ,the token is sent to the next ViewModel "EnggFragViewModel" to its method employeeList

       /* EnggFragViewModel enggFragViewModel = new EnggFragViewModel(LoginActivity.this);
        enggFragViewModel.employeeList();*/
    }

}


