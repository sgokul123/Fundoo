package com.fundoohr.bridgeit.fundoohr.model;

/**
 * Created by bridgeit on 5/1/17.
 */
public class LoginModel {

    String token;
    String message;
    int status;

    public LoginModel() {
    }

    public LoginModel(String email, String password, String token, String message, int status) {

        this.token = token;
        this.message = message;
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
