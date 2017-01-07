package com.example.bridgeit.fundoo.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.bridgeit.fundoo.R;

public class HRDetails extends Fragment {

ImageButton mImageButton;
    EditText mHiring,mFellowship,mBlstart,mCmpstrtdate,mCmpEnddate,mInitialize,
            mSigned, mCompanyInitiated,mCompSigned,mContractSignDate,mInitiateTransfer,
    mTransferStatas,mTransferDate,mCertificate,mCollected,mHr_certificate_submit,
            mHr_other_documents,mHr_original_returned,mHr_return_details,mHr_refferal;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_hrdetails,container,false);

        mImageButton= (ImageButton) view.findViewById(R.id.hr_edit);
        mHiring= (EditText) view.findViewById(R.id.hr_hiring);
        mFellowship= (EditText) view.findViewById(R.id.hr_fellow);
        mBlstart= (EditText) view.findViewById(R.id.hr_blstart);
        mCmpstrtdate= (EditText) view.findViewById(R.id.hr_companyStartDate);
        mCmpEnddate= (EditText) view.findViewById(R.id.hr_companyEndDate);

        mInitialize= (EditText) view.findViewById(R.id.hr_initiated);
        mSigned= (EditText) view.findViewById(R.id.hr_signed);
        mCompanyInitiated= (EditText) view.findViewById(R.id.hr_comp_initiated);
        mCompSigned= (EditText) view.findViewById(R.id.hr_comp_signed);
        mContractSignDate= (EditText) view.findViewById(R.id.hr_contract_signed_date);
        mInitiateTransfer= (EditText) view.findViewById(R.id.hr_initiate_transfer);
        mTransferStatas= (EditText) view.findViewById(R.id.hr_transfer_status);
        mTransferDate= (EditText) view.findViewById(R.id.hr_transfer_date);
        mCertificate= (EditText) view.findViewById(R.id.hr_certificate);
        mCollected= (EditText) view.findViewById(R.id.hr_collected);
        mHr_certificate_submit= (EditText) view.findViewById(R.id.hr_certificate_submit);
        mHr_other_documents= (EditText) view.findViewById(R.id.hr_other_documents);
        mHr_original_returned= (EditText) view.findViewById(R.id.hr_original_returned);
        mHr_return_details= (EditText) view.findViewById(R.id.hr_return_details);
        mHr_refferal= (EditText) view.findViewById(R.id.hr_refferal);

        mHiring.setFocusable(false);
        mFellowship.setFocusable(false);
        mBlstart.setFocusable(false);
        mCmpstrtdate.setFocusable(false);
        mCmpEnddate.setFocusable(false);
        mInitialize.setFocusable(false);
        mSigned.setFocusable(false);
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
               mInitialize.setFocusableInTouchMode(true);
               mSigned.setFocusableInTouchMode(true);
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
               mFellowship.setFocusableInTouchMode(true);
           }
       });


        return view;

    }
}
