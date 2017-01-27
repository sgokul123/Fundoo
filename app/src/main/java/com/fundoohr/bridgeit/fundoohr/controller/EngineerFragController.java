package com.fundoohr.bridgeit.fundoohr.controller;

import android.util.Log;

import com.fundoohr.bridgeit.fundoohr.callback.EnggFragInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 7/1/17.
 */
public class EngineerFragController {
    public void getEnggFragController(String mEngineer_data, RequestParams params, final EnggFragInterface enggFragInterface) {
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(mEngineer_data, params, new AsyncHttpResponseHandler()

        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(responseBody != null){
                    enggFragInterface.employeeData(responseBody);
                    Log.i("Engi Available", "onSuccess: "+responseBody);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
              //  enggFragInterface.employeeData(responseBody);
                Log.i("Data Available", "Failure: " +statusCode);

            }
        });
    }
}
