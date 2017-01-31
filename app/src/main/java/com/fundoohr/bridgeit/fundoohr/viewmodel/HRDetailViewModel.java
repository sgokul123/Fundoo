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
