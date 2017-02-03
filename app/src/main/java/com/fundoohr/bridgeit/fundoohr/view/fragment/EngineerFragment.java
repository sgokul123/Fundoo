package com.fundoohr.bridgeit.fundoohr.view.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

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
public class EngineerFragment extends Fragment implements EnggViewModelInterface {
    String mEngineer_url;
    String arr[] = new String[35];

    ListView mListview;
    Context mContext;
    EditText mSearch;
    ProgressDialog mDailog;
    SharedPreferences mSharedPreferences;
    ArrayAdapter<String> adapter2;
    String searchName;

    ArrayList<String> strings;

    int k[] = {R.drawable.logo, R.drawable.person, R.drawable.image};


    @SuppressLint("ValidFragment")
    public EngineerFragment(Context applicationContext) {
        this.mContext = applicationContext;
       // this.mDailog = mProgressDialog;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_engineer, container, false);
        mListview = (ListView) view.findViewById(R.id.listView_engineer);
        mSearch = (EditText) view.findViewById(R.id.searchBox);


        SideBarEngineer indexBar = (SideBarEngineer) view.findViewById(R.id.sideBar);
        indexBar.setListView(mListview);


        mSharedPreferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String tokenHeader = mSharedPreferences.getString("token", null);
        mEngineer_url = getResources().getString(R.string.searchEmployeeByName_url);
        Log.i("EngineerFragment", "employeeList: token = " + tokenHeader);

        //Passing the parameters to ViewModel
        EnggFragViewModel enggFragViewModel = new EnggFragViewModel(mContext, mDailog);
        enggFragViewModel.employeeList(mEngineer_url, tokenHeader, this);


        mDailog= new ProgressDialog(getActivity());
        mDailog.setMessage("Loading....");
        mDailog.show();
        return view;
    }


    //Getting back the data from the controller through the interface
    @Override
    public void enggViewMInterface(ArrayList<EnggFragModel> enggFragModels) {

        if (mDailog != null) {
            mDailog.dismiss();
        }

        Log.i("EnggVIew", "enggViewMInterface: " + enggFragModels.get(8).getEmployeeName());
        Log.i("EnggVIew", "enggViewMInterface:id " + enggFragModels.get(3).getEngineerID());
         ArrayList<String> sortList = new ArrayList<>();
        for (int i = 0; i <enggFragModels.size() ; i++) {
            String name = enggFragModels.get(i).getEmployeeName();
            sortList.add(name);
        }
        Collections.sort(sortList);
        Log.i("sorting", "enggViewMInterface: "+sortList);

        for (int i = 0; i < enggFragModels.size() - 1; i++) {
            searchName = enggFragModels.get(i).getEmployeeName();
            Log.i("EnggVIew", "enggViewMInterface: " + searchName);
            //strArray = new String[] {enggFragModels.get(i).getEmployeeName()};
            strings = new ArrayList<>();
            strings.add(searchName);
            arr[i] = searchName;
            Bundle bundle = new Bundle();
            bundle.putStringArray("array",arr);

            Intent intent = getActivity().getIntent();
            intent.putExtras(bundle);

        }
/*
        for (int j = 0; j < arr.length; j++) {
            Log.i("arr", "enggViewMInterface: " + arr[j]);
        }*/

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.frame_engineer,R.id.name_engineer, arr);
        adapter2.notifyDataSetChanged();

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

        //sending the data to set on the CardView to EngineerSideBarAdapter
        EngineerSideBarAdapter adapter = new EngineerSideBarAdapter(enggFragModels, getActivity(),sortList);
        mListview.setAdapter(adapter);
       /* MySearchAdapter searchBox = new MySearchAdapter(mContext, enggFragModels);
        mListview.setAdapter(searchBox);*/

    }
}


