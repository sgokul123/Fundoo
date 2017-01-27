package com.fundoohr.bridgeit.fundoohr.controller;

import android.util.Log;
import android.widget.Toast;

import com.fundoohr.bridgeit.fundoohr.callback.LoginInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/*
 * Created by bridgeit on 6/1/17.*/


public class LoginController {
    public void getLoginController(String login_url, RequestParams params, final LoginInterface loginInterface) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(login_url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(responseBody != null){
                    loginInterface.dataValidation(responseBody);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("Data Available", "Failure: " +statusCode);

    }
});

}
}