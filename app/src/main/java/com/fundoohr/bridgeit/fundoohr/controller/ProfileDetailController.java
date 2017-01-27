package com.fundoohr.bridgeit.fundoohr.controller;

import android.util.Log;

import com.fundoohr.bridgeit.fundoohr.callback.ProfileDetailBInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 23/1/17.
 */
public class ProfileDetailController {
    public void getProfileDetailController(String mProfile_url, final RequestParams params, final ProfileDetailBInterface profileInterface){
        AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.get(mProfile_url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (responseBody != null){
                    profileInterface.profileDataInterface(responseBody);
                    Log.i("Data is in Contro","getProfileDetailController: " + responseBody);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("Data Available", "Failure: " +statusCode);
            }
        });

    }
}
