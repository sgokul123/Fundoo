package com.fundoohr.controller;

import android.content.Context;
import android.util.Log;

import com.fundoohr.callback.HRDetailBInterface;
import com.fundoohr.model.HRDetailsModel;
import com.fundoohr.utility.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.ResponseHandler;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPut;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * Created by bridgeit on 23/1/17.
 * * Purpose:
 * It is data controller in mvvm arch.
 * It Will Act Like A Manager Which WillControls The Flow Of Data In Between
 * The Models and Views .Controller Will Get The Data From The Server
 * And Will Pass Data To viewmodel.
 * It will interact with rest service to get data with the cloud
 * It encapsulates content info model
 * This provides interface for viewmodel to interact with the controller
 * essentially abstracting the service layer data model.
 */
public class HRController {
    private static   String TAG ="HRController";

    public  HRController(){
        Log.i(TAG, "controller: ");
    }

    public void getHRControllerData(String token, String mHR_url, RequestParams requestParams, final HRDetailBInterface hrDetailBInterface){

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("x-token",token);

        asyncHttpClient.get(mHR_url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                hrDetailBInterface.getHrData(responseBody);
                Log.i("hrController", "onSuccess: "+responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("Data Available", "Failure: " +statusCode);
                hrDetailBInterface.closeDialog();
            }
        });

    }

    public void updateHRDetails(String token, String mHR_url, RequestParams requestParams, final HRDetailBInterface hrDetailBInterface) {
        Log.i(TAG, "controller: "+mHR_url+token);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader("x-token",token);
        asyncHttpClient.put(mHR_url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String st=responseBody.toString();
                hrDetailBInterface.getStatusofUpdate(st);
                Log.i("hrController", "onSuccess: "+st);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("Data Available", "Failure: " +statusCode);
                hrDetailBInterface.closeDialog();
            }
        });


    }


}

/*
*
        HttpClient client;
        client = new DefaultHttpClient();
        URL url = null;
        try {
            url = new URL(mHR_url);
            HttpPut put= new HttpPut(String.valueOf(url));

            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair(Constants.RequestParam.HIRINGCITY, requestParams.getHiringCity()));
            pairs.add(new BasicNameValuePair(Constants.RequestParam.FELLOWSHIPPERIOD,requestParams.getFellowshipPeriod()));
            pairs.add(new BasicNameValuePair(Constants.RequestParam.BLSTARTDATE,requestParams.getBlStartDate()));
            pairs.add(new BasicNameValuePair(Constants.RequestParam.COMPANYJOINDATE,requestParams.getCompanyJoinDate()));
            pairs.add(new BasicNameValuePair(Constants.RequestParam.COMPONYLEAVEDATE,requestParams.getCompanyLeaveDate()));
            pairs.add(new BasicNameValuePair(Constants.RequestParam.COMPCONTRACTSIGNED,requestParams.getCompanyLeaveDate()));
            pairs.add(new BasicNameValuePair(Constants.RequestParam.ENGGCONTRACTINITIATED,requestParams.getEnggContractInitiated()));
            pairs.add(new BasicNameValuePair(Constants.RequestParam.ENGGCONTRACTSIGNED,requestParams.getEnggContractSigned()));
            pairs.add(new BasicNameValuePair(Constants.RequestParam.COMPCONTRACTINITIATED,requestParams.getCompContractInitiated()));
            pairs.add(new BasicNameValuePair(Constants.RequestParam.CONTRACTSIGNDATE,requestParams.getConntractSignDate()));
            pairs.add(new BasicNameValuePair(Constants.RequestParam.INITIATETRANSFER,requestParams.getInitiateTransfer()));
            pairs.add(new BasicNameValuePair(Constants.RequestParam.EMPLOYEESTATUS,requestParams.getmEmployeeStatus()));
            pairs.add(new BasicNameValuePair(Constants.RequestParam.NCOMPANY,requestParams.getmCompany()));
            Log.i(TAG, "controller: "+pairs);
            //put.setHeader("x-token",token);
            put.setEntity(new UrlEncodedFormEntity(pairs));
            put.setHeader("Content-type", "application/x-www-form-urlencoded");
            HttpResponse response = client.execute(put);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


/*//  RequestParams request=new RequestParams(requestParams);
        //RequestParams praParams = new RequestParams();
                 /* praParams.put("token","'jvbudvhdgd");*/
       /* praParams.put("engineerId", mId.getText().toString());
        praParams.put("emailId", mEmail.getText().toString());
        praParams.put("mobile", mMobile.getText().toString());
        Log.i("mobile", "onClick: " + mId.getText().toString());
        praParams.put("dateOfBirth", mDOB.getText().toString());
        praParams.put("fatherName", mFathername.getText().toString());
        praParams.put("fatherMobile", mFathetmob.getText().toString());
        praParams.put("occupation", mOccupation.getText().toString());
        praParams.put("annualSalary", mAnnualsalary.getText().toString());
        praParams.put("mumbaiAddress", mMumAdd.getText().toString());
        praParams.put("permanentAddress", mPermanentAdd.getText().toString());*/