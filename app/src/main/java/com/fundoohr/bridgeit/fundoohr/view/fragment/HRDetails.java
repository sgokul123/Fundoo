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
import android.widget.EditText;
import android.widget.ImageButton;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.bridgeit.fundoohr.callback.HRDetailArrayInterface;
import com.fundoohr.bridgeit.fundoohr.model.HRDetailsModel;
import com.fundoohr.bridgeit.fundoohr.viewmodel.HRDetailViewModel;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

public class HRDetails extends Fragment implements HRDetailArrayInterface {
    String mHR_url;
    SharedPreferences mSharedPreferences;
    ImageButton mImageButton;
    EditText mHiring, mFellowship, mBlstart, mCmpstrtdate, mCmpEnddate, mEnggContractInitiate, mEnggContractSigned,
            mCompanyInitiated, mCompSigned, mContractSignDate, mInitiateTransfer,
            mTransferStatas, mTransferDate, mCertificate, mCollected, mHr_certificate_submit,
            mHr_other_documents, mHr_original_returned, mHr_return_details, mHr_refferal;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hrdetails, container, false);

        mImageButton = (ImageButton) view.findViewById(R.id.hr_edit);
        mHiring = (EditText) view.findViewById(R.id.hr_hiring);
        mFellowship = (EditText) view.findViewById(R.id.hr_fellow);
        mBlstart = (EditText) view.findViewById(R.id.hr_blstart);
        mCmpstrtdate = (EditText) view.findViewById(R.id.hr_companyStartDate);
        mCmpEnddate = (EditText) view.findViewById(R.id.hr_companyEndDate);

        mEnggContractInitiate = (EditText) view.findViewById(R.id.hr_enggContractinitiated);
        mEnggContractSigned = (EditText) view.findViewById(R.id.hr_enggContractsigned);
        mCompanyInitiated = (EditText) view.findViewById(R.id.hr_comp_initiated);
        mCompSigned = (EditText) view.findViewById(R.id.hr_comp_signed);
        mContractSignDate = (EditText) view.findViewById(R.id.hr_contract_signed_date);
        mInitiateTransfer = (EditText) view.findViewById(R.id.hr_initiate_transfer);
        mTransferStatas = (EditText) view.findViewById(R.id.hr_transfer_status);
        mTransferDate = (EditText) view.findViewById(R.id.hr_transfer_date);
        mCertificate = (EditText) view.findViewById(R.id.hr_certificate);
        mCollected = (EditText) view.findViewById(R.id.hr_collected);
        mHr_certificate_submit = (EditText) view.findViewById(R.id.hr_certificate_submit);
        mHr_other_documents = (EditText) view.findViewById(R.id.hr_other_documents);
        mHr_original_returned = (EditText) view.findViewById(R.id.hr_original_returned);
        mHr_return_details = (EditText) view.findViewById(R.id.hr_return_details);
        mHr_refferal = (EditText) view.findViewById(R.id.hr_refferal);

        mHiring.setFocusable(false);
        mFellowship.setFocusable(false);
        mBlstart.setFocusable(false);
        mCmpstrtdate.setFocusable(false);
        mCmpEnddate.setFocusable(false);
        mEnggContractInitiate.setFocusable(false);
        mEnggContractSigned.setFocusable(false);
        mCompanyInitiated.setFocusable(false);
        mCompSigned.setFocusable(false);
        mContractSignDate.setFocusable(false);
        mInitiateTransfer.setFocusable(false);
        mTransferStatas.setFocusable(false);
        mTransferDate.setFocusable(false);
        mCertificate.setFocusable(false);
        mCollected.setFocusable(false);
        mHr_certificate_submit.setFocusable(false);
        mHr_other_documents.setFocusable(false);
        mHr_original_returned.setFocusable(false);
        mHr_return_details.setFocusable(false);
        mHr_refferal.setFocusable(false);
        mFellowship.setFocusable(false);

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHiring.setFocusableInTouchMode(true);
                mFellowship.setFocusableInTouchMode(true);
                mBlstart.setFocusableInTouchMode(true);
                mCmpstrtdate.setFocusableInTouchMode(true);
                mCmpEnddate.setFocusableInTouchMode(true);
                mEnggContractInitiate.setFocusableInTouchMode(true);
                mEnggContractSigned.setFocusableInTouchMode(true);
                mCompanyInitiated.setFocusableInTouchMode(true);
                mCompSigned.setFocusableInTouchMode(true);
                mContractSignDate.setFocusableInTouchMode(true);
                mInitiateTransfer.setFocusableInTouchMode(true);
                mTransferStatas.setFocusableInTouchMode(true);
                mTransferDate.setFocusableInTouchMode(true);
                mCertificate.setFocusableInTouchMode(true);
                mCollected.setFocusableInTouchMode(true);
                mHr_certificate_submit.setFocusableInTouchMode(true);
                mHr_other_documents.setFocusableInTouchMode(true);
                mHr_original_returned.setFocusableInTouchMode(true);
                mHr_return_details.setFocusableInTouchMode(true);
                mHr_refferal.setFocusableInTouchMode(true);

            }
        });
        mSharedPreferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String token = mSharedPreferences.getString("token", null);
        String engineerId = getArguments().getString("id");
        RequestParams requestParams = new RequestParams();
        requestParams.put("engineerId", engineerId);
        Log.i("HR", "onCreateView: token" + token);
        Log.i("HR", "onCreateView: token" + engineerId);
        mHR_url= getResources().getString(R.string.HR_url);


        HRDetailViewModel hrViewModel = new HRDetailViewModel();
        hrViewModel.hrDataList(token,mHR_url,requestParams, this);
        return view;

    }

    @Override
    public void hrDetailData(ArrayList<HRDetailsModel> hrDetailModels) {
        HRDetailsModel modelHR = hrDetailModels.get(0);

        mBlstart.setText(modelHR.getBlStartDate());
        mCompanyInitiated.setText(modelHR.getCompContractInitiated());
        mCompSigned.setText(modelHR.getCompContractSigned());
        mCmpstrtdate.setText(modelHR.getCompanyJoinDate());
        mCmpEnddate.setText(modelHR.getCompanyLeaveDate());
        mContractSignDate.setText(modelHR.getConntractSignDate());
        mEnggContractInitiate.setText(modelHR.getEnggContractInitiated());
        mEnggContractSigned.setText(modelHR.getEnggContractSigned());
        mFellowship.setText(modelHR.getFellowshipPeriod());
        mHiring.setText(modelHR.getHiringCity());
        mInitiateTransfer.setText(modelHR.getInitiateTransfer());

    }
}
