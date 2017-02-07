/*
package com.fundoohr.bridgeit.fundoohr.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.bridgeit.fundoohr.adapter.EngineerSideBarAdapter;
import com.fundoohr.bridgeit.fundoohr.callback.EnggViewModelInterface;
import com.fundoohr.bridgeit.fundoohr.model.EnggFragModel;
import com.fundoohr.bridgeit.fundoohr.utility.SideBarEngineer;
import com.fundoohr.bridgeit.fundoohr.viewmodel.EnggFragViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.Inflater;


public class SearchBarActivity extends Activity implements EnggViewModelInterface {

    ListView mListview;
    ArrayAdapter<String> myAdapter;
    ListView listView;
    String mEngineer_url;
    ArrayList<String> sortList, data;
    String[] dataArray = new String[] {};
    ProgressDialog mDailog;
    Bundle i=new Bundle();
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_engineer);

        mListview = (ListView) findViewById(R.id.listView_engineer);
        //mSearch = (EditText) view.findViewById(R.id.searchBox);


        SideBarEngineer indexBar = (SideBarEngineer) findViewById(R.id.sideBar);
        indexBar.setListView(mListview);


        //==========================
      */
/* ArrayList<String> data = getIntent().getStringArrayListExtra("employee");
        Log.i("sort", "onCreate: "+data);
        String[] dfg= data.toArray(new String[data.size()]);
        Log.i("stringArray", "onCreate: "+dfg);*//*

        listView = (ListView) findViewById(R.id.listView_engineer);

       // data=open(i);

        dataArray = new String[]{"Abhishek Ganguly", "Amit R Singh", "Anuj Sachan", "Bhavana B.K.", "Bhushan", "Dhareppa Metri", "Dhiresh Bhoir", "Divakar Y.N.", "Durga Madhab Mishra", "Hamid", "Jyotish K.M.", "Kalitha H.N.", "Khan Suhail Ahmad", "Krushna Bhamare", "Mandalkar Rahul", "Mohan", "Mukesh", "Naveen P.V.", "Nikita H.N", "Niraj Pujari", "Prashant Praveen", "Prashant Thorat", "Praveen T.", "Priyanka", "Satyendra Singh", "Sharad Vanjari", "Shilpa Reddy", "Shraddha Mane", "Shwetali", "Sonawane Gokul", "Swati S.D.", "Tanvi Raul", "Umashankar Chaurasiya", "Vaibhav Patil", "Vikash Kumar Prasad", "Vivek H."};
        Log.i("SearchBarActivity", "onCreate: ................................."+data);

        myAdapter = new ArrayAdapter<String>(this,R.layout.activity_card_engineer,R.id.name_engineer , dataArray);
        listView.setAdapter(myAdapter);
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                System.out.println(arg2+" --postion");
            }
        });

        mSharedPreferences = this.getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String tokenHeader = mSharedPreferences.getString("token", null);
        mEngineer_url = getResources().getString(R.string.searchEmployeeByName_url);
        Log.i("EngineerFragment", "employeeList: token = " + tokenHeader);

        //Passing the parameters to ViewModel
        EnggFragViewModel enggFragViewModel = new EnggFragViewModel(this, mDailog);
        enggFragViewModel.employeeList(mEngineer_url, tokenHeader, this);

        mDailog= new ProgressDialog(this);
        mDailog.setMessage("Loading....");
        mDailog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
       // SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        SearchView searchView = (SearchView) findViewById(R.id.searchBox);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextChange(String newText)
            {
                // this is your adapter that will be filtered
                myAdapter.getFilter().filter(newText);
                return true;
            }
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                // this is your adapter that will be filtered
                myAdapter.getFilter().filter(query);
                return true;
            }
        };
        searchView.setOnQueryTextListener(textChangeListener);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public void enggViewMInterface(ArrayList<EnggFragModel> enggFragModels) {
        if (mDailog != null) {
            mDailog.dismiss();
        }

        Log.i("EnggVIew", "enggViewMInterface: " + enggFragModels.get(8).getEmployeeName());
        Log.i("EnggVIew", "enggViewMInterface:id " + enggFragModels.get(3).getEngineerID());
        sortList = new ArrayList<>();
        for (int i = 0; i <enggFragModels.size() ; i++) {
            String name = enggFragModels.get(i).getEmployeeName();
            sortList.add(name);
        }
        Collections.sort(sortList);
        Log.i("sorting", "enggViewMInterface: "+sortList);

        i.putStringArrayList("key1", sortList); // Key1
        open(i);


        //sending the data to set on the CardView to EngineerSideBarAdapter
        EngineerSideBarAdapter adapter = new EngineerSideBarAdapter(enggFragModels, this,sortList);
        mListview.setAdapter(adapter);
    }

    private ArrayList<String> open(Bundle args) {
        ArrayList<String> empName = args.getStringArrayList("key1"); //// Key1
        Log.i("SearchbarAct", "open: ........."+empName);
        return empName;
    }
}
*/
