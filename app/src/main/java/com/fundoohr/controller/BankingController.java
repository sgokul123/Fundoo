package com.fundoohr.controller;

import android.util.Log;

import com.fundoohr.callback.BankingDetailBInterface;
import com.fundoohr.callback.PersonalDetailBInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 24/1/17.
 * * * Purpose:
 * It is data controller in mvvm arch.
 * It Will Act Like A Manager Which WillControls The Flow Of Data In Between
 * The Models and Views .Controller Will Get The Data From The Server
 * And Will Pass Data To viewmodel.
 * It will interact with rest service to get data with the cloud
 * It encapsulates content info model
 * This provides interface for viewmodel to interact with the controller
 * essentially abstracting the service layer data model.
 */
public class BankingController {
    private static String TAG ="BankingController";
    public void getBankControllerData(String token, String mBank_url, RequestParams params, final BankingDetailBInterface bankingDetailBInterface){

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("x-token",token);
        asyncHttpClient.get(mBank_url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
               if(responseBody != null){
                   bankingDetailBInterface.bankByteData(responseBody);
                   Log.i("bankController", "onSuccess: "+responseBody);
               }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("Data Available", "Failure: " +statusCode);
                bankingDetailBInterface.closeDialog();
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
