package com.fundoohr.bridgeit.fundoohr.controller;

import android.util.Log;

import com.fundoohr.bridgeit.fundoohr.callback.HRDetailBInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 23/1/17.
 */
public class HRController {
    public void getHRControllerData(String mHR_url, RequestParams requestParams, final HRDetailBInterface hrDetailBInterface){
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(mHR_url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                hrDetailBInterface.getHrData(responseBody);
                Log.i("hrController", "onSuccess: "+responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("Data Available", "Failure: " +statusCode);
            }
        });

    }
}
