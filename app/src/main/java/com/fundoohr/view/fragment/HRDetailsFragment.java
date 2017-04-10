package com.fundoohr.view.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.callback.HRDetailArrayInterface;
import com.fundoohr.model.HRDetailsModel;
import com.fundoohr.utility.Constants;
import com.fundoohr.utility.ProgressUtil;
import com.fundoohr.viewmodel.HRDetailViewModel;
import com.loopj.android.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class HRDetailsFragment extends Fragment implements View.OnClickListener {
    Button mSave,mCancel;
    private EditText mEditTextcheng;
    SharedPreferences mSharedPreferences;
    ImageButton mImageButton;
    EditText mHiring, mFellowship, mBlstart, mCmpstrtdate, mCmpEnddate, mEnggContractInitiate, mEnggContractSigned,
            mCompanyInitiated, mCompSigned, mContractSignDate, mInitiateTransfer,
            mTransferStatas, mTransferDate;
    private String TAG ="HRDetailsFragment";
    private ProgressUtil prog;
    private  DatePickerDialog.OnDateSetListener date;
        private String mHR_url;/*, mCertificate, mCollected, mHrcertificatesubmit,
            mHrotherdocuments, mHroriginalreturned, mHrreturndetails, mHrRefferal;
*/  final Calendar myCalendar = Calendar.getInstance();
    private String url;
    private  boolean isEditable=false;
    private  String engineerId;
    private String token;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hrdetails, container, false);
        mSave= (Button) view.findViewById(R.id.save_hr_details);
        mCancel = (Button) view.findViewById(R.id.cancel_hr_details);
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


        mCompSigned.setOnClickListener(this);
        mEnggContractSigned.setOnClickListener(this);
        mBlstart.setOnClickListener(this);
        mCmpEnddate.setOnClickListener(this);
        mInitiateTransfer.setOnClickListener(this);
        mCmpstrtdate.setOnClickListener(this);
        mContractSignDate.setOnClickListener(this);
        mEnggContractInitiate.setOnClickListener(this);
        mCompanyInitiated.setOnClickListener(this);

        setVisibility(false);

       /* mCertificate.setFocusable(false);
        mCollected.setFocusable(false);
        mHrcertificatesubmit.setFocusable(false);
        mHrotherdocuments.setFocusable(false);
        mHroriginalreturned.setFocusable(false);
        mHrreturndetails.setFocusable(false);
        mHrRefferal.setFocusable(false);*/
        mFellowship.setFocusable(false);
        mSave.setVisibility(View.INVISIBLE);
        mCancel.setVisibility(view.INVISIBLE);

      /*  mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVisibility(true);
                isEditable=true;
              *//*  mCertificate.setFocusableInTouchMode(true);
                mCollected.setFocusableInTouchMode(true);
                mHrcertificatesubmit.setFocusableInTouchMode(true);
                mHrotherdocuments.setFocusableInTouchMode(true);
                mHroriginalreturned.setFocusableInTouchMode(true);
                mHrreturndetails.setFocusableInTouchMode(true);
                mHrRefferal.setFocusableInTouchMode(true);
               *//* //Button to save and Cancel to Update the Data to the Server
                mSave.setVisibility(View.VISIBLE);
                mCancel.setVisibility(View.VISIBLE);

            }
        });*/
               mImageButton.setOnClickListener(this);

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        mSharedPreferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        token = mSharedPreferences.getString("token", null);
        engineerId= getArguments().getString("id");
        RequestParams requestParams = new RequestParams();
        requestParams.put("engineerId", engineerId);
        Log.i(TAG, "onCreateView: token" + token);
        Log.i(TAG, "onCreateView: token" + engineerId);
        url=getResources().getString(R.string.url);
        mHR_url= getResources().getString(R.string.HR_url);
        mHR_url=url+mHR_url;
        prog = new ProgressUtil(getActivity());
        prog.showProgress("Loading..");
        final HRDetailViewModel hrViewModel = new HRDetailViewModel();
        hrViewModel.hrDataList(token, mHR_url, requestParams, new HRDetailArrayInterface() {
            @Override
            public void hrDetailData(ArrayList<HRDetailsModel> hrDetailModels) {
                setdate(hrDetailModels);
                Log.i(TAG, "data set...");
            }

            @Override
            public void isDataUpdated(String status) {

            }

            @Override
            public void closeDilog() {
                prog.dismissProgress();
            }
        });

        Log.i(TAG, "onCreateView: token   " + requestParams);


         mSave.setOnClickListener(this);/*new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        mCancel.setOnClickListener(this);/*new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVisibility(false);
                mCancel.setVisibility(View.INVISIBLE);
                mSave.setVisibility(View.INVISIBLE);
            }
        });*/
        return view;
    }


    public  void setdate(ArrayList<HRDetailsModel> hrDetailModels){
        prog.dismissProgress();
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
        mTransferStatas.setText(modelHR.getmEmployeeStatus());
    }

    public  void setVisibility(Boolean state) {
        mHiring.setEnabled(state);
        mFellowship.setEnabled(state);
        mBlstart.setEnabled(state);
        mCmpstrtdate.setEnabled(state);
        mCmpEnddate.setEnabled(state);
        mEnggContractInitiate.setEnabled(state);
        mEnggContractSigned.setEnabled(state);
        mCompanyInitiated.setEnabled(state);
        mCompSigned.setEnabled(state);
        mContractSignDate.setEnabled(state);
        mInitiateTransfer.setEnabled(state);
        mTransferStatas.setEnabled(state);
        mTransferDate.setEnabled(state);
    }
    private void updateLabel() {

        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        mEditTextcheng.setText(sdf.format(myCalendar.getTime()));
    }




    @Override
    public void onClick(View v) {
      if(isEditable) {
          //date picker

          new DatePickerDialog(getActivity(), date, myCalendar
                  .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                  myCalendar.get(Calendar.DAY_OF_MONTH)).show();

          //for chenge of edittext
          switch (v.getId()) {
              case R.id.hr_blstart:
                  mEditTextcheng = mBlstart;
                  break;
              case R.id.hr_comp_initiated:
                  mEditTextcheng = mCompanyInitiated;
                  break;
              case R.id.hr_companyStartDate:
                  mEditTextcheng = mCmpstrtdate;
                  break;
              case R.id.hr_enggContractinitiated:
                  mEditTextcheng = mEnggContractInitiate;
                  break;
              case R.id.hr_enggContractsigned:
                  mEditTextcheng = mEnggContractSigned;
                  break;
              case R.id.hr_initiate_transfer:
                  mEditTextcheng = mInitiateTransfer;
                  break;
              case R.id.hr_contract_signed_date:
                  mEditTextcheng = mContractSignDate;
                  break;
              case R.id.hr_comp_signed:
                  mEditTextcheng = mCompSigned;
                  break;
              case R.id.hr_companyEndDate:
                  mEditTextcheng = mCmpEnddate;
                  break;
              case R.id.save_hr_details:
                  prog.showProgress("Updating HR Data ....");
                  RequestParams paramData =new RequestParams();
                  paramData.put(Constants.RequestParam.HIRINGCITY, mHiring.getText().toString());
                  paramData.put(Constants.RequestParam.FELLOWSHIPPERIOD,mFellowship.getText().toString());
                  paramData.put(Constants.RequestParam.BLSTARTDATE,mBlstart.getText().toString());
                  paramData.put(Constants.RequestParam.COMPANYJOINDATE,mCmpstrtdate.getText().toString());
                  paramData.put(Constants.RequestParam.COMPONYLEAVEDATE,mCmpEnddate.getText().toString());
                  paramData.put(Constants.RequestParam.ENGGCONTRACTINITIATED,mEnggContractInitiate.getText().toString());
                  paramData.put(Constants.RequestParam.ENGGCONTRACTSIGNED,mEnggContractSigned.getText().toString());
                  paramData.put(Constants.RequestParam.COMPCONTRACTINITIATED,mCompanyInitiated.getText().toString());
                  paramData.put(Constants.RequestParam.CONTRACTSIGNDATE,mContractSignDate.getText().toString());
                  paramData.put(Constants.RequestParam.INITIATETRANSFER,mInitiateTransfer.getText().toString());
                  paramData.put(Constants.RequestParam.COMPCONTRACTSIGNED,mCompSigned.getText().toString());
                  paramData.put(Constants.RequestParam.NCOMPANY,"-");
                  paramData.put(Constants.RequestParam.EMPLOYEESTATUS,mTransferStatas.getText().toString());
                  paramData.put(Constants.RequestParam.EMPLOYEEID,engineerId);
                  Log.i(TAG, "onCreateView: update" + token);

                  HRDetailViewModel hrModel=new HRDetailViewModel();
                  Log.i(TAG, "onCreateView: 1");

                  String hr_url=getResources().getString(R.string.HR_Update_url);
                  hr_url=url+hr_url;
                  hrModel.hrDataUpdate(token, hr_url, paramData,new HRDetailArrayInterface() {
                      @Override
                      public void hrDetailData(ArrayList<HRDetailsModel> hrDetailModels) {
                      }
                      @Override
                      public void isDataUpdated(String status) {
                          prog.dismissProgress();
                          Log.i(TAG, "data updated..."+status);
                          setVisibility(false);
                          mCancel.setVisibility(View.INVISIBLE);
                          mSave.setVisibility(View.INVISIBLE);
                      }

                      @Override
                      public void closeDilog() {
                          prog.dismissProgress();
                      }
                  });

                  break;
              case R.id.cancel_hr_details:
                  setVisibility(false);
                  mCancel.setVisibility(View.INVISIBLE);
                  mSave.setVisibility(View.INVISIBLE);
                  break;

              default:

                  break;


          }
      }
      else if(v.getId()==R.id.hr_edit){

          setVisibility(true);
          isEditable=true;

          mSave.setVisibility(View.VISIBLE);
          mCancel.setVisibility(View.VISIBLE);

      }
    }
}

