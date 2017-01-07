package com.example.bridgeit.fundoo.viewmodel;

import android.util.Log;

import com.example.bridgeit.fundoo.callback.LoginInterface;
import com.example.bridgeit.fundoo.callback.Tokenable;
import com.example.bridgeit.fundoo.controller.LoginController;
import com.example.bridgeit.fundoo.model.LoginModel;
import com.example.bridgeit.fundoo.view.LoginActivity;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**Created by bridgeit on 6/1/17.*/


public class LoginViewModel {

    ArrayList<LoginModel> list;
    RequestParams params= new RequestParams();

    public  void checkData(String email, String password, final Tokenable tokenable){
        params.put("emailId",email);
        params.put("password",password);
        Log.i("LogiViewModel", "checkData: "+params);


        LoginController controller =new LoginController();
        controller.getLoginController(params , new LoginInterface() {
            @Override
            public void dataValidation(byte[] bytes) {
                try {
                    JSONObject object= new JSONObject(new String(bytes));
                    LoginModel loginModel=new LoginModel();
                    loginModel.setMessage(object.getString("message"));
                    loginModel.setToken(object.getString("token"));
                    loginModel.setStatus(object.getInt("status"));
                    tokenable.getLoginData(loginModel);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                /*Log.i("Login", "dataValidation: " +loginModels);
                LoginActivity loginActivity = new LoginActivity();
                loginActivity.statusMessage(loginModels);*/


            }
        });



    }
}
