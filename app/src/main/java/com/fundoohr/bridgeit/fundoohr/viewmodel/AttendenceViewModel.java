package com.fundoohr.bridgeit.fundoohr.viewmodel;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.fundoohr.bridgeit.fundoohr.callback.AttendenceArrayInterface;
import com.fundoohr.bridgeit.fundoohr.callback.AttendenceBInterface;
import com.fundoohr.bridgeit.fundoohr.controller.AttendenceController;
import com.fundoohr.bridgeit.fundoohr.model.AttendenceModel;
import com.fundoohr.bridgeit.fundoohr.view.activity.AttendanceDetails;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bridgeit on 25/1/17.
 */
public class AttendenceViewModel {
    Context mContext;
    ArrayList<AttendenceModel> attendenceModels = new ArrayList<>();
    public AttendenceViewModel() {
    }

    public AttendenceViewModel(Context mContext) {
        this.mContext = mContext;
    }



    public void attendViewModelData(long timestamp, String mEngeerId, String tokenValue, final AttendanceDetails attendanceDetails) {
      RequestParams params = new RequestParams();
        params.put("timeStamp",timestamp);
        params.put("token",tokenValue);
        params.put("engineerId",mEngeerId);
        AttendenceController attendenceController = new AttendenceController();
        attendenceController.getAttendenceData(params, new AttendenceBInterface() {
            @Override
            public void getAttendBData(byte[] bytes) {


                try {
                    JSONObject jsonObject= new JSONObject();
                    JSONObject jsonChild = jsonObject.getJSONObject(new String(bytes));
                    for (int i = 0; i <jsonChild.length(); i++) {
                        AttendenceModel attendM = new AttendenceModel();
                        attendM.setAttendenceStatus(jsonChild.getString("attendanceStatus"));
                        attendM.setMarkedStatus(jsonChild.getString("markedStatus"));
                        attendM.setPunchIn(jsonChild.getString("punchIn"));
                        attendM.setPunchOut(jsonChild.getString("punchOut"));
                        attendM.setReason(jsonChild.getString("reason"));
                        attendenceModels.add(attendM);
                    }
                   attendanceDetails.getAttendArrayData(attendenceModels);
                    Log.i("attendViewModel", "getAttendBData: "+attendenceModels.size());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }



}

