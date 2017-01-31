package com.fundoohr.bridgeit.fundoohr.viewmodel;

import android.content.Context;
import android.util.Log;

import com.fundoohr.bridgeit.fundoohr.callback.ProfileDetailArrayInterface;
import com.fundoohr.bridgeit.fundoohr.callback.ProfileDetailBInterface;
import com.fundoohr.bridgeit.fundoohr.controller.ProfileDetailController;
import com.fundoohr.bridgeit.fundoohr.model.ProfileDetailsModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bridgeit on 23/1/17.
 */
public class ProfileDetailViewModel {
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
        });

    }
}
