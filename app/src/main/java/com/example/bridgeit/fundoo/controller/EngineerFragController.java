package com.example.bridgeit.fundoo.controller;

import android.util.Log;

import com.example.bridgeit.fundoo.callback.EnggFragInterface;
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
    public void getEnggFragController(RequestParams params, EnggFragInterface enggFragInterface){
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get("http://192.168.0.171:3000/searchEmployeeByName", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                       JSONObject jsonObject = null;
                try {
                     jsonObject = new JSONObject(new String( responseBody));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    String token = (String) jsonObject.get("token");
                    Log.i("EngiController", "onSuccess: "+token);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }
}
