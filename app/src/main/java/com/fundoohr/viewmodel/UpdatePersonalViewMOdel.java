package com.fundoohr.viewmodel;

import android.util.Log;

import com.fundoohr.callback.PersonalDetailArrayInterface;
import com.fundoohr.callback.PersonalDetailBInterface;
import com.fundoohr.controller.UpdatePersonalController;
import com.fundoohr.model.UpdatePersonalModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bridgeit on 27/1/17.
 */
public class UpdatePersonalViewMOdel {

    private  static  String TAG ="UpdatePersonalViewMOdel";

    public void updatePersonData(String token,String Str_url ,RequestParams requestParams, final PersonalDetailArrayInterface personalDetailArrayInterface){

        Log.i(TAG, "call to controller: ");
        UpdatePersonalController updatePersonalController = new UpdatePersonalController();
        updatePersonalController.updateController(token,Str_url,requestParams, new PersonalDetailBInterface() {
            @Override
            public void personalViewModelData(byte[] bytes) {
                 if(bytes != null){
                     try {
                         JSONObject object= new JSONObject(new String(bytes));
                         UpdatePersonalModel UpPersonalModel = new UpdatePersonalModel();
                         UpPersonalModel.setmStatus(object.getString("status"));
                         UpPersonalModel.setmMessage(object.getString("message"));
                         Log.i(TAG, "personalViewModelData: "+object.getString("message"));
                         personalDetailArrayInterface.updatePersonalDetail(UpPersonalModel);
                     }catch (JSONException e) {
                         e.printStackTrace();
                     }


                 }
            }

            @Override
            public void closeDialog() {
                personalDetailArrayInterface.closeDialog();

            }
        });

    }
}
