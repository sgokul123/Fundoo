package com.fundoohr.bridgeit.fundoohr.view.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.bridgeit.fundoohr.adapter.EngineerSideBarAdapter;

import com.fundoohr.bridgeit.fundoohr.adapter.MySearchAdapter;
import com.fundoohr.bridgeit.fundoohr.callback.EnggViewModelInterface;
import com.fundoohr.bridgeit.fundoohr.model.EnggFragModel;
import com.fundoohr.bridgeit.fundoohr.utility.SideBarEngineer;
import com.fundoohr.bridgeit.fundoohr.viewmodel.EnggFragViewModel;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by bridgeit on 10/12/16.
 */
@SuppressLint("ValidFragment")
public class EngineerFragment extends Fragment implements EnggViewModelInterface{
    String mEngineer_url;
    ArrayList mData;
    ArrayList mEnggFragData;
    ListView mListview;
    Context mContext;
    EditText mSearch;
    ProgressDialog mDailog;
    SharedPreferences mSharedPreferences;
    ArrayAdapter<String> adapter2;
    String searchName;
    String[] strArray;
    ArrayList<String> strings;

    int k[] = {R.drawable.logo, R.drawable.person, R.drawable.image};
    String[] text = {""};

    @SuppressLint("ValidFragment")
    public EngineerFragment(Context applicationContext, ProgressDialog mProgressDialog) {
        this.mContext=applicationContext;
        this.mDailog=mProgressDialog;
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_engineer, container, false);
        mListview = (ListView) view.findViewById(R.id.listView_engineer);
        mSearch= (EditText) view.findViewById(R.id.searchBox);
        mEnggFragData= new ArrayList();
        mData = new ArrayList();

        for (int i = 0; i < text.length; i++) {
            //mData.add(k[i]);
            mData.add(text[i]);
        }
        Collections.sort(mData);



        SideBarEngineer indexBar = (SideBarEngineer) view.findViewById(R.id.sideBar);
        indexBar.setListView(mListview);



        mSharedPreferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String tokenHeader=mSharedPreferences.getString("token",null);
        mEngineer_url=getResources().getString(R.string.searchEmployeeByName_url);
        Log.i("EngineerFragment", "employeeList: token = "+tokenHeader);

        //Passing the parameters to ViewModel
        EnggFragViewModel enggFragViewModel = new EnggFragViewModel(mContext,mDailog);
        enggFragViewModel.employeeList(mEngineer_url,tokenHeader, this);
        return view;
    }


    //Getting back the data from the controller through the interface
    @Override
    public void enggViewMInterface(ArrayList<EnggFragModel> enggFragModels) {

        Log.i("EnggVIew", "enggViewMInterface: " +enggFragModels.get(8).getEmployeeName());
        Log.i("EnggVIew", "enggViewMInterface:id " +enggFragModels.get(3).getEngineerID());


        for (int i = 0; i <enggFragModels.size()-1 ; i++) {
            searchName = enggFragModels.get(i).getEmployeeName();
            Log.i("EnggVIew", "enggViewMInterface: "+searchName);
            //strArray = new String[] {enggFragModels.get(i).getEmployeeName()};
            strings = new ArrayList<>();
            strings.add(searchName);


        }
        adapter2 = new ArrayAdapter<String>(getActivity(),R.layout.activity_card_engineer,
                R.id.name_engineer,strings);


        mListview.setAdapter(adapter2);
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter2.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        EngineerSideBarAdapter adapter = new EngineerSideBarAdapter(enggFragModels, getActivity());
        mListview.setAdapter(adapter);
       /* MySearchAdapter searchBox = new MySearchAdapter(mContext, enggFragModels);
        mListview.setAdapter(searchBox);*/

        if(mDailog != null) {
            mDailog.dismiss();
        }

    }
}


