package com.example.bridgeit.fundoo.viewmodel;

import android.util.Log;

import com.example.bridgeit.fundoo.callback.EnggFragInterface;
import com.example.bridgeit.fundoo.controller.EngineerFragController;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

/**
 * Created by bridgeit on 7/1/17.
 */
public class EnggFragViewModel {
    RequestParams params = new RequestParams();

    public void employeeList(String employeeDataList){
        params.put("token",employeeDataList);
        Log.i("EnggViewModel", "employeeList: "+params);

        EngineerFragController enggController = new EngineerFragController();
        enggController.getEnggFragController(params, new EnggFragInterface() {
            @Override
            public void employeeData(ArrayList<Object> models) {
                Log.i("Employee", "employeeData: " +models);

            }
        });
    }
}
