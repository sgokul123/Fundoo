package com.fundoohr.bridgeit.fundoohr.controller;

import android.util.Log;

import com.fundoohr.bridgeit.fundoohr.callback.BankingDetailBInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 24/1/17.
 */
public class BankingController {
    public void getBankControllerData(String mBank_url, RequestParams params, final BankingDetailBInterface bankingDetailBInterface){

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
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
            }
        });

    }
}
