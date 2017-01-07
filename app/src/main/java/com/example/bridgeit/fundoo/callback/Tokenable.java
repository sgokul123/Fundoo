package com.example.bridgeit.fundoo.callback;

import com.example.bridgeit.fundoo.model.LoginModel;
import com.example.bridgeit.fundoo.viewmodel.LoginViewModel;

/**
 * Created by bridgeit on 6/1/17.
 */
public interface Tokenable {
    void getLoginData(LoginModel loginData);
}
