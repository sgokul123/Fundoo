package com.fundoohr.bridgeit.fundoohr.view.fragment;

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
import com.fundoohr.bridgeit.fundoohr.callback.PersonalDetailArrayInterface;
import com.fundoohr.bridgeit.fundoohr.model.PersonalDetailsModel;
import com.fundoohr.bridgeit.fundoohr.viewmodel.PersonalDetailViewModel;
import com.fundoohr.bridgeit.fundoohr.viewmodel.UpdatePersonalViewMOdel;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

public class Personaldetails extends Fragment implements PersonalDetailArrayInterface {
    Button mSave, mCancel;
    String mPersonal_url;
    ImageButton mImageButton;
    SharedPreferences mSharedPreferences;
    EditText mId,mEmail,mMobile,mDOB,mFathername,mFathetmob,mOccupation,mAnnualsalary,mMumAdd,mPermanentAdd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_personal_details,container,false);
        mSave= (Button) view.findViewById(R.id.save);
        mCancel = (Button) view.findViewById(R.id.cancel);
        mImageButton= (ImageButton) view.findViewById(R.id.personal_edit);
        mId= (EditText) view.findViewById(R.id.personal_id);
        mEmail= (EditText) view.findViewById(R.id.personal_email);
        mMobile= (EditText) view.findViewById(R.id.personal_mobile);
        mDOB= (EditText) view.findViewById(R.id.personal_dob);
        mFathername= (EditText) view.findViewById(R.id.personal_father);
        mFathetmob= (EditText) view.findViewById(R.id.personal_father_mobile);
        mOccupation= (EditText) view.findViewById(R.id.personal_occup);
        mAnnualsalary= (EditText) view.findViewById(R.id.personal_anual);
        mMumAdd= (EditText) view.findViewById(R.id.personal_mumbaiadd);
        mPermanentAdd= (EditText) view.findViewById(R.id.personal_peradd);

        mId.setFocusable(false);
        mEmail.setFocusable(false);
        mMobile.setFocusable(false);
        mDOB.setFocusable(false);
        mFathername.setFocusable(false);
        mFathetmob.setFocusable(false);
        mOccupation.setFocusable(false);
        mAnnualsalary.setFocusable(false);
        mMumAdd.setFocusable(false);
        mPermanentAdd.setFocusable(false);
        mSave.setVisibility(View.INVISIBLE);
        mCancel.setVisibility(view.INVISIBLE);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mId.setFocusableInTouchMode(true);
                mEmail.setFocusableInTouchMode(true);
                mMobile.setFocusableInTouchMode(true);
                mDOB.setFocusableInTouchMode(true);
                mFathername.setFocusableInTouchMode(true);
                mFathetmob.setFocusableInTouchMode(true);
                mOccupation.setFocusableInTouchMode(true);
                mAnnualsalary.setFocusableInTouchMode(true);
                mMumAdd.setFocusableInTouchMode(true);
                mPermanentAdd.setFocusableInTouchMode(true);
                //Button to save and Cancel to Update the Data to the Server
                mSave.setVisibility(View.VISIBLE);
                mCancel.setVisibility(View.VISIBLE);
            }
        });



        mSharedPreferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String token=mSharedPreferences.getString("token",null);
        String strtext = getArguments().getString("id");
        mPersonal_url=getResources().getString(R.string.Personal_url);
       /* String tokenValue = getArguments().getString("token");*/
        RequestParams requestParams = new RequestParams();
        Log.i("Pd", "onCreateView: token"+token);
        requestParams.put("engineerId",strtext);
        Log.i("Pd", "onCreateView: engid"+strtext);
        Toast.makeText(getActivity(), "gettting id"+strtext, Toast.LENGTH_SHORT).show();

        PersonalDetailViewModel personalDetailVModel= new PersonalDetailViewModel();
        personalDetailVModel.personalData(token,mPersonal_url,requestParams,this);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestParams praParams = new RequestParams();
                praParams.put("token","'jvbudvhdgd");
                praParams.put("engineerId",mId.getText().toString());
                praParams.put("emailId",mEmail.getText().toString());
                praParams.put("mobile",mMobile.getText().toString());
                praParams.put("dateOfBirth",mDOB.getText().toString());
                praParams.put("fatherName",mFathername.getText().toString());
                praParams.put("fatherMobile",mFathetmob.getText().toString());
                praParams.put("occupation",mOccupation.getText().toString());
                praParams.put("annualSalary",mAnnualsalary.getText().toString());
                praParams.put("mumbaiAddress",mMumAdd.getText().toString());
                praParams.put("permanentAddress",mPermanentAdd.getText().toString());
                //Updating the edited data for that we need to create the updateviewmodelController
                //object
                UpdatePersonalViewMOdel updatePersonalViewMOdel = new UpdatePersonalViewMOdel();
                updatePersonalViewMOdel.updatePersonData(praParams);
                Log.i("UpdatePersonalViewMOdel", "onClick: "+praParams);


            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCancel.setVisibility(View.INVISIBLE);
                mSave.setVisibility(View.INVISIBLE);
            }
        });
        return view;

    }

    @Override
    public void personalArrayListDetail(ArrayList<PersonalDetailsModel> personalDetailsModels) {

        PersonalDetailsModel personalModel = personalDetailsModels.get(0);
        Log.i("personalView", "personalArrayListDetail: "+personalModel.getAnnualSalary());
        //Setting the data to View

        mId.setText(personalModel.getEngineerId());
        mEmail.setText(personalModel.getEmailId());
        mMobile.setText(personalModel.getMobile());
        mDOB.setText(personalModel.getDateOfBirth());
        mFathername.setText(personalModel.getFatherName());
        mFathetmob.setText(personalModel.getFatherMobile());
        mOccupation.setText(personalModel.getOccupation());
        mAnnualsalary.setText(personalModel.getAnnualSalary());
        mMumAdd.setText(personalModel.getMumbaiAddress());
        mPermanentAdd.setText(personalModel.getPremanentAddress());


    }


}
