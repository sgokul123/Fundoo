package com.fundoohr.controller;

import android.util.Log;

import com.fundoohr.callback.AttendenceBInterface;
import com.fundoohr.callback.DayAttendanceInterface;
import com.fundoohr.callback.PersonalDetailBInterface;
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
    private  static String TAG ="AttendenceController";
    public void getAttendenceData(String attenURl,String tokenValue, RequestParams requestParams,final AttendenceBInterface attendenceBInterface){
        Log.i(TAG, "getAttendenceData: "+requestParams);
        AsyncHttpClient asyncHttpClient =new AsyncHttpClient();
        asyncHttpClient.addHeader("x-token",tokenValue);
        asyncHttpClient.get(attenURl, requestParams,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        attendenceBInterface.getAttendBData(responseBody);
                        try {
                            String str = new String(responseBody, "UTF-8");
                            Log.i(TAG, "onSuccess:  data fetch "+responseBody.length +str);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        attendenceBInterface.onFailuarBack(responseBody);
                        Log.i(TAG, "Failure: " +statusCode);
                        attendenceBInterface.closeDialog();

                    }
                });

    }

    public void updateController(String token,String str_url, RequestParams params,
                                 final DayAttendanceInterface dayAttendanceInterface) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("x-token", token);
        Log.i(TAG, " updateToken: " + str_url);
        Log.i(TAG, " updateToken:  " + params);
        try{
                asyncHttpClient.post(str_url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Log.i(TAG, "onSuccess update: " + responseBody);
                        dayAttendanceInterface.dayattendanceArrayList(responseBody);

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        Log.i(TAG, "onFailure update: " + statusCode);
                        dayAttendanceInterface.closeDialog();
                        dayAttendanceInterface.dayattendanceArrayList(responseBody);

                    }
                });
            }catch(Exception e){
            dayAttendanceInterface.closeDialog();
        }
        finally {

        }
    }
}
