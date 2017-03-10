package com.fundoohr.viewmodel;

import android.content.Context;
import android.util.Log;

import com.fundoohr.callback.AttendenceArrayInterface;
import com.fundoohr.callback.AttendenceBInterface;
import com.fundoohr.callback.DayAttendanceInterface;
import com.fundoohr.callback.PersonalDetailArrayInterface;
import com.fundoohr.callback.PersonalDetailBInterface;
import com.fundoohr.controller.AttendenceController;
import com.fundoohr.controller.UpdatePersonalController;
import com.fundoohr.model.AttendenceModel;
import com.fundoohr.model.UpdatePersonalModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by bridgeit on 25/1/17.
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
public class AttendenceViewModel {
    private static String TAG ="AttendenceViewModel";
    Context mContext;
    ArrayList<AttendenceModel> attendenceModels = new ArrayList<>();

    public AttendenceViewModel() {
    }

    public AttendenceViewModel(Context mContext) {
        this.mContext = mContext;
    }


    public void attendViewModelData(String URL, long timestamp, String mEngeerId, String tokenValue,
                                    final AttendenceArrayInterface attendenceArrayInterface) {
        RequestParams params = new RequestParams();
        params.put("timeStamp", timestamp);
        params.put("engineerId", mEngeerId);
        AttendenceController attendenceController = new AttendenceController();

        attendenceController.getAttendenceData(URL, tokenValue, params, new AttendenceBInterface() {
            @Override
            public void getAttendBData(byte[] bytes) {
                try {
                    JSONObject jsonObject = new JSONObject(new String(bytes));
                    Log.i(TAG, "getAttendBData: " + jsonObject.toString());

                    JSONObject jsonChild = jsonObject.getJSONObject("attendanceData");
                    Log.i(TAG, "getAttendBData: ....." + jsonChild);
                    JSONObject jsonChild1 = null;
                    //Getting

                    Iterator<String> keys = jsonChild.keys();
                    Log.i(TAG, "getAttendBData: key " + keys);
                    while (keys.hasNext()) {
                        String keyValue = (String) keys.next();

                        jsonChild1 = jsonChild.getJSONObject(keyValue);
                        Log.i(TAG, "getAttendBData: " + keyValue);
                        Log.i(TAG, "getAttendBData: " + jsonChild1);

                        AttendenceModel attendM = new AttendenceModel();
                        attendM.setMarkedStatus(jsonChild1.getString("markedStatus"));
                        attendM.setDays(keyValue);
                        attendM.setAttendenceStatus(jsonChild1.getString("attendanceStatus"));
                        attendM.setPunchIn(jsonChild1.getString("punchIn"));
                        attendM.setPunchOut(jsonChild1.getString("punchOut"));
                        attendM.setReason(jsonChild1.getString("reason"));
                        Log.i(TAG, "getAttendBData: " + jsonChild1.getString("attendanceStatus"));
                        attendenceModels.add(attendM);
                    }
//                    if(attendenceModels!=null && attendenceModels.size()>0)
                    attendenceArrayInterface.getAttendArrayData(attendenceModels);
                    Log.i(TAG, "getAttendBData: " + attendenceModels.size());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailuarBack(byte[] bytes) {
                JSONObject object= null;
                try {
                    object = new JSONObject(new String(bytes));
                    UpdatePersonalModel UpPersonalModel = new UpdatePersonalModel();
                    UpPersonalModel.setmStatus(object.getString("status"));
                    UpPersonalModel.setmMessage(object.getString("message"));
                    Log.i(TAG, "personalViewModelData: "+object.getString("message"));
                    attendenceArrayInterface.onFailureGetData(UpPersonalModel);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void closeDialog() {
                attendenceArrayInterface.closeDialog();
            }
        });
    }


    public void updateDayAtendance(String token,String Str_url ,RequestParams requestParams, final DayAttendanceInterface dayAttendanceInterface){

        Log.i(TAG, "call to controller: ");
        AttendenceController attendenceController = new AttendenceController();
        attendenceController.updateController(token,Str_url,requestParams, new DayAttendanceInterface() {
            @Override
            public void dayattendanceArrayList(byte[] bytes) {
                if(bytes != null){
                    try {
                        JSONObject object= new JSONObject(new String(bytes));
                        UpdatePersonalModel UpPersonalModel = new UpdatePersonalModel();
                        UpPersonalModel.setmStatus(object.getString("status"));
                        UpPersonalModel.setmMessage(object.getString("message"));
                        Log.i(TAG, "day attendance : "+object.getString("message"));
                        dayAttendanceInterface.updateDayAttendance(UpPersonalModel);
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void updateDayAttendance(UpdatePersonalModel updatePersonalModel) {

            }

            @Override
            public void closeDialog() {
                dayAttendanceInterface.closeDialog();
            }
        });

    }
}

