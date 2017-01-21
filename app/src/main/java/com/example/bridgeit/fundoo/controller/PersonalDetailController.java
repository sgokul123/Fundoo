package com.example.bridgeit.fundoo.controller;

import android.util.Log;

import com.example.bridgeit.fundoo.callback.LoginInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 16/1/17.
 */
public class PersonalDetailController {
    public void getPersonDetailController(RequestParams params, final LoginInterface personalDetailInterface){
        AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.get("http://192.168.0.9:3000/readEmployeePersonalData", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                personalDetailInterface.dataValidation(responseBody);
                Log.i("PersonalDC","onSuccess: ");
                Log.i("Data is in Contro","getPersonDetailController: " + responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
               personalDetailInterface.dataValidation(responseBody);
            }
        });

    }
}
