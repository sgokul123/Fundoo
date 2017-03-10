package com.fundoohr.controller;

import android.util.Log;

import com.fundoohr.callback.PersonalDetailBInterface;
import com.fundoohr.callback.TrackingDetailBInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 24/1/17.
 */
public class TrackinController {
    private static String TAG ="TrackinController";
    public void trackDataController(String token, String mTrack_url, RequestParams requestParams, final TrackingDetailBInterface trackingDetailBInterface){
        Log.i(TAG, "trackDataController: "+requestParams);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("x-token",token);
        asyncHttpClient.get(mTrack_url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                trackingDetailBInterface.trackData(responseBody);
                Log.i(TAG, "onSuccess: ");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i(TAG, "Failure: " +statusCode);
                trackingDetailBInterface.closeDialog();
            }
        });
    }


    public void updateController(String token, String mProfile_url, RequestParams requestParams, final PersonalDetailBInterface personalDetailBInterface) {

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("x-token",token);
        Log.i(TAG, " token: "+token);
        Log.i(TAG, " token:  "+requestParams);
        asyncHttpClient.put(mProfile_url, requestParams, new AsyncHttpResponseHandler() {
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
