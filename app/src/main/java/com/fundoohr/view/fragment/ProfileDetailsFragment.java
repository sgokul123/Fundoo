package com.fundoohr.view.fragment;

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
import com.fundoohr.callback.ProfileDetailArrayInterface;
import com.fundoohr.callback.ProfileDetailUpdateInteface;
import com.fundoohr.model.ProfileDetailsModel;
import com.fundoohr.model.UpdatePersonalModel;
import com.fundoohr.utility.ProgressUtil;
import com.fundoohr.viewmodel.ProfileDetailViewModel;
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

public class ProfileDetailsFragment extends Fragment implements ProfileDetailArrayInterface ,ProfileDetailUpdateInteface{
    private  static  String TAG ="ProfileDetailsFragment";
    ProgressUtil prog;
    Button mSave,mCancel;
    String mProfile_url, engineerId;
    ImageButton mImageButton;
    SharedPreferences mSharedPreferences;
   // RelativeLayout relativeLayout;
    EditText mProfile_diploma, mProfile_degree, mProfile_disciplane, mProfile_yop, mProfile_aggregate,mProfile_finalyear,
            mProfile_training_institute, mProfile_training_duration, mProfile_training;
    private String url;

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

        focusableTextField(false);
        mSave.setVisibility(View.INVISIBLE);
        mCancel.setVisibility(view.INVISIBLE);
        /*relativeLayout.setFocusable(false);*/
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusableTextField(true);
                //Button to save and Cancel to Update the Data to the Server
                mSave.setVisibility(View.VISIBLE);
                mCancel.setVisibility(View.VISIBLE);

            }
        });
        mSharedPreferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        url=getResources().getString(R.string.url);
        mProfile_url=getResources().getString(R.string.Profile_url);
        mProfile_url=url+mProfile_url;
        final String token=mSharedPreferences.getString("token",null);
        engineerId=getArguments().getString("id");
        RequestParams requestParams = new RequestParams();
        Log.i(TAG, "onCreateView: token"+token);
        requestParams.put("engineerId",engineerId);
        Log.i(TAG, "onCreateView: token"+engineerId);
        //Toast.makeText(getActivity(), "engineerId"+engineerId, Toast.LENGTH_SHORT).show();

        ProfileDetailViewModel profileDetailViewModel = new ProfileDetailViewModel();
        profileDetailViewModel.profileData(token,mProfile_url,requestParams,this);
        prog = new ProgressUtil(getActivity());
        prog.showProgress("Loading...");

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams praParams = new RequestParams();
                 /* praParams.put("token","'jvbudvhdgd");*/
                Log.i(TAG, "trainingInstitute: " + mProfile_training_institute.getText().toString());

                praParams.put("engineerId", engineerId);
                praParams.put("yearOfPassing", mProfile_yop.getText().toString());
                praParams.put("trainingPeriod", mProfile_training_duration.getText().toString());
                praParams.put("finalYearPercentage", mProfile_finalyear.getText().toString());
                praParams.put("discipline", mProfile_disciplane.getText().toString());
                praParams.put("training", mProfile_training.getText().toString());
                praParams.put("trainingInstitute", mProfile_training_institute.getText().toString());
                praParams.put("diploma", mProfile_diploma.getText().toString());
                praParams.put("degree", mProfile_degree.getText().toString());
                praParams.put("aggregateIn", mProfile_aggregate.getText().toString());
                prog.showProgress("Updating Profile Data ...");

                String hr_url=getResources().getString(R.string.Update_Profile_Data);
                hr_url=url+hr_url;
                ProfileDetailViewModel profileDetailViewModel1=new ProfileDetailViewModel();
                profileDetailViewModel1.updateProfile(token,hr_url,praParams,ProfileDetailsFragment.this);

            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                focusableTextField(false);
                //Button to save and Cancel to Update the Data to the Server
                mSave.setVisibility(View.INVISIBLE);
                mCancel.setVisibility(View.INVISIBLE);
            }
        });
        return view;
    }

    @Override
    public void profileArraylistData(ArrayList<ProfileDetailsModel> profileDMOdel) {
        prog.dismissProgress();
        ProfileDetailsModel getDetailsModel = profileDMOdel.get(0);
        Log.i(TAG, "profileArraylistData: "+getDetailsModel.getDiploma());
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

    @Override
    public void closeDialog() {

    }

    @Override
    public void callforUpdate(UpdatePersonalModel updatePersonalModel) {
        prog.dismissProgress();
        Log.i(TAG, "status : "+updatePersonalModel.getmStatus());
        Log.i(TAG, "Message: "+updatePersonalModel.getmMessage());
        Toast.makeText(getActivity(),updatePersonalModel.getmMessage()+ " ...", Toast.LENGTH_SHORT).show();
        focusableTextField(false);
        mSave.setVisibility(View.INVISIBLE);
        mCancel.setVisibility(View.INVISIBLE);
    }

    @Override
    public void closeDilog() {
        prog.dismissProgress();
    }

    public  void focusableTextField(Boolean state){
        mProfile_diploma.setEnabled(state);
        mProfile_degree.setEnabled(state);
        mProfile_disciplane.setEnabled(state);
        mProfile_yop.setEnabled(state);
        mProfile_aggregate.setEnabled(state);
        mProfile_finalyear.setEnabled(state);
        mProfile_training_institute.setEnabled(state);
        mProfile_training_duration.setEnabled(state);
        mProfile_training.setEnabled(state);

    }
}
