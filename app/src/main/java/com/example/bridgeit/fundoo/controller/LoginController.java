package com.example.bridgeit.fundoo.controller;

import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.bridgeit.fundoo.callback.LoginInterface;
import com.example.bridgeit.fundoo.model.LoginModel;
import com.example.bridgeit.fundoo.view.LoginActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/*
 * Created by bridgeit on 6/1/17.*/


public class LoginController {
    public void getLoginController(RequestParams params,final LoginInterface loginInterface) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://192.168.0.9:3000/login", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                loginInterface.dataValidation(responseBody);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                loginInterface.dataValidation(responseBody);

    }
});

}
}