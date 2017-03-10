package com.fundoohr.viewmodel;

import android.util.Log;

import com.fundoohr.callback.ILoginServiceCallback;
import com.fundoohr.callback.ILoginCallback;
import com.fundoohr.controller.LoginController;
import com.fundoohr.model.LoginModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**Created by bridgeit on 6/1/17.
 ** * Purpose:
 * 1.This Class Is The ViewModel Of MVVM Design Pattern.
 * 2.Holding The Model Required For The Content View List.
 * 3.This Class Has The ContentListController Object To Retrieve The Necessary
 * Model.
 * Carries The Required Field Data To The View That Is If You Want Data From
 * Multiple Tables.
 * As In Eg:
 * Here We Can Take The Necessary Data And Pass It To The View.
 */


public class LoginViewModel {
    private static String TAG ="LoginViewModel";
    RequestParams params= new RequestParams();

    public  void validateUserLogin(String email, String password, String login_url, final ILoginCallback loginCallback){
        params.put("emailId",email);
        params.put("password",password);

        Log.i("LogiViewModel", "validateUserLogin: "+login_url);

        //Creating the controller Class object to get data through interface
        LoginController controller =new LoginController();
        controller.getLoginController(login_url,params , new ILoginServiceCallback() {

            @Override
            public void dataValidation(byte[] bytes,int statuscode) {

                if (bytes != null){
                    try {
                        JSONObject object= new JSONObject(new String(bytes));
                        LoginModel loginModel=new LoginModel();
                        loginModel.setMessage(object.getString("message"));
                        loginModel.setToken(object.getString("token"));
                        loginModel.setStatus(object.getInt("status"));
                        loginCallback.loginResponse(loginModel,statuscode);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                /*Log.i("Login", "dataValidation: " +loginModels);
                LoginActivity loginActivity = new LoginActivity();
                loginActivity.statusMessage(loginModels);*/


            }

            @Override
            public void closeDialog() {
                loginCallback.closeDialog();
            }
        });



    }
}
