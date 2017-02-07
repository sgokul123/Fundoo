package com.fundoohr.bridgeit.fundoohr.view.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.bridgeit.fundoohr.callback.ProfileDetailArrayInterface;
import com.fundoohr.bridgeit.fundoohr.model.ProfileDetailsModel;
import com.fundoohr.bridgeit.fundoohr.viewmodel.ProfileDetailViewModel;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
/**
 * * Purpose:
 * It Is The View Of MVVM Design Pattern.
 * It Is The UI Class Which Hold The UI Elements.
 * It Listens To Action Performed In UI class.
 * It Implements And The Observer Pattern To Listen Changes In The View model.
 * It Holds The View model To Update Its State Of The UI.
 * It is The Activity Which Need To Be Included In Manifest.xml File.
 **/

public class ProfileDetailsFragment extends Fragment implements ProfileDetailArrayInterface {
    ProgressDialog mDailog;
    Button mSave,mCancel;
    String mProfile_url;
    ImageButton mImageButton;
    SharedPreferences mSharedPreferences;
   // RelativeLayout relativeLayout;
    EditText mProfile_diploma, mProfile_degree, mProfile_disciplane, mProfile_yop, mProfile_aggregate,mProfile_finalyear,
            mProfile_training_institute, mProfile_training_duration, mProfile_training;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile__details,container,false);
        mSave= (Button) view.findViewById(R.id.save);
        mCancel = (Button) view.findViewById(R.id.cancel);
       // relativeLayout= (RelativeLayout) view.findViewById(R.id.profile_relative);
        mImageButton= (ImageButton) view.findViewById(R.id.profile_edit);
        mProfile_diploma= (EditText) view.findViewById(R.id.profile_diploma);
        mProfile_degree= (EditText) view.findViewById(R.id.profile_degree);
        mProfile_disciplane= (EditText) view.findViewById(R.id.profile_disciplane);
        mProfile_yop= (EditText) view.findViewById(R.id.profile_yop);
        mProfile_aggregate= (EditText) view.findViewById(R.id.profile_aggregate);
        mProfile_finalyear= (EditText) view.findViewById(R.id.profile_final_year);
        mProfile_training_institute= (EditText) view.findViewById(R.id.profile_training_institute);
        mProfile_training_duration= (EditText) view.findViewById(R.id.profile_training_duration);
        mProfile_training= (EditText) view.findViewById(R.id.profile_training);

        mProfile_diploma.setFocusable(false);
        mProfile_training.setFocusable(false);
        mProfile_training_institute.setFocusable(false);
        mProfile_training_duration.setFocusable(false);
        mProfile_degree.setFocusable(false);
        mProfile_aggregate.setFocusable(false);
        mProfile_finalyear.setFocusable(false);
        mProfile_yop.setFocusable(false);
        mProfile_disciplane.setFocusable(false);
        mSave.setVisibility(View.INVISIBLE);
        mCancel.setVisibility(view.INVISIBLE);
        /*relativeLayout.setFocusable(false);*/
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProfile_diploma.setFocusableInTouchMode(true);
                mProfile_degree.setFocusableInTouchMode(true);
                mProfile_disciplane.setFocusableInTouchMode(true);
                mProfile_yop.setFocusableInTouchMode(true);
                mProfile_aggregate.setFocusableInTouchMode(true);
                mProfile_finalyear.setFocusableInTouchMode(true);
                mProfile_training_institute.setFocusableInTouchMode(true);
                mProfile_training_duration.setFocusableInTouchMode(true);
                mProfile_training.setFocusableInTouchMode(true);
                //Button to save and Cancel to Update the Data to the Server
                mSave.setVisibility(View.VISIBLE);
                mCancel.setVisibility(View.VISIBLE);

            }
        });
        mSharedPreferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        mProfile_url=getResources().getString(R.string.Profile_url);
        String token=mSharedPreferences.getString("token",null);
        String engineerId=getArguments().getString("id");
        RequestParams requestParams = new RequestParams();
        Log.i("profile", "onCreateView: token"+token);
        requestParams.put("engineerId",engineerId);
        Log.i("profile", "onCreateView: token"+engineerId);
        Toast.makeText(getActivity(), "engineerId"+engineerId, Toast.LENGTH_SHORT).show();

        ProfileDetailViewModel profileDetailViewModel = new ProfileDetailViewModel();
        profileDetailViewModel.profileData(token,mProfile_url,requestParams,this);
        mDailog = new ProgressDialog(getActivity());
        mDailog.setMessage("Loading....");
        mDailog.show();
        return view;
    }

    @Override
    public void profileArraylistData(ArrayList<ProfileDetailsModel> profileDMOdel) {
        mDailog.dismiss();
        ProfileDetailsModel getDetailsModel = profileDMOdel.get(0);
        Log.i("profView", "profileArraylistData: "+getDetailsModel.getDiploma());
        //Setting the data to View
        mProfile_diploma.setText(getDetailsModel.getDiploma());
        mProfile_degree.setText(getDetailsModel.getDegree());
        mProfile_disciplane.setText(getDetailsModel.getDiscipline());
        mProfile_yop.setText(getDetailsModel.getYearOfPassing());
        mProfile_aggregate.setText(getDetailsModel.getAggregateIn());
        mProfile_finalyear.setText(getDetailsModel.getFinalYear());
        mProfile_training_institute.setText(getDetailsModel.getTrainingInstitute());
        mProfile_training_duration.setText(getDetailsModel.getTrainingDuration());
        mProfile_training.setText(getDetailsModel.getTraining());
    }
}
