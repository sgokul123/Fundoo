package com.fundoohr.bridgeit.fundoohr.viewmodel;

import android.content.Context;
import android.util.Log;

import com.fundoohr.bridgeit.fundoohr.callback.PersonalDetailArrayInterface;
import com.fundoohr.bridgeit.fundoohr.callback.PersonalDetailBInterface;
import com.fundoohr.bridgeit.fundoohr.controller.PersonalDetailController;
import com.fundoohr.bridgeit.fundoohr.model.PersonalDetailsModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

    public void personalData(String mPersonal_url, RequestParams requestParams, final PersonalDetailArrayInterface personalDetailInterface){
        final ArrayList<PersonalDetailsModel>  personalDetailsModels = new ArrayList<>();

         PersonalDetailController personalController=new PersonalDetailController();
        //Calling the interface through interface
        personalController.getPersonDetailController(mPersonal_url,requestParams, new PersonalDetailBInterface() {
            @Override
            public void personalViewModelData(byte[] bytes) {
                try {
                    JSONObject jsonObject = new JSONObject( new String(bytes));
                    JSONObject childObject =jsonObject.getJSONObject("personalData");
                    for (int i = 0; i <childObject.length() ; i++) {
                        PersonalDetailsModel personalModel = new PersonalDetailsModel();
                        personalModel.setEmailId(childObject.getString("emailId"));
                        personalModel.setMobile(childObject.getString("mobile"));
                        personalModel.setDateOfBirth(childObject.getString("dateOfBirth"));
                        personalModel.setFatherMobile(childObject.getString("fatherMobile"));
                        personalModel.setFatherName(childObject.getString("fatherName"));
                        personalModel.setOccupation(childObject.getString("occupation"));
                        personalModel.setAnnualSalary(childObject.getString("annualSalary"));
                        personalModel.setMumbaiAddress(childObject.getString("mumbaiAddress"));
                        personalModel.setPremanentAddress(childObject.getString("permenantAddress"));
                        personalDetailsModels.add(personalModel);
                    }
                    personalDetailInterface.personalArrayListDetail(personalDetailsModels);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            });
    }

}
