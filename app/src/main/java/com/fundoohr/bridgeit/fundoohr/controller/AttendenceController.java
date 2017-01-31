package com.fundoohr.bridgeit.fundoohr.controller;

import android.util.Log;

import com.fundoohr.bridgeit.fundoohr.callback.AttendenceBInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 25/1/17.
 ** * Purpose:
 * It is data controller in mvvm arch.
 * It Will Act Like A Manager Which WillControls The Flow Of Data In Between
 * The Models and Views .Controller Will Get The Data From The Server
 * And Will Pass Data To viewmodel.
 * It will interact with rest service to get data with the cloud
 * It encapsulates content info model
 * This provides interface for viewmodel to interact with the controller
 * essentially abstracting the service layer data model.
 */

public class AttendenceController {
    public void getAttendenceData(String attenURl,String tokenValue, RequestParams requestParams,
                                  final AttendenceBInterface attendenceBInterface){
        Log.i("attendence", "getAttendenceData: "+requestParams);
        AsyncHttpClient asyncHttpClient =new AsyncHttpClient();
        asyncHttpClient.addHeader("x-token",tokenValue);
        asyncHttpClient.get(attenURl, requestParams,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        attendenceBInterface.getAttendBData(responseBody);

                        try {
                            String str = new String(responseBody, "UTF-8");
                            Log.i("attend contro", "onSuccess: "+responseBody.length +str);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.i("Data Available", "Failure: " +statusCode);
                    }
                });

    }
}
