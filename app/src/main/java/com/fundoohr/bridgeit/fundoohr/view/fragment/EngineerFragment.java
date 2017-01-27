package com.fundoohr.bridgeit.fundoohr.view.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.bridgeit.fundoohr.adapter.EngineerSideBarAdapter;
import com.fundoohr.bridgeit.fundoohr.callback.EnggViewModelInterface;
import com.fundoohr.bridgeit.fundoohr.model.EnggFragModel;
import com.fundoohr.bridgeit.fundoohr.utility.SideBarEngineer;
import com.fundoohr.bridgeit.fundoohr.view.activity.DashBoard;
import com.fundoohr.bridgeit.fundoohr.viewmodel.EnggFragViewModel;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by bridgeit on 10/12/16.
 */
public class EngineerFragment extends Fragment implements EnggViewModelInterface{
    String mEngineer_url;
    ArrayList mData;
    ArrayList mEnggFragData;
    ListView mListview;
    ProgressDialog mProgressDialog;
    SharedPreferences mSharedPreferences;
    int k[] = {R.drawable.logo, R.drawable.person, R.drawable.image};
    String[] text = {""};


    public EngineerFragment() {

    }

    @SuppressLint("ValidFragment")
    public EngineerFragment(DashBoard dashBoard, ProgressDialog mDailog) {
        this.mProgressDialog=mDailog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_engineer, container, false);
        mListview = (ListView) view.findViewById(R.id.listView_engineer);
        mEnggFragData= new ArrayList();
        mData = new ArrayList();

        for (int i = 0; i < text.length; i++) {
            //mData.add(k[i]);
            mData.add(text[i]);
        }
        Collections.sort(mData);

        SideBarEngineer indexBar = (SideBarEngineer) view.findViewById(R.id.sideBar);
        indexBar.setListView(mListview);


       /* String token=mSharedPreferences.getString("token",null);*/
        mSharedPreferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String token=mSharedPreferences.getString("token",null);
        mEngineer_url=getResources().getString(R.string.searchEmployeeByName_url);
        Log.i("EngineerFragment", "employeeList: token = "+token);
        RequestParams requestParams = new RequestParams();
        requestParams.put("",token);
        EnggFragViewModel enggFragViewModel = new EnggFragViewModel();
        enggFragViewModel.employeeList(mEngineer_url,requestParams , this);
        return view;
    }

    @Override
    public void enggViewMInterface(ArrayList<EnggFragModel> enggFragModels) {
        Log.i("EnggVIew", "enggViewMInterface: " +enggFragModels.get(8).getEmployeeName());
        Log.i("EnggVIew", "enggViewMInterface:id " +enggFragModels.get(3).getEngineerID());
        EngineerSideBarAdapter adapter = new EngineerSideBarAdapter(enggFragModels, getActivity());
        mListview.setAdapter(adapter);
        mProgressDialog.dismiss();
    }
}


