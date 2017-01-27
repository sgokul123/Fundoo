package com.fundoohr.bridgeit.fundoohr.view.activity;

import android.app.DatePickerDialog;
//import android.support.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.GregorianCalendar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.bridgeit.fundoohr.adapter.GridCalenderAdapter;
import com.fundoohr.bridgeit.fundoohr.callback.AttendenceArrayInterface;
import com.fundoohr.bridgeit.fundoohr.model.AttendenceModel;
import com.fundoohr.bridgeit.fundoohr.view.fragment.BankingDetails;
import com.fundoohr.bridgeit.fundoohr.view.fragment.HRDetails;
import com.fundoohr.bridgeit.fundoohr.utility.MonthYearPickerDialog;
import com.fundoohr.bridgeit.fundoohr.view.fragment.Personaldetails;
import com.fundoohr.bridgeit.fundoohr.view.fragment.ProfileDetails;
import com.fundoohr.bridgeit.fundoohr.view.fragment.TrackingDetails;
import com.fundoohr.bridgeit.fundoohr.viewmodel.AttendenceViewModel;
import com.loopj.android.http.RequestParams;

//import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AttendanceDetails extends AppCompatActivity implements AttendenceArrayInterface {
    DatePickerDialog.OnDateSetListener pick;
    Button save, cancel, down_arrow;
    TextView textView;
    GridView calendarView;
    static String mEngeerId;
    int getmonth,getyear;
    ArrayList<AttendenceModel> attendenceModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance__details);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        textView = (TextView) findViewById(R.id.datedisplay);
        down_arrow = (Button) findViewById(R.id.date);
        save = (Button) findViewById(R.id.btn_save);
        cancel = (Button) findViewById(R.id.btn_cancel);
        save.setVisibility(View.GONE);
        cancel.setVisibility(View.GONE);
        SharedPreferences preferences = this.getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String tokenValue = preferences.getString("token", null);
        Log.i("engg collap", "callPersonal: "+tokenValue);
        mEngeerId=getIntent().getExtras().getString("engineerId");
        Log.i("oncreate", "onCreate: "+mEngeerId);
        String[] monthNames =getResources().getStringArray(R.array.Month);

        down_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MonthYearPickerDialog pd = new MonthYearPickerDialog();
                pd.setListener(AttendanceDetails.this, pick);
                pd.show(getFragmentManager(), "MonthYearPickerDialog");
            }
        });
         getmonth = getIntent().getIntExtra("month", 0);
         getyear = getIntent().getIntExtra("year", 0);
        long timestamp= getIntent().getLongExtra("timeStamp", 0);
        // set data to textview
        textView.setText(monthNames[getmonth] + "," + getyear);
        //get current month and year
        Calendar _calendar = Calendar.getInstance(Locale.getDefault());
        int mMonth = _calendar.get(Calendar.MONTH) + 1;
        int mYear = _calendar.get(Calendar.YEAR);
        int mDate = _calendar.get(Calendar.DATE);
        calendarView = (GridView) findViewById(R.id.calendar);
        AttendenceViewModel attendenceViewModel = new AttendenceViewModel();
        attendenceViewModel.attendViewModelData(timestamp,mEngeerId,tokenValue,this);


        MonthYearPickerDialog month = new MonthYearPickerDialog();
        getFragmentManager().beginTransaction().replace(R.id.attendance_frame,month.newInstance(mEngeerId));
        Log.i("Attend", "onCreate: "+mEngeerId);
        GridCalenderAdapter adapter = new GridCalenderAdapter(AttendanceDetails.this,attendenceModels, getmonth+1, getyear, save, cancel);
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menupop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.attendence:
                Toast.makeText(getApplicationContext(), "1st selected", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.personal:
                Toast.makeText(getApplicationContext(), "2nd selected", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.attendance_frame, new Personaldetails()).commit();
                return true;
            case R.id.profile:
                Toast.makeText(getApplicationContext(), "3rd selected", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.attendance_frame, new ProfileDetails()).commit();
                return true;
            case R.id.hr_details:
                Toast.makeText(getApplicationContext(), "4th selected", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.attendance_frame, new HRDetails()).commit();
                return true;
            case R.id.bank:
                Toast.makeText(getApplicationContext(), "5th selected", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.attendance_frame, new BankingDetails()).commit();
                return true;
            case R.id.tracking:
                Toast.makeText(getApplicationContext(), "6th selected", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.attendance_frame, new TrackingDetails()).commit();
                return true;
            default:

                return super.onOptionsItemSelected(item);

        }

    }


    @Override
    public void getAttendArrayData(ArrayList<AttendenceModel> attendenceModels) {
                this.attendenceModels = attendenceModels;
        Log.i("attendenceModels", "getAttendArrayData: "+attendenceModels.size());


    }
}


