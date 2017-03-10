package com.fundoohr.viewmodel;

import android.content.Context;
import android.util.Log;

import com.fundoohr.callback.PersonalDetailBInterface;
import com.fundoohr.callback.ProfileDetailUpdateInteface;
import com.fundoohr.callback.TrackingDetailArrayInterface;
import com.fundoohr.callback.TrackingDetailBInterface;
import com.fundoohr.controller.ProfileDetailController;
import com.fundoohr.controller.TrackinController;
import com.fundoohr.model.ProfileDetailsModel;
import com.fundoohr.model.TrackingDetailsModel;
import com.fundoohr.model.UpdatePersonalModel;
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
    private static String TAG ="TrackingDetailViewModel";
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

            @Override
            public void closeDialog() {
                trackingDetailArrayInterface.closeDialog();
            }
        });


    }

    public void updateProfile(String token, String mProfile_url, RequestParams requestParams, final ProfileDetailUpdateInteface profileDetailUpdateInteface){
        final ArrayList<ProfileDetailsModel> profileDetailsModel=new ArrayList<>();
        TrackinController trackinControllernew=new TrackinController();
        Log.i(TAG, "onClick Update ...");
        trackinControllernew.updateController(token,mProfile_url,requestParams, new PersonalDetailBInterface() {
            @Override
            public void personalViewModelData(byte[] bytes) {
                JSONObject object= null;
                try {

                    object = new JSONObject(new String(bytes));
                    UpdatePersonalModel UpPersonalModel = new UpdatePersonalModel();
                    UpPersonalModel.setmStatus(object.getString("status"));
                    UpPersonalModel.setmMessage(object.getString("message"));
                    profileDetailUpdateInteface.callforUpdate(UpPersonalModel);
                    Log.i(TAG, "onSuccess: "+object.getString("message"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void closeDialog() {
                profileDetailUpdateInteface.closeDilog();
            }
        });

    }
}
