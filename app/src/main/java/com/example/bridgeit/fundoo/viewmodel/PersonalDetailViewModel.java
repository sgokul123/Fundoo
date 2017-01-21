package com.example.bridgeit.fundoo.viewmodel;

import android.content.Context;
import android.util.Log;

import com.example.bridgeit.fundoo.callback.LoginInterface;
import com.example.bridgeit.fundoo.callback.PersonalDetailInterface;
import com.example.bridgeit.fundoo.controller.PersonalDetailController;
import com.example.bridgeit.fundoo.model.PersonalDetailsModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bridgeit on 16/1/17.
 */
public class PersonalDetailViewModel {

    Context mContext;
    public PersonalDetailViewModel() {
    }

    public PersonalDetailViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void personalData(RequestParams requestParams, final PersonalDetailInterface personalDetailInterface){
       /* SharedPreferences mSharedPreferences = mContext.getSharedPreferences("RECORDS",Context.MODE_PRIVATE);
        String data =mSharedPreferences.getString("token",null);
        params.put("token",data);
        Log.i("PersnoalDetailViewModel", "personalData: " +data);*/
        //Creating Constructor object to get DATA from Controller to ViewModel
         PersonalDetailController personalController=new PersonalDetailController();
        //Calling the interface through interface
        personalController.getPersonDetailController(requestParams, new LoginInterface() {
            @Override
            public void dataValidation(byte[] bytes) {
                try {
                    JSONObject jsonObject = new JSONObject( new String(bytes));
                    JSONObject childObject =jsonObject.getJSONObject("personalData");
                    for (int i = 0; i <childObject.length() ; i++) {
                        PersonalDetailsModel personalModel = new PersonalDetailsModel();
                        personalModel.setAnnualSalary(childObject.getString("annualSalary"));
                        Log.i("personalViewModel", "dataValidation: " +personalModel.getAnnualSalary());

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}
