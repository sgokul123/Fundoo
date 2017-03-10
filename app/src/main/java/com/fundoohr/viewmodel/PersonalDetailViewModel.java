package com.fundoohr.viewmodel;

import android.content.Context;

import com.fundoohr.callback.PersonalDetailArrayInterface;
import com.fundoohr.callback.PersonalDetailBInterface;
import com.fundoohr.controller.PersonalDetailController;
import com.fundoohr.model.PersonalDetailsModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bridgeit on 16/1/17.
 * * * Purpose:
 * 1.This Class Is The ViewModel Of MVVM Design Pattern.
 * 2.Holding The Model Required For The Content View List.
 * 3.This Class Has The ContentListController Object To Retrieve The Necessary
 * Model.
 * Carries The Required Field Data To The View That Is If You Want Data From
 * Multiple Tables.
 * As In Eg:
 * Here We Can Take The Necessary Data And Pass It To The View.
 */
public class PersonalDetailViewModel {
    private static String TAG ="PersonalDetailViewModel";
    Context mContext;
    public PersonalDetailViewModel() {
    }

    public PersonalDetailViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void personalData(String token, String mPersonal_url, RequestParams requestParams, final PersonalDetailArrayInterface personalDetailInterface){
        final ArrayList<PersonalDetailsModel>  personalDetailsModels = new ArrayList<>();

         PersonalDetailController personalController=new PersonalDetailController();
        //Calling the interface through interface
        personalController.getPersonDetailController(token,mPersonal_url,requestParams, new PersonalDetailBInterface() {
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

            @Override
            public void closeDialog() {
                personalDetailInterface.closeDialog();
            }
        });
    }

}
