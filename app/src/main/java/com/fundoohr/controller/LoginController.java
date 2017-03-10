package com.fundoohr.controller;

import android.util.Log;

import com.fundoohr.callback.ILoginServiceCallback;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/*
 * Created by bridgeit on 6/1/17.*/


public class LoginController {
    private  String TAG ="LoginController";
    public void getLoginController(String login_url, RequestParams params, final ILoginServiceCallback loginInterface) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(login_url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(responseBody != null){
                    Log.i(TAG, "onSuccess:  Auth");
                    loginInterface.dataValidation(responseBody,statusCode);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("TAG", "Failure:  Auth" +statusCode);
                loginInterface.closeDialog();

    }
});

}
}