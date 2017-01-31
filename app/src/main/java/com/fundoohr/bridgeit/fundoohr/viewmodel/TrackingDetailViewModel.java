package com.fundoohr.bridgeit.fundoohr.viewmodel;

import android.content.Context;
import android.util.Log;

import com.fundoohr.bridgeit.fundoohr.callback.TrackingDetailArrayInterface;
import com.fundoohr.bridgeit.fundoohr.callback.TrackingDetailBInterface;
import com.fundoohr.bridgeit.fundoohr.controller.TrackinController;
import com.fundoohr.bridgeit.fundoohr.model.TrackingDetailsModel;
import com.fundoohr.bridgeit.fundoohr.view.fragment.TrackingDetails;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bridgeit on 24/1/17.
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
