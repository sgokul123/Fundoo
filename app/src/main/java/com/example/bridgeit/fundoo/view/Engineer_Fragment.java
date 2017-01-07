package com.example.bridgeit.fundoo.view;

import android.content.Intent;
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
import com.example.bridgeit.fundoo.callback.Tokenable;
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
public class Engineer_Fragment extends Fragment {

    /*public Fragment getInstance(String token) {
        Fragment fragment = new Engineer_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("token", token);
        setArguments(bundle);
       return  fragment;
    }*/
    ArrayList mData;
    ListView mListview;
    CardView mCardView;
    int k[] = {R.drawable.logo, R.drawable.person, R.drawable.image};
    String[] text = {"Apple", "Add", "Andrew", "Adam", "Bmm", "Mukesh", "Raj", "Queen", "Narayan", "Vijay", "Zebra", "Niraj", "King"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_engineer, container, false);
        mListview = (ListView) view.findViewById(R.id.listView_engineer);
     //   View view1 = inflater.inflate(R.layout.card_engineer, container, false);
     //   RequestParams params = new RequestParams();

     //        String data = getArguments().getString("token",null);
        /*Log.i("555", "jjj" + data);*/
    //    Log.i("Engineer", "onCreateView: "+data);
        /* LoginActivity loginActivity = new LoginActivity();*/

        mData = new ArrayList();

        for (int i = 0; i < text.length; i++) {
            //mData.add(k[i]);
            mData.add(text[i]);
        }
        Collections.sort(mData);

        Engineer_SideBar_adapter adapter = new Engineer_SideBar_adapter(getContext(), mData);
        mListview.setAdapter(adapter);
        SideBar_engineer indexBar = (SideBar_engineer) view.findViewById(R.id.sideBar);
        indexBar.setListView(mListview);
        return view;
    }
}
  /*  public void invokeWS(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.0.171:3000/searchEmployeeByName", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject search = new JSONObject(new String(responseBody));
                    String employeedata = (String) search.get("token");
                    Log.i("employee", "onSuccess:"+employeedata);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        }
    }*/

