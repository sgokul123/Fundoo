package com.example.bridgeit.fundoo.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bridgeit.fundoo.R;
import com.example.bridgeit.fundoo.adapter.Engineer_SideBar_adapter;
import com.example.bridgeit.fundoo.callback.EnggViewModelInterface;
import com.example.bridgeit.fundoo.callback.Tokenable;
import com.example.bridgeit.fundoo.model.EnggFragModel;
import com.example.bridgeit.fundoo.viewmodel.EnggFragViewModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import cz.msebera.android.httpclient.Header;

/**
 * Created by bridgeit on 10/12/16.
 */
public class Engineer_Fragment extends Fragment implements EnggViewModelInterface{

    ArrayList mData;
    ArrayList mEnggFragData;
    ListView mListview;
    ProgressDialog mProgressDialog;
    SharedPreferences mSharedPreferences;
    int k[] = {R.drawable.logo, R.drawable.person, R.drawable.image};
    String[] text = {""};

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

        SideBar_engineer indexBar = (SideBar_engineer) view.findViewById(R.id.sideBar);
        indexBar.setListView(mListview);


       /* String token=mSharedPreferences.getString("token",null);*/
        mSharedPreferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String token=mSharedPreferences.getString("token",null);
        Log.i("Engineer_Fragment", "employeeList: token = "+token);
        RequestParams requestParams = new RequestParams();
        requestParams.put("",token);
        EnggFragViewModel enggFragViewModel = new EnggFragViewModel();
        enggFragViewModel.employeeList(requestParams , this);
        return view;
    }

    @Override
    public void enggViewMInterface(ArrayList<EnggFragModel> enggFragModels) {
        Log.i("EnggVIew", "enggViewMInterface: " +enggFragModels.get(8).getEmployeeName());
        Log.i("EnggVIew", "enggViewMInterface:id " +enggFragModels.get(3).getEngineerID());
        Engineer_SideBar_adapter adapter = new Engineer_SideBar_adapter(enggFragModels, getActivity());
        mListview.setAdapter(adapter);

    }
}


