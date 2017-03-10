package com.fundoohr.viewmodel;

import android.content.Context;
import android.util.Log;

import com.fundoohr.callback.PersonalDetailBInterface;
import com.fundoohr.callback.ProfileDetailArrayInterface;
import com.fundoohr.callback.ProfileDetailBInterface;
import com.fundoohr.callback.ProfileDetailUpdateInteface;
import com.fundoohr.controller.ProfileDetailController;
import com.fundoohr.model.ProfileDetailsModel;
import com.fundoohr.model.UpdatePersonalModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bridgeit on 23/1/17.
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
public class ProfileDetailViewModel {
    private static String TAG ="ProfileDetailViewModel";
    Context mContext;

    public ProfileDetailViewModel() {
    }

    public ProfileDetailViewModel(Context mContext) { this.mContext = mContext;}
    public void profileData(String token, String mProfile_url, RequestParams requestParams, final ProfileDetailArrayInterface profileDetailArrayInterface){
        final ArrayList<ProfileDetailsModel> profileDetailsModel=new ArrayList<>();
        ProfileDetailController profileDetailController = new ProfileDetailController();
        profileDetailController.getProfileDetailController(token,mProfile_url,requestParams, new ProfileDetailBInterface() {
            @Override
            public void profileDataInterface(byte[] bytes) {
                try{
                    JSONObject jsonObject = new JSONObject(new String(bytes));
                    JSONObject jsonChildObject = jsonObject.getJSONObject("profileData");
                    for (int i = 0; i <jsonChildObject.length(); i++) {
                        ProfileDetailsModel profileModel = new ProfileDetailsModel();

                        profileModel.setAggregateIn(jsonChildObject.getString("aggregateIn"));
                        profileModel.setDegree(jsonChildObject.getString("degree"));
                        profileModel.setDiploma(jsonChildObject.getString("diploma"));
                        profileModel.setDiscipline(jsonChildObject.getString("discipline"));
                        profileModel.setFinalYear(jsonChildObject.getString("finalYearPercentage"));
                        profileModel.setTraining(jsonChildObject.getString("training"));
                        profileModel.setTrainingInstitute(jsonChildObject.getString("trainingInstitute"));
                        profileModel.setTrainingDuration(jsonChildObject.getString("trainingPeriod"));
                        profileModel.setYearOfPassing(jsonChildObject.getString("yearOfPassing"));
                        profileDetailsModel.add(profileModel);
                    }
                    profileDetailArrayInterface.profileArraylistData(profileDetailsModel);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void closeDialog() {
                profileDetailArrayInterface.closeDialog();

            }
        });

    }

    public void updateProfile(String token, String mProfile_url, RequestParams requestParams, final ProfileDetailUpdateInteface profileDetailUpdateInteface){
        final ArrayList<ProfileDetailsModel> profileDetailsModel=new ArrayList<>();
        ProfileDetailController profileDetailController = new ProfileDetailController();
        profileDetailController.updateController(token,mProfile_url,requestParams, new PersonalDetailBInterface() {
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
