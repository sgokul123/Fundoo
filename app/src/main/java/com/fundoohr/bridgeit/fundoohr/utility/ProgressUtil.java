package com.fundoohr.bridgeit.fundoohr.utility;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by bridgeit on 10/2/17.
 */
public class ProgressUtil {
    ProgressDialog mProgressDialog;

    public ProgressUtil(Context context){
        mProgressDialog= new ProgressDialog(context);
    }

    public void showProgress(String message){
        mProgressDialog.setMessage(message);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.show();
    }

    public void dismissProgress(){
        mProgressDialog.dismiss();
    }

}
