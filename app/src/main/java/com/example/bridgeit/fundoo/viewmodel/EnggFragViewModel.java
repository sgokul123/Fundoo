package com.example.bridgeit.fundoo.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.bridgeit.fundoo.callback.EnggFragInterface;
import com.example.bridgeit.fundoo.callback.EnggViewModelInterface;
import com.example.bridgeit.fundoo.controller.EngineerFragController;
import com.example.bridgeit.fundoo.model.EnggFragModel;
import com.example.bridgeit.fundoo.view.Engineer_Fragment;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EnggFragViewModel {

 /*   RequestParams params = new RequestParams();
    Context mContext;*/

   /* public EnggFragViewModel(Context context) {
        this.mContext=context;
    }
    //Empty Constructor
    public EnggFragViewModel() {

    }*/

    public  void employeeList(RequestParams params, final EnggViewModelInterface enggViewModelInterface){
        final ArrayList<EnggFragModel> viewModelData = new ArrayList<EnggFragModel>();
        Log.i("EnggViewModel", "employeeList:Data is Available here ");

        //Using the shared preferences to send the token through the "Value" variable
      /*  SharedPreferences mSharedPreferences = mContext.getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String value=(mSharedPreferences.getString("token",null));
        Log.i("EnggFragmentViewModel", "employeeList: token = "+value);
        params.put("", value);*/
        //Creating controller class to pass the data
        final EngineerFragController enggController = new EngineerFragController();
        enggController.getEnggFragController(params, new EnggFragInterface() {
            @Override
            public void employeeData(byte[] bytes) {
                try {
                    //As the data in rest call contained in JSONObject inside that JSONArray and inside
                    //JSONArray JSONObject is there ,Hence to get the data and set to the Model Class
                    //Creating JSON Object and obtained the data in bytes


                    JSONObject jsonObject = new JSONObject(new String(bytes));
                    Log.i("json object", "employeeList: " );
                    if (jsonObject != null) {
                        JSONArray jsonArray = jsonObject.getJSONArray("employeeList");
                        Log.i("json Array", "employeeList: " + jsonArray.length());
                        //Creating the object of modelClass

                        if (jsonArray.length() != 0) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject childObject = jsonArray.getJSONObject(i);
                                //Through the object of model Class the obtained data is set to the
                                //Model class
                                EnggFragModel enggFragModel = new EnggFragModel();
                                enggFragModel.setEmployeeName(childObject.getString("employeeName"));
                                enggFragModel.setEmployeeStatus(childObject.getString("employeeStatus"));
                                enggFragModel.setCompany(childObject.getString("company"));
                                enggFragModel.setEmployeeMobile(childObject.getString("mobile"));
                                enggFragModel.setEmployeeEmail(childObject.getString("emailId"));
                                enggFragModel.setEngineerID(childObject.getString("engineerId"));
                                Log.i("EngineerId", "employeeData: " +childObject.getString("engineerId"));
                                viewModelData.add(enggFragModel);
                                //modelList.add(enggFragModel);
                               /* String name = (String) childObject.get("employeeName");
                                Log.i("employeeData: ", "" + name);*/
                                //View Class Name

                            }


                            enggViewModelInterface.enggViewMInterface(viewModelData);
                            Log.i("Employee", "employeeList: " + viewModelData);

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.i("Employee", "employeeList: " );

            }
        });
    }
}

