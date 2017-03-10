package com.fundoohr.view.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.callback.PersonalDetailArrayInterface;
import com.fundoohr.model.PersonalDetailsModel;
import com.fundoohr.model.UpdatePersonalModel;
import com.fundoohr.utility.ProgressUtil;
import com.fundoohr.viewmodel.PersonalDetailViewModel;
import com.fundoohr.viewmodel.UpdatePersonalViewMOdel;
import com.loopj.android.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * * Purpose:
 * It Is The View Of MVVM Design Pattern.
 * It Is The UI Class Which Hold The UI Elements.
 * It Listens To Action Performed In UI class.
 * It Implements And The Observer Pattern To Listen Changes In The View model.
 * It Holds The View model To Update Its State Of The UI.
 * It is The Activity Which Need To Be Included In Manifest.xml File.
 **/

@SuppressLint("ValidFragment")
public class PersonaldetailsFragment extends Fragment implements PersonalDetailArrayInterface {
    private  static  String TAG ="PersonaldetailsFragment";
    ProgressUtil prog;
    Button mSave, mCancel;
    String enggID,menggName;
    String mPersonal_url;
    ImageButton mImageButton;
    boolean shouldEdit = false;
    ProgressDialog mProgressDialog;
    SharedPreferences mSharedPreferences;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    EditText mId, mEmail, mMobile, mDOB, mFathername, mFathetmob, mOccupation, mAnnualsalary, mMumAdd, mPermanentAdd;
    final Calendar myCalendar = Calendar.getInstance();
    private String url;

    /*private Pattern mPattern;
    private Matcher mMatcher;*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personal_details, container, false);
        mSave = (Button) view.findViewById(R.id.save);
        mCancel = (Button) view.findViewById(R.id.cancel);
        mImageButton = (ImageButton) view.findViewById(R.id.personal_edit);
        mId = (EditText) view.findViewById(R.id.personal_id);
        mEmail = (EditText) view.findViewById(R.id.personal_email);
        mMobile = (EditText) view.findViewById(R.id.personal_mobile);
        mDOB = (EditText) view.findViewById(R.id.personal_dob);
        mFathername = (EditText) view.findViewById(R.id.personal_father);
        mFathetmob = (EditText) view.findViewById(R.id.personal_father_mobile);
        mOccupation = (EditText) view.findViewById(R.id.personal_occup);
        mAnnualsalary = (EditText) view.findViewById(R.id.personal_anual);
        mMumAdd = (EditText) view.findViewById(R.id.personal_mumbaiadd);
        mPermanentAdd = (EditText) view.findViewById(R.id.personal_peradd);
        mId.setEnabled(false);
        setVisibility(false);
        mSave.setVisibility(View.INVISIBLE);
        mCancel.setVisibility(view.INVISIBLE);
      //  mPattern = Pattern.compile(EMAIL_PATTERN);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVisibility(true);
                shouldEdit = true;
                //Button to save and Cancel to Update the Data to the Server
                mSave.setVisibility(View.VISIBLE);
                mCancel.setVisibility(View.VISIBLE);
            }
        });


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
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

        mDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        mSharedPreferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        final String token = mSharedPreferences.getString("token", null);
        enggID = getArguments().getString("id");
        menggName=getArguments().getString("engineerName");
        for (int i = 0; i < enggID.length(); i++) {
            Log.i(TAG, "emailID: " + enggID);
        }
        url=getResources().getString(R.string.url);
        mPersonal_url = getResources().getString(R.string.Personal_url);
        mPersonal_url=url+mPersonal_url;
        RequestParams requestParams = new RequestParams();
        requestParams.put("engineerId", enggID);
        Log.i(TAG, "onCreateView: engid    " + enggID);

       // Toast.makeText(getActivity(), "gettting id" + enggID, Toast.LENGTH_SHORT).show();
        //Sending the token in header and with all the required feilds to th ViewModel
        PersonalDetailViewModel personalDetailVModel = new PersonalDetailViewModel();
        personalDetailVModel.personalData(token, mPersonal_url, requestParams, this);

        prog = new ProgressUtil(getActivity());
        prog.showProgress("Loading...");
        Log.i(TAG, "onCreateView: token" + token);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callValidations()) {
                    prog.showProgress("Updating EmployeeData...");
                    mSharedPreferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
                    String Updatetoken = mSharedPreferences.getString("token", null);
                    Log.i(TAG, "token    shared:  " + Updatetoken);

                    RequestParams praParams = new RequestParams();
                 /* praParams.put("token","'jvbudvhdgd");*/
                    praParams.put("engineerId", mId.getText().toString());
                    praParams.put("emailId", mEmail.getText().toString());
                    praParams.put("mobile", mMobile.getText().toString());
                    Log.i(TAG, "onClick: " + mId.getText().toString());
                    praParams.put("dateOfBirth", mDOB.getText().toString());
                    praParams.put("fatherName", mFathername.getText().toString());
                    praParams.put("fatherMobile", mFathetmob.getText().toString());
                    praParams.put("occupation", mOccupation.getText().toString());
                    praParams.put("annualSalary", mAnnualsalary.getText().toString());
                    praParams.put("mumbaiAddress", mMumAdd.getText().toString());
                    praParams.put("permenantAddress", mPermanentAdd.getText().toString());
                    praParams.put("employeeName", menggName);
                    //Updating the edited data for that we need to create the updateviewmodelController
                    //call to  UpdatePersonalViewMOdel for update data

