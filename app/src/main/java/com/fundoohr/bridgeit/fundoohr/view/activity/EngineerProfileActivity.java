package com.fundoohr.bridgeit.fundoohr.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.bridgeit.fundoohr.callback.EnggViewModelInterface;
import com.fundoohr.bridgeit.fundoohr.model.EnggFragModel;
import com.fundoohr.bridgeit.fundoohr.view.fragment.BankingDetails;
import com.fundoohr.bridgeit.fundoohr.view.fragment.EngineerFragment;
import com.fundoohr.bridgeit.fundoohr.view.fragment.HRDetails;
import com.fundoohr.bridgeit.fundoohr.view.fragment.Personaldetails;
import com.fundoohr.bridgeit.fundoohr.view.fragment.ProfileDetails;
import com.fundoohr.bridgeit.fundoohr.view.fragment.TrackingDetails;

import java.util.ArrayList;

/**
 * Created by bridgeit on 17/12/16.
 */
public class EngineerProfileActivity extends AppCompatActivity implements EnggViewModelInterface {
    ProgressDialog mProgressDialog;
    TextView mId, mStatus, mCompany, mMobile, mEmail, mBLStart, mCompanyStart, mCompanyLeave, mLeave;
    Context context;
    CollapsingToolbarLayout mCollapsingToolbar;
    String mEnggId;
    Bundle bundle;

    public EngineerProfileActivity() {
    }

    public EngineerProfileActivity(Context context) {
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.engineer_collapse);
        mId = (TextView) findViewById(R.id.id_engg);
        mStatus = (TextView) findViewById(R.id.status_engg);
        mCompany = (TextView) findViewById(R.id.company_engg);
        mMobile = (TextView) findViewById(R.id.mobile_engg);
        mEmail = (TextView) findViewById(R.id.email_engg);
        mBLStart = (TextView) findViewById(R.id.blstart_engg);
        mCompanyStart = (TextView) findViewById(R.id.companystart_engg);
        mCompanyLeave = (TextView) findViewById(R.id.companyleave_engg);
        mLeave = (TextView) findViewById(R.id.leave_engg);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        setTitleColor(0xD8D1C7);
        //Onclick of the CardView from EngineerSideBarAdapter the data is obtained
        // through bundle which is set according to the position
        Bundle bundle = getIntent().getExtras().getBundle("data");
        if (bundle != null) {
            mEnggId = bundle.getString("id");
            mId.setText(mEnggId);
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
        getMenuInflater().inflate(R.menu.menupop, menu);
        return true;
    }

    //Navigation Menu OPtions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.attendence:
                Toast.makeText(getApplicationContext(), "1st selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EngineerProfileActivity.this, AttendanceDetailsActivity.class);
                intent.putExtra("engineerId", mEnggId);
                startActivity(intent);
                return true;
            case R.id.personal:
                Toast.makeText(getApplicationContext(), "2nd selected", Toast.LENGTH_SHORT).show();
                callPersonal();
                return true;
            case R.id.profile:
                Toast.makeText(getApplicationContext(), "3rd selected", Toast.LENGTH_SHORT).show();
                callProfile();
                return true;
            case R.id.hr_details:
                Toast.makeText(getApplicationContext(), "4th selected", Toast.LENGTH_SHORT).show();
                callHr();
                return true;
            case R.id.bank:
                Toast.makeText(getApplicationContext(), "5th selected", Toast.LENGTH_SHORT).show();
                callBanking();
                return true;
            case R.id.tracking:
                Toast.makeText(getApplicationContext(), "6th selected", Toast.LENGTH_SHORT).show();
                callTracking();
                return true;
            default:

                return super.onOptionsItemSelected(item);

        }

    }

    public void callPersonal() {
        bundle = getData();
        Personaldetails fragobj = new Personaldetails();
        fragobj.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.collapse_frame, fragobj).addToBackStack(null).commit();

    }


    public void callProfile() {
        bundle = getData();
        ProfileDetails prof = new ProfileDetails();
        prof.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.collapse_frame, prof).addToBackStack(null).commit();
    }

    public void callHr() {
        bundle = getData();
        HRDetails hr = new HRDetails();
        hr.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.collapse_frame, hr).addToBackStack(null).commit();

    }

    public void callTracking() {
        bundle = getData();
        TrackingDetails track = new TrackingDetails();
        track.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.collapse_frame, track).addToBackStack(null).commit();
    }


    public void callBanking() {
        bundle = getData();
        BankingDetails bank = new BankingDetails();
        bank.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.collapse_frame, bank).addToBackStack(null).commit();
    }

    private Bundle getData() {
        if (mEnggId != null) {
            SharedPreferences preferences = this.getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
            String tokenValue = preferences.getString("token", null);
            Toast.makeText(EngineerProfileActivity.this, "" + mEnggId, Toast.LENGTH_SHORT).show();
            Log.i("engg collap", "callPersonal: " + tokenValue);
            bundle = new Bundle();
            bundle.putString("id", mEnggId);
            bundle.putString("token", tokenValue);
            return bundle;
        } else {
            Toast.makeText(EngineerProfileActivity.this, "Wait a While", Toast.LENGTH_SHORT).show();
        }
        return bundle;

    }


    @Override
    public void enggViewMInterface(ArrayList<EnggFragModel> enggFragModels) {
        EnggFragModel enggFragModel = enggFragModels.get(0);
        Log.i("collapse", "enggViewMInterface: " + enggFragModel.getEmployeeName());
    }
}

