package com.fundoohr.bridgeit.fundoohr.controller;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 27/1/17.
 */
public class UpdatePersonalController {
    public void updateController(RequestParams params){
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.put("http://192.168.0.17:3000/updateEmployeePersonalData", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.i("UpdatePersonal", "onSuccess: "+responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
