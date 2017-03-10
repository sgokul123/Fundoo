package com.fundoohr.callback;

/**
 * Created by bridgeit on 6/1/17.
 */
public interface ILoginServiceCallback {
    public void dataValidation(byte []bytes,int statuscode);
    public  void  closeDialog();
}
