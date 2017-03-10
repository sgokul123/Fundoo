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
import com.fundoohr.callback.ProfileDetailUpdateInteface;
import com.fundoohr.callback.TrackingDetailArrayInterface;
import com.fundoohr.model.TrackingDetailsModel;
import com.fundoohr.model.UpdatePersonalModel;
import com.fundoohr.utility.ProgressUtil;
import com.fundoohr.viewmodel.TrackingDetailViewModel;
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
public class TrackingDetailsFragment extends Fragment implements TrackingDetailArrayInterface {
    private  static  String TAG ="TrackingDetailsFragment";
    ProgressUtil prog;
    Button mSave,mCancel;
    String mTrack_url,token,engineerId;
    ImageButton mEditbutton;
    EditText mEditText, mEditTextStartDate, mEditTextEndDate, mEditTextCurrWeek, mEditTextWeekLeft, mEditTextWeek1;
    SharedPreferences mSharedPreferences;
    private String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracking_details, container, false);
        mSave= (Button) view.findViewById(R.id.save);
        mCancel = (Button) view.findViewById(R.id.cancel);
        mEditbutton = (ImageButton) view.findViewById(R.id.track_edit);
        mEditText = (EditText) view.findViewById(R.id.track_stack);
        mEditTextStartDate = (EditText) view.findViewById(R.id.track_startdate);
        mEditTextEndDate = (EditText) view.findViewById(R.id.track_enddate);
        mEditTextCurrWeek = (EditText) view.findViewById(R.id.track_currweek);
        mEditTextWeekLeft = (EditText) view.findViewById(R.id.track_weekleft);
        mEditTextWeek1 = (EditText) view.findViewById(R.id.track_week1);

        setVisibility(false);
        mSave.setVisibility(View.INVISIBLE);
        mCancel.setVisibility(view.INVISIBLE);
        mEditbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // mEditText.setEnabled(true); // to enable it
                setVisibility(true);
                //Button to save and Cancel to Update the Data to the Server
                mSave.setVisibility(View.VISIBLE);
                mCancel.setVisibility(View.VISIBLE);

            }
        });
        mSharedPreferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        token= mSharedPreferences.getString("token", null);
        engineerId= getArguments().getString("id");
        RequestParams requestParams = new RequestParams();
        requestParams.put("engineerId", engineerId);
        Log.i("HR", "onCreateView: token" + token);
        Log.i("HR", "onCreateView: token" + engineerId);
        url=getResources().getString(R.string.url);
        mTrack_url=getResources().getString(R.string.Tracking_url);
        mTrack_url=url+mTrack_url;
        TrackingDetailViewModel trackViewModel = new TrackingDetailViewModel();
        trackViewModel.trackingData(token,mTrack_url,requestParams, this);
        prog = new ProgressUtil(getActivity());
        prog.showProgress("Loading...");
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick : ...");
                prog.showProgress("Updating Employee Tracking Data ...");
                RequestParams paramData =new RequestParams();
                paramData.put("techStack",mEditText.getText().toString());
                paramData.put("bridgelabzStartDate", mEditTextStartDate.getText().toString());
                paramData.put("bridgelabzEndDate", mEditTextEndDate.getText().toString());
                paramData.put("currentWeek", mEditTextCurrWeek.getText().toString());
                paramData.put("numberOfWeeksLeft", mEditTextWeekLeft.getText().toString());
                paramData.put("week1", mEditTextWeek1.getText().toString());
                paramData.put("engineerId",engineerId);
                String hr_url=getResources().getString(R.string.Tracking_update_url);
                hr_url=url+hr_url;
                TrackingDetailViewModel trackingDetailViewModel =new TrackingDetailViewModel();
                trackingDetailViewModel.updateProfile(token,hr_url,paramData, new ProfileDetailUpdateInteface() {
                    @Override
                    public void callforUpdate(UpdatePersonalModel updatePersonalModel) {
                        prog.dismissProgress();
                        Log.i(TAG, "status : "+updatePersonalModel.getmStatus());
                        Log.i(TAG, "Message: "+updatePersonalModel.getmMessage());
                        Toast.makeText(getActivity(),updatePersonalModel.getmMessage()+ " ...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void closeDilog() {
                        prog.dismissProgress();
                    }
                });
                setVisibility(false);
                mCancel.setVisibility(View.INVISIBLE);
                mSave.setVisibility(View.INVISIBLE);
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

    @Override
    public void trackingData(ArrayList<TrackingDetailsModel> trackingDetailsModels) {
        prog.dismissProgress();
        TrackingDetailsModel trackingModel = trackingDetailsModels.get(0);
        mEditText.setText(trackingModel.getTechStack());
        mEditTextStartDate.setText(trackingModel.getBridgelabzStartDate());
        mEditTextEndDate.setText(trackingModel.getBridgelabzEndDate());
        mEditTextCurrWeek.setText(trackingModel.getCurrentWeek());
        mEditTextWeekLeft.setText(trackingModel.getNumberOfWeeksLeft());
        mEditTextWeek1.setText(trackingModel.getWeek1());
        Log.i("TRac", "trackingData: "+trackingModel.getWeek1());
    }

    @Override
    public void closeDialog() {
        prog.dismissProgress();
    }

    public  void setVisibility(Boolean stat){
        mEditText.setEnabled(stat);
        mEditTextStartDate.setEnabled(stat);
        mEditTextEndDate.setEnabled(stat);
        mEditTextCurrWeek.setEnabled(stat);
        mEditTextWeekLeft.setEnabled(stat);
        mEditTextWeek1.setEnabled(stat);

    }
}
