package com.example.bridgeit.fundoo.controller;

import android.app.ProgressDialog;
import android.util.Log;

import com.example.bridgeit.fundoo.callback.EnggFragInterface;
import com.example.bridgeit.fundoo.view.Engineer_Fragment;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 7/1/17.
 */
public class EngineerFragController {
    public void getEnggFragController(RequestParams params, final EnggFragInterface enggFragInterface) {
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get("http://192.168.0.9:3000/searchEmployeeByName", params, new AsyncHttpResponseHandler()

        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    enggFragInterface.employeeData(responseBody);
                Log.i("Data Available", "onSuccess: "+responseBody);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                enggFragInterface.employeeData(responseBody);
                Log.i("Data Available", "Failure: ");

            }
        });
    }
}
