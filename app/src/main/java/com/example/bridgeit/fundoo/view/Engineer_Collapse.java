package com.example.bridgeit.fundoo.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.bridgeit.fundoo.R;
import com.example.bridgeit.fundoo.callback.EnggViewModelInterface;
import com.example.bridgeit.fundoo.model.EnggFragModel;

import java.util.ArrayList;

/**
 * Created by bridgeit on 17/12/16.
 */
public class Engineer_Collapse extends AppCompatActivity implements EnggViewModelInterface{
    TextView mId,mStatus,mCompany,mMobile,mEmail,mBLStart,mCompanyStart, mCompanyLeave,mLeave;
    SharedPreferences mSharedPreferences;
    Context context;
    CollapsingToolbarLayout mCollapsingToolbar;
    Personal_details personalDetails = new Personal_details();
    public Engineer_Collapse() {
    }

    public Engineer_Collapse (Context context ) {
         this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.engineer_collapse);
        mId= (TextView) findViewById(R.id.id_engg);
        mStatus= (TextView) findViewById(R.id.status_engg);
        mCompany= (TextView) findViewById(R.id.company_engg);
        mMobile= (TextView) findViewById(R.id.mobile_engg);
        mEmail= (TextView) findViewById(R.id.email_engg);
        mBLStart= (TextView) findViewById(R.id.blstart_engg);
        mCompanyStart= (TextView) findViewById(R.id.companystart_engg);
        mCompanyLeave = (TextView) findViewById(R.id.companyleave_engg);
        mLeave= (TextView) findViewById(R.id.leave_engg);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
         setSupportActionBar(toolbar);
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        setTitleColor(0xD8D1C7);
       //Onclick of the CardView from EngineerSideBarAdapter the data is obtained
        // through bundle which is set according to the position
       Bundle bundle = getIntent().getExtras().getBundle("data");
        if (bundle != null){
            mId.setText(bundle.getString("id"));
            mStatus.setText(bundle.getString("status"));
            mCompany.setText(bundle.getString("company"));
            mMobile.setText(bundle.getString("mobile"));
            mEmail.setText(bundle.getString("emailid"));
            mBLStart.setText(bundle.getString("blStart"));
            mCompanyStart.setText(bundle.getString("companyStart"));
            mCompanyLeave.setText(bundle.getString("CompanyLeave"));
            mLeave.setText(bundle.getString("leave"));
            mCollapsingToolbar.setTitle(bundle.getString("name"));

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menupop,menu);
        return true;
    }
    //Navigation Menu OPtions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.attendence:
                Toast.makeText(getApplicationContext(), "1st selected", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Engineer_Collapse.this,Attendance_Details.class);
                startActivity(intent);
                return true;
            case R.id.personal:
                Toast.makeText(getApplicationContext(), "2nd selected", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.collapse_frame,personalDetails).commit();
                return true;
            case R.id.profile:
                Toast.makeText(getApplicationContext(), "3rd selected", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.collapse_frame,new Profile_Details()).commit();
                return true;
            case R.id.hr_details:
                Toast.makeText(getApplicationContext(), "4th selected", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.collapse_frame,new HRDetails()).commit();
                return true;
            case R.id.bank:
                Toast.makeText(getApplicationContext(), "5th selected", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.collapse_frame,new BankingDetails()).commit();
                return true;
            case R.id.tracking:
                Toast.makeText(getApplicationContext(), "6th selected", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.collapse_frame,new TrackingDetails()).commit();
                return true;
            default:

                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public void enggViewMInterface(ArrayList<EnggFragModel> enggFragModels) {
        EnggFragModel enggFragModel = enggFragModels.get(0);
        Log.i("collapse", "enggViewMInterface: " +enggFragModel.getEmployeeName());
    }
}

