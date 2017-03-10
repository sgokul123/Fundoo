package com.fundoohr.controller;

import android.util.Log;

import com.fundoohr.callback.PersonalDetailBInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 27/1/17.
 */
public class UpdatePersonalController {
    private  static  String TAG ="UpdatePersonalController";
    public void updateController(String token,String str_url, RequestParams params,
                                 final PersonalDetailBInterface personalDetailBInterface){
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("x-token",token);
        Log.i(TAG, " token: "+token);
        Log.i(TAG, " token:  "+params);
        asyncHttpClient.put(str_url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                personalDetailBInterface.personalViewModelData(responseBody);
                Log.i(TAG, "onSuccess: "+responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                personalDetailBInterface.personalViewModelData(responseBody);
                Log.i(TAG, "onFailure: "+statusCode);
                personalDetailBInterface.closeDialog();

            }
        });
    }
}
