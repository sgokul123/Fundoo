package com.fundoohr.bridgeit.fundoohr.controller;

import android.util.Log;

import com.fundoohr.bridgeit.fundoohr.callback.AttendenceBInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 25/1/17.
 */
public class AttendenceController {
    public void getAttendenceData(RequestParams requestParams, final AttendenceBInterface attendenceBInterface){
        Log.i("attendence", "getAttendenceData: "+requestParams);
        AsyncHttpClient asyncHttpClient =new AsyncHttpClient();
        asyncHttpClient.get("http://192.168.0.17:3000/readEmployeeMonthlyAttendance", requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                  attendenceBInterface.getAttendBData(responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("Data Available", "Failure: " +statusCode);
            }
        });

    }
}
