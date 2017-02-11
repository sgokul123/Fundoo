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
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.bridgeit.fundoohr.adapter.EngineerSideBarAdapter;


import com.fundoohr.bridgeit.fundoohr.adapter.MySearchAdapter;
import com.fundoohr.bridgeit.fundoohr.callback.EnggViewModelInterface;
import com.fundoohr.bridgeit.fundoohr.model.EnggFragModel;
import com.fundoohr.bridgeit.fundoohr.utility.SideBarEngineer;
import com.fundoohr.bridgeit.fundoohr.viewmodel.EnggFragViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bridgeit on 10/12/16.
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
public class EngineerFragment extends Fragment implements EnggViewModelInterface {
    String mEngineer_url;
    //String arr[] = new String[35];
    List<String> arr=new ArrayList<>();
    // List<String> arrCopy=new ArrayList<>();

    ListView mListview;
    Context mContext;
    EditText mSearch;
    ProgressDialog mDailog;
    SharedPreferences mSharedPreferences;
    MySearchAdapter adapter2;
    String searchName;
    SideBarEngineer indexBar;

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


        indexBar = (SideBarEngineer) view.findViewById(R.id.sideBar);


        mSharedPreferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String tokenHeader = mSharedPreferences.getString("token", null);
        mEngineer_url = getResources().getString(R.string.searchEmployeeByName_url);
        Log.i("EngineerFragment", "employeeList: token = " + tokenHeader);

        //Passing the parameters to ViewModel
        EnggFragViewModel enggFragViewModel = new EnggFragViewModel();
        enggFragViewModel.employeeList(mEngineer_url, tokenHeader, this);
        mDailog = new ProgressDialog(getActivity());
        mDailog.setMessage("Loading....");
        mDailog.show();
        return view;
    }


    //Getting back the data from the controller through the interface
    @Override
    public void enggViewMInterface(ArrayList<EnggFragModel> enggFragModels) {
        mDailog.dismiss();
        Log.i("EnggVIew", "enggViewMInterface: " + enggFragModels.get(8).getEmployeeName());
        Log.i("EnggVIew", "enggViewMInterface:id " + enggFragModels.get(3).getEngineerID());

        for (int i = 0; i < enggFragModels.size() - 1; i++) {
            searchName = enggFragModels.get(i).getEmployeeName();
            Log.i("EnggVIew", "enggViewMInterface: " + searchName);
            //strArray = new String[] {enggFragModels.get(i).getEmployeeName()};
            strings = new ArrayList<>();
            strings.add(searchName);
            arr.add(searchName);
           // arrCopy.add(searchName);
        }
            /*Bundle bundle = new Bundle();
            bundle.putStringArray("array",arr);

            Intent intent = getActivity().getIntent();
            intent.putExtras(bundle);*/


/*
        for (int j = 0; j < arr.length; j++) {
            Log.i("arr", "enggViewMInterface: " + arr[j]);
        }*/

        //sending the data to set on the CardView to EngineerSideBarAdapter
        EngineerSideBarAdapter adapter = new EngineerSideBarAdapter(enggFragModels, getActivity(), (ArrayList<String>) arr);
        mListview.setAdapter(adapter);

        indexBar.setListView(mListview);
        indexBar.setCustomTouchListener(this);

        adapter2 = new MySearchAdapter(mContext, enggFragModels);

        //adapter2.notifyDataSetChanged();

        mListview.setAdapter(adapter2);
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Filter filter = adapter2.getFilter();
                filter.filter(charSequence);



               /* if(charSequence.length()==0){
                    adapter2.getFilter().filter(charSequence);
                    //when search field is empty display full list
                    arr.clear();
                    for(String name: arrCopy){
                        arr.add(name);
                    }
                    //When arr list is updated call notifyDataSetChanged to update listview
                    adapter2.notifyDataSetChanged();
                }else{
                   *//* filterArrayList(charSequence.toString());*//*
                    //When arr list is updated call notifyDataSetChanged to update listview
                    adapter2.notifyDataSetChanged();
                }*/


            }

            @Override
            public void afterTextChanged(Editable editable) {
                //adapter2.notifyDataSetChanged();
            }
        });



       /* MySearchAdapter searchBox = new MySearchAdapter(mContext, enggFragModels);
        mListview.setAdapter(searchBox);*/

    }

    @Override
    public void getCharClicked(int position, char c) {
        Filter filter = adapter2.getFilter();
        filter.filter(String.valueOf(c));
    }





   /* //This method used to filter list according to the search key word
    private void filterArrayList(String  searchKeyWord) {
        arr.clear();
        for (String curVal : arrCopy){
            if (curVal.contains(searchKeyWord)){
                arr.add(curVal);
            }
        }

    }
*/}