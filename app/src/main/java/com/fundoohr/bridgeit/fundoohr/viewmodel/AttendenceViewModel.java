package com.fundoohr.bridgeit.fundoohr.viewmodel;

import android.content.Context;
import android.util.Log;

import com.fundoohr.bridgeit.fundoohr.callback.AttendenceArrayInterface;
import com.fundoohr.bridgeit.fundoohr.callback.AttendenceBInterface;
import com.fundoohr.bridgeit.fundoohr.controller.AttendenceController;
import com.fundoohr.bridgeit.fundoohr.model.AttendenceModel;
import com.fundoohr.bridgeit.fundoohr.view.activity.AttendanceDetailsActivity;
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
    Context mContext;
    ArrayList<AttendenceModel> attendenceModels = new ArrayList<>();

    public AttendenceViewModel() {
    }

    public AttendenceViewModel(Context mContext) {
        this.mContext = mContext;
    }


    public void attendViewModelData(String URL,long timestamp, String mEngeerId, String tokenValue,
                                    final AttendenceArrayInterface attendenceArrayInterface) {
        RequestParams params = new RequestParams();
        params.put("timeStamp", timestamp);
        params.put("engineerId", mEngeerId);
        AttendenceController attendenceController = new AttendenceController();
        attendenceController.getAttendenceData(URL,tokenValue, params, new AttendenceBInterface() {
            @Override
            public void getAttendBData(byte[] bytes) {


                try {
                    JSONObject jsonObject = new JSONObject(new String(bytes));
                    Log.i("json data attendance", "getAttendBData: "+jsonObject.toString());

                    JSONObject jsonChild = jsonObject.getJSONObject("attendanceData");
                    Log.i("jsonChild", "getAttendBData: ....."+jsonChild);
                    JSONObject jsonChild1 = null;
                    //Getting

                    Iterator<String> keys = jsonChild.keys();
                    Log.i("keys", "getAttendBData: "+keys);
                    while (keys.hasNext()){
                        String keyValue = (String)keys.next();
                        jsonChild1=jsonChild.getJSONObject(keyValue);
                        Log.i("keyValue", "getAttendBData: "+keyValue);
                        Log.i("jsonChild1", "getAttendBData: "+jsonChild1);

                        AttendenceModel attendM = new AttendenceModel();
                        attendM.setMarkedStatus(jsonChild1.getString("markedStatus"));
                        //attendM.setDays(jsonChild1.getString("attendanceData"));
                        attendM.setAttendenceStatus(jsonChild1.getString("attendanceStatus"));
                        attendM.setPunchIn(jsonChild1.getString("punchIn"));
                        attendM.setPunchOut(jsonChild1.getString("punchOut"));
                        attendM.setReason(jsonChild1.getString("reason"));
                        Log.i("Att..", "getAttendBData: "+jsonChild1.getString("attendanceStatus"));
                        attendenceModels.add(attendM);
                    }

                    attendenceArrayInterface.getAttendArrayData(attendenceModels);
                    Log.i("attendViewModel", "getAttendBData: " + attendenceModels.size());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}

