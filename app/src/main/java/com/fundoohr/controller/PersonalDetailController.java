package com.fundoohr.controller;

import android.util.Log;

import com.fundoohr.callback.PersonalDetailBInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 16/1/17.
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
public class PersonalDetailController {
    private  static String TAG="PersonalDetailController";
    public void getPersonDetailController(String token, String mPersonal_url, RequestParams params,
                                          final PersonalDetailBInterface personalDetailInterface){
        AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.addHeader("x-token",token);
        asyncHttpClient.get(mPersonal_url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(responseBody!= null){
                    personalDetailInterface.personalViewModelData(responseBody);
                    Log.i(TAG,"onSuccess: ");
                    Log.i(TAG,"getPersonDetailController: " + responseBody);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("Data Available", "Failure: " +statusCode);
                personalDetailInterface.closeDialog();
            }
        });

    }
}
