package com.fundoohr.bridgeit.fundoohr.controller;

import android.util.Log;

import com.fundoohr.bridgeit.fundoohr.callback.PersonalDetailBInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 16/1/17.
 */
public class PersonalDetailController {
    public void getPersonDetailController(String mPersonal_url, RequestParams params, final PersonalDetailBInterface personalDetailInterface){
        AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.get(mPersonal_url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(responseBody!= null){
                    personalDetailInterface.personalViewModelData(responseBody);
                    Log.i("PersonalDC","onSuccess: ");
                    Log.i("Data is in Contro","getPersonDetailController: " + responseBody);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("Data Available", "Failure: " +statusCode);
            }
        });

    }
}
