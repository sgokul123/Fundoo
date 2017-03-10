package com.fundoohr.callback;

import com.fundoohr.model.LoginModel;

/**
 * Created by bridgeit on 6/1/17.
 */
public interface ILoginCallback {
    void loginResponse(LoginModel loginData,int statuscode);
    public void  closeDialog();
}
