package com.fundoohr.bridgeit.fundoohr.viewmodel;

import com.fundoohr.bridgeit.fundoohr.callback.HRDetailArrayInterface;
import com.fundoohr.bridgeit.fundoohr.callback.HRDetailBInterface;
import com.fundoohr.bridgeit.fundoohr.controller.HRController;
import com.fundoohr.bridgeit.fundoohr.model.HRDetailsModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bridgeit on 23/1/17.
 * * Purpose:
 * 1.This Class Is The ViewModel Of MVVM Design Pattern.
 * 2.Holding The Model Required For The Content View List.
 * 3.This Class Has The ContentListController Object To Retrieve The Necessary
 * Model.
 * Carries The Required Field Data To The View That Is If You Want Data From
 * Multiple Tables.
 * As In Eg:
 * Here We Can Take The Necessary Data And Pass It To The View.
 */
public class HRDetailViewModel {
    public void hrDataList(String token, String mHR_url, RequestParams requestParams, final HRDetailArrayInterface hrDetailArrayInterface){
        final ArrayList<HRDetailsModel> hrDetails = new ArrayList<>();

        //Making a Call to the Controller..
        final HRController hrController = new HRController();
        hrController.getHRControllerData(token,mHR_url,requestParams,new HRDetailBInterface(){


            @Override
            public void getHrData(byte[] bytes) {
                try {
                    JSONObject jsonObject=new JSONObject(new String(bytes));
                    JSONObject jsonChildObject = jsonObject.getJSONObject("hrData");
                    for (int i = 0; i <jsonChildObject.length(); i++) {
                        HRDetailsModel hrModel = new HRDetailsModel();
                        hrModel.setBlStartDate(jsonChildObject.getString("blStartDate"));
                        hrModel.setCompContractInitiated(jsonChildObject.getString("compContractInitiated"));
                        hrModel.setCompContractSigned(jsonChildObject.getString("compContractSigned"));
                        hrModel.setCompanyJoinDate(jsonChildObject.getString("companyJoinDate"));
                        hrModel.setCompanyLeaveDate(jsonChildObject.getString("companyLeaveDate"));
                        hrModel.setConntractSignDate(jsonChildObject.getString("contractSignDate"));
                        hrModel.setEnggContractInitiated(jsonChildObject.getString("enggContractInitiated"));
                        hrModel.setEnggContractSigned(jsonChildObject.getString("enggContractSigned"));
                        hrModel.setFellowshipPeriod(jsonChildObject.getString("fellowshipPeriod"));
                        hrModel.setHiringCity(jsonChildObject.getString("hiringCity"));
                        hrModel.setInitiateTransfer(jsonChildObject.getString("initiateTransfer"));
                        hrDetails.add(hrModel);
                    }
                    hrDetailArrayInterface.hrDetailData(hrDetails);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
