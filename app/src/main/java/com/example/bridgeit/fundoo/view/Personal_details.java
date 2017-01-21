package com.example.bridgeit.fundoo.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.bridgeit.fundoo.R;
import com.example.bridgeit.fundoo.callback.PersonalDetailInterface;
import com.example.bridgeit.fundoo.model.PersonalDetailsModel;
import com.example.bridgeit.fundoo.viewmodel.PersonalDetailViewModel;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

public class Personal_details extends Fragment implements PersonalDetailInterface{
    ImageButton mImageButton;
    SharedPreferences mSharedPreferences;
    EditText mId,mEmail,mMobile,mDOB,mFathername,mFathetmob,mOccupation,mAnnualsalary,mMumAdd,mPermanentAdd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.activity_personal_details,container,false);
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
            }
        });



        mSharedPreferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String token=mSharedPreferences.getString("token",null);
        //Geting the Engineer ID from EngineerFragment
        Bundle args = getArguments();
      //  String engineerID = args.getString("Id");
        String engineerID = "427188EI";
        Log.i("Pd", "onCreateView: engid"+engineerID);
        RequestParams requestParams = new RequestParams();
        requestParams.put("",token);
        Log.i("Pd", "onCreateView: token"+token);
        requestParams.put("",engineerID);

        PersonalDetailViewModel personalDetailVModel= new PersonalDetailViewModel();
        personalDetailVModel.personalData(requestParams,this);
        return view;

    }

    @Override
    public void personalArrayListDetail(ArrayList<PersonalDetailsModel> personalDetailsModels) {
        Log.i("personalView", "personalArrayListDetail: " +personalDetailsModels);
    }


}
