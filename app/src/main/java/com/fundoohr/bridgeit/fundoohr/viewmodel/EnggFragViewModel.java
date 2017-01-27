package com.fundoohr.bridgeit.fundoohr.viewmodel;

import android.util.Log;

import com.fundoohr.bridgeit.fundoohr.callback.EnggFragInterface;
import com.fundoohr.bridgeit.fundoohr.callback.EnggViewModelInterface;
import com.fundoohr.bridgeit.fundoohr.controller.EngineerFragController;
import com.fundoohr.bridgeit.fundoohr.model.EnggFragModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EnggFragViewModel {
    public  void employeeList(String mEngineer_data, RequestParams params, final EnggViewModelInterface enggViewModelInterface){
        final ArrayList<EnggFragModel> viewModelData = new ArrayList<>();
        Log.i("EnggViewModel", "employeeList:Data is Available here ");

        //Creating controller class to pass the data
        final EngineerFragController enggController = new EngineerFragController();
        enggController.getEnggFragController(mEngineer_data,params, new EnggFragInterface() {
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

