package com.fundoohr.bridgeit.fundoohr.viewmodel;

import android.content.Context;
import android.util.Log;

import com.fundoohr.bridgeit.fundoohr.callback.TrackingDetailArrayInterface;
import com.fundoohr.bridgeit.fundoohr.callback.TrackingDetailBInterface;
import com.fundoohr.bridgeit.fundoohr.controller.TrackinController;
import com.fundoohr.bridgeit.fundoohr.model.TrackingDetailsModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bridgeit on 24/1/17.
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
public class TrackingDetailViewModel {
    Context mContext;

    public TrackingDetailViewModel(Context mContext) {this.mContext = mContext;}

    public TrackingDetailViewModel() {

    }

    public  void trackingData(String token, String mTrack_url, RequestParams params, final TrackingDetailArrayInterface trackingDetailArrayInterface){
          final ArrayList<TrackingDetailsModel> trackDM = new ArrayList<>();
        TrackinController trackinController = new TrackinController();
        trackinController.trackDataController(token,mTrack_url,params, new TrackingDetailBInterface() {
            @Override
            public void trackData(byte[] bytes) {
                try {
                    JSONObject jsonObject= new JSONObject(new String(bytes));
                    JSONObject jsonObjectChild=jsonObject.getJSONObject("trackingData");
                    for (int i = 0; i <jsonObjectChild.length() ; i++) {
                        TrackingDetailsModel trackModel = new TrackingDetailsModel();
                        trackModel.setBridgelabzStartDate(jsonObjectChild.getString("bridgelabzStartDate"));
                        trackModel.setBridgelabzEndDate(jsonObjectChild.getString("bridgelabzEndDate"));
                        trackModel.setCurrentWeek(jsonObjectChild.getString("currentWeek"));
                        trackModel.setNumberOfWeeksLeft(jsonObjectChild.getString("numberOfWeeksLeft"));
                        trackModel.setTechStack(jsonObjectChild.getString("techStack"));
                        trackModel.setWeek1(jsonObjectChild.getString("week1"));
                        Log.i("trackviewmodel", "trackData: ......."+jsonObjectChild.getString("bridgelabzStartDate"));
                        trackDM.add(trackModel);
                    }
                    trackingDetailArrayInterface.trackingData(trackDM);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
