package com.fundoohr.bridgeit.fundoohr.viewmodel;

import android.util.Log;

import com.fundoohr.bridgeit.fundoohr.callback.LoginInterface;
import com.fundoohr.bridgeit.fundoohr.callback.TokenableInterface;
import com.fundoohr.bridgeit.fundoohr.controller.LoginController;
import com.fundoohr.bridgeit.fundoohr.model.LoginModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**Created by bridgeit on 6/1/17.*/


public class LoginViewModel {

    RequestParams params= new RequestParams();

    public  void checkData(String email, String password,String login_url, final TokenableInterface tokenableInterface){
        params.put("emailId",email);
        params.put("password",password);

        Log.i("LogiViewModel", "checkData: "+login_url);

        //Creating the controller Class object to get data through interface
        LoginController controller =new LoginController();
        controller.getLoginController(login_url,params , new LoginInterface() {

            @Override
            public void dataValidation(byte[] bytes) {

                if (bytes != null){
                    try {
                        JSONObject object= new JSONObject(new String(bytes));
                        LoginModel loginModel=new LoginModel();
                        loginModel.setMessage(object.getString("message"));
                        loginModel.setToken(object.getString("token"));
                        loginModel.setStatus(object.getInt("status"));
                        tokenableInterface.getLoginData(loginModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                /*Log.i("Login", "dataValidation: " +loginModels);
                LoginActivity loginActivity = new LoginActivity();
                loginActivity.statusMessage(loginModels);*/


            }
        });



    }
}