                    String hr_url = getResources().getString(R.string.updatePersonalData);
                    hr_url = url + hr_url;
                    UpdatePersonalViewMOdel updatePersonalViewMOdel = new UpdatePersonalViewMOdel();
                    updatePersonalViewMOdel.updatePersonData(Updatetoken, hr_url, praParams, PersonaldetailsFragment.this);
                    Log.i(TAG, "onClick: " + Updatetoken);

                }
                else{
                    Toast.makeText(getActivity(), "Invalid data...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVisibility(false);
                mCancel.setVisibility(View.INVISIBLE);
                mSave.setVisibility(View.INVISIBLE);
            }
        });
        return view;
    }

    private boolean callValidations() {
        boolean flag=false;
        if (shouldEdit) {
            if (mEmail.getText().toString().matches(EMAIL_PATTERN)) {
                if(mMobile.getText().length()==10){
                      if(mFathetmob.getText().length()==10) {
                          if (mDOB.getText().length() > 0 && mFathername.getText().length() > 0) {
                              flag = true;
                          } else {
                              flag = false;
                          }
                      }else {mFathetmob.setText("");mFathetmob.setHint("Mobile should 10 disit");flag=false;}
                }
                else{
                    if(mFathetmob.getText().length()!=10) {mFathetmob.setText("");mFathetmob.setHint("Mobile should 10 disit");flag=false;}

                     mMobile.setText("");mMobile.setHint("Mobile should 10 disit");  flag=false;
                }
            } else {
                //Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                //or
                mEmail.setText("");
                mEmail.setHint("Invald Email...");
            }
        }
    return flag;
    }

    @Override
    public void personalArrayListDetail(ArrayList<PersonalDetailsModel> personalDetailsModels) {

        prog.dismissProgress();
        PersonalDetailsModel personalModel = personalDetailsModels.get(0);
        Log.i(TAG, "sallary:  " + personalModel.getAnnualSalary());
        //Setting the data to View
        mId.setText(enggID);
        Log.i(TAG, "engnID: " + personalModel.getEngineerId());
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

   /* private void doAuthentication() {

        mMatcher = mPattern.matcher(mEmail.getText().toString());
        if (!mMatcher.matches()) {
            mEmail.setText("Email Is Not Valid...");
            mEmail.setFocusable(true);
        }
    }
*/

    @Override
    public void updatePersonalDetail(UpdatePersonalModel updatePersonalModel) {
        prog.dismissProgress();
        Log.i(TAG, "Updated status: " + updatePersonalModel.getmMessage());
        Log.i(TAG, "Updated status: " + updatePersonalModel.getmStatus());
        Toast.makeText(getActivity(), " "+updatePersonalModel.getmMessage()+"...", Toast.LENGTH_SHORT).show();
        mCancel.setVisibility(View.INVISIBLE);
        mSave.setVisibility(View.INVISIBLE);
        setVisibility(false);
    }

    @Override
    public void closeDialog() {
        prog.dismissProgress();

    }

    public  void setVisibility(Boolean stat){
        //mId.setEnabled(stat);
        mEmail.setEnabled(stat);
        mMobile.setEnabled(stat);
        mDOB.setEnabled(stat);
        mFathername.setEnabled(stat);
        mFathetmob.setEnabled(stat);
        mOccupation.setEnabled(stat);
        mAnnualsalary.setEnabled(stat);
        mMumAdd.setEnabled(stat);
        mPermanentAdd.setEnabled(stat);

    }
    public boolean isPhone(){
        return  true;
    }
    private void updateLabel() {

        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mDOB.setText(sdf.format(myCalendar.getTime()));
    }


}
