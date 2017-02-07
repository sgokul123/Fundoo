/*
package com.fundoohr.bridgeit.fundoohr.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;


import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.bridgeit.fundoohr.adapter.MySearchAdapter;
import com.fundoohr.bridgeit.fundoohr.model.SearchModel;

import java.util.ArrayList;

public class SearchEngineer extends AppCompatActivity {
    EditText mSearch;
    ListView mListView;
    private ArrayList<SearchModel> mSearchList = new ArrayList<SearchModel>();
    private MySearchAdapter adapter_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_engineer);
        initialize();

        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter_search.getFilter().filter(charSequence.toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter_search.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void initialize(){
        mSearch= (EditText) findViewById(R.id.searchBox);
        mListView= (ListView) findViewById(R.id.listView_engineer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSearchList.add(new SearchModel("Adam"));
        mSearchList.add(new SearchModel("Adam"));
        mSearchList.add(new SearchModel("Vijay"));
        mSearchList.add(new SearchModel("Zebra"));
        mSearchList.add(new SearchModel("Narayan"));

    }
}
*/
