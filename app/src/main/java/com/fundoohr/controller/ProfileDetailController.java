package com.fundoohr.controller;

import android.util.Log;

import com.fundoohr.callback.PersonalDetailBInterface;
import com.fundoohr.callback.ProfileDetailBInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 23/1/17.
 * * Purpose:
 * It is data controller in mvvm arch.
 * It Will Act Like A Manager Which WillControls The Flow Of Data In Between
 * The Models and Views .Controller Will Get The Data From The Server
 * And Will Pass Data To viewmodel.
 * It will interact with rest service to get data with the cloud
 * It encapsulates content info model
 * This provides interface for viewmodel to interact with the controller
 * essentially abstracting the service layer data model.
 */
public class ProfileDetailController {
     private  static  String TAG ="ProfileDetailController";
    public void getProfileDetailController(String token, String mProfile_url, final RequestParams params, final ProfileDetailBInterface profileInterface){
        AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.addHeader("x-token",token);
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
                profileInterface.closeDialog();
            }
        });

    }
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
