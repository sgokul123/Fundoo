package com.fundoohr.bridgeit.fundoohr.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.fundoohr.bridgeit.fundoohr.callback.EnggFragInterface;
import com.fundoohr.bridgeit.fundoohr.viewmodel.EnggFragViewModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 7/1/17.
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
public class EngineerFragController {

    public void getEnggFragController(String mEngineer_data, String tokenHeader,
                                      final EnggFragInterface enggFragInterface) {
       // progressDialog = new ProgressDialog(mContext);
        Log.i("url", "getEnggFragController: "+mEngineer_data);
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.addHeader("x-token",tokenHeader);
        Log.i("toke Controller", "getEnggFragController: "+tokenHeader);
        httpClient.get(mEngineer_data, new AsyncHttpResponseHandler()

        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(responseBody != null){
                    enggFragInterface.employeeData(responseBody);
                    Log.i("Engi Available", "onSuccess: "+responseBody);
                   /* Toast.makeText(mContext, "Check Internet Connetion", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();*/
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
               /* enggFragInterface.employeeData(responseBody);*/
               // Toast.makeText(mContext, "Server not Responding", Toast.LENGTH_SHORT).show();
                if(statusCode== 0){
                    Log.i("Data Available", "Failure: " +statusCode);
                    //progressDialog.dismiss();
                }
            }
        });
    }
}
