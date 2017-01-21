package com.example.bridgeit.fundoo.view;

import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
//import android.support.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bridgeit.fundoo.R;
import com.example.bridgeit.fundoo.adapter.GridCalenderAdapter;

//import com.firebase.client.Firebase;

import java.util.Calendar;
import java.util.Locale;

import static java.security.AccessController.getContext;

public class Attendance_Details extends AppCompatActivity {
    DatePickerDialog.OnDateSetListener pick;
    Button save, cancel, down_arrow;
    TextView textView;

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

        String[] monthNames = new String[]{"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        down_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MonthYearPickerDialog pd = new MonthYearPickerDialog();
                pd.setListener(Attendance_Details.this, pick);
                pd.show(getFragmentManager(), "MonthYearPickerDialog");
                //Creating fire base object
               /* Firebase ref= new Firebase(MonthsData.FIREBASEURL);*/

                //value event listener to fetch the data from firebase by its overide method
               /* ref.addValueEventListener*/

            }
        });
        int getmonth = getIntent().getIntExtra("month", 0);
        int getyear = getIntent().getIntExtra("year", 0);
        // set data to textview
        textView.setText(monthNames[getmonth] + "," + getyear);
        //get current month and year
        Calendar _calendar = Calendar.getInstance(Locale.getDefault());
        int mMonth = _calendar.get(Calendar.MONTH) + 1;
        int mYear = _calendar.get(Calendar.YEAR);
        int mDate = _calendar.get(Calendar.DATE);
        GridView calendarView = (GridView) findViewById(R.id.calendar);
        GridCalenderAdapter adapter = new GridCalenderAdapter(Attendance_Details.this, getmonth+1, getyear, save, cancel);
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
                getSupportFragmentManager().beginTransaction().replace(R.id.attendance_frame, new Personal_details()).commit();
                return true;
            case R.id.profile:
                Toast.makeText(getApplicationContext(), "3rd selected", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.attendance_frame, new Profile_Details()).commit();
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


}
//-------//using fragment creates the scrolling problem,Hence the the below code is not used//-------
/*public class Attendance_Details extends Fragment {
    Button save,cancel;
    Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_attendance__details,container,false);
        save = (Button)v.findViewById(R.id.btn_save);
        cancel = (Button)v.findViewById(R.id.btn_cancel);
        save.setVisibility(View.GONE);
        cancel.setVisibility(View.GONE);

        String[] monthNames = new String[]{"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        //get current month and year
        Calendar _calendar = Calendar.getInstance(Locale.getDefault());

        int  m_Month = _calendar.get(Calendar.MONTH) + 1;
        int mYear = _calendar.get(Calendar.YEAR);
        int  mDate = _calendar.get(Calendar.DATE);

        GridView calendarView = (GridView)v.findViewById(R.id.calendar);
        GridCalenderAdapter adapter = new GridCalenderAdapter(getContext(),m_Month, mYear, save, cancel);
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);

        return v;
    }*/


