package com.fundoohr.bridgeit.fundoohr.viewmodel;

import android.util.Log;

import com.fundoohr.bridgeit.fundoohr.callback.PersonalDetailArrayInterface;
import com.fundoohr.bridgeit.fundoohr.callback.PersonalDetailBInterface;
import com.fundoohr.bridgeit.fundoohr.controller.UpdatePersonalController;
import com.fundoohr.bridgeit.fundoohr.model.UpdatePersonalModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bridgeit on 27/1/17.
 */
public class UpdatePersonalViewMOdel {
    public void updatePersonData(String token, RequestParams requestParams,
                                 final PersonalDetailArrayInterface personalDetailArrayInterface){
        UpdatePersonalController updatePersonalController = new UpdatePersonalController();
        updatePersonalController.updateController(token,requestParams, new PersonalDetailBInterface() {
            @Override
            public void personalViewModelData(byte[] bytes) {
                 if(bytes != null){
                     try {
                         JSONObject object= new JSONObject(new String(bytes));
                         UpdatePersonalModel UpPersonalModel = new UpdatePersonalModel();
                         UpPersonalModel.setmMessage(object.getString("status"));
                         UpPersonalModel.setmMessage(object.getString("message"));
                         Log.i("UpPersonalVM", "personalViewModelData: "+object.getString("message"));
                         personalDetailArrayInterface.updatePersonalDetail(UpPersonalModel);
                     }catch (JSONException e) {
                         e.printStackTrace();
                     }


                 }
            }
        });

    }
}
