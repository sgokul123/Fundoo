package com.fundoohr.bridgeit.fundoohr.controller;

import android.util.Log;

import com.fundoohr.bridgeit.fundoohr.callback.TrackingDetailBInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 24/1/17.
 */
public class TrackinController {
    public void trackDataController(String token, String mTrack_url, RequestParams requestParams, final TrackingDetailBInterface trackingDetailBInterface){
        Log.i("Track ", "trackDataController: "+requestParams);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("x-token",token);
        asyncHttpClient.get(mTrack_url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                trackingDetailBInterface.trackData(responseBody);
                Log.i("track", "onSuccess: ");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("Data Available", "Failure: " +statusCode);
            }
        });
    }




}
