package com.fundoohr.view.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fundoohr.adapter.GridCalenderAdapter;
import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.callback.AttendenceArrayInterface;
import com.fundoohr.callback.DayAttendanceInterface;
import com.fundoohr.model.AttendenceModel;
import com.fundoohr.model.UpdatePersonalModel;
import com.fundoohr.utility.MonthYearPickerDialog;
import com.fundoohr.utility.ProgressUtil;
import com.fundoohr.viewmodel.AttendenceViewModel;
import com.loopj.android.http.RequestParams;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

//import android.support.app.FragmentTransaction;
//import com.firebase.client.Firebase;

/**
 * * Purpose:
 * It Is The View Of MVVM Design Pattern.
 * It Is The UI Class Which Hold The UI Elements.
 * It Listens To Action Performed In UI class.
 * It Implements And The Observer Pattern To Listen Changes In The View model.
 * It Holds The View model To Update Its State Of The UI.
 * It is The Activity Which Need To Be Included In Manifest.xml File.
 **/

public class AttendanceDetailsActivity extends AppCompatActivity implements AttendenceArrayInterface {
    static String mEngeerId;
    private String mTimeStamp,mDaySelected="";
    private static String TAG ="AttendanceDetailsActivity";
    private final String[] months = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    int mGetmonth, mGetyear, mMonth, mYear,mDate;
    private DatePickerDialog.OnDateSetListener mPick;
    private Button  mDown_arrow;//mSave, mCancel,
    private TextView inText, outText, mTextViewOkReason;
    private EditText mEditTextReason;
    private TextView mTextView;
    private String mStrInText="-",mStrOutText="-",mStrReason="-",mAttendanceStatus="-";
    private GridView mCalendarView;
    boolean mMarkedStatus=true;
    private Context mContext;
    private long timestamp;
    private TextView gridcell;
    private ImageView textMark;
    private String attendenceURL;
    private String tokenValue;
    private ProgressUtil mProgress;
    private ArrayList<AttendenceModel> attendenceModels;
    private EngineerProfileActivity engineerCollapse;
    private TextView mEditTextTitleReason,customTime;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance__details);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        mTextView = (TextView) findViewById(R.id.datedisplay);
        mDown_arrow = (Button) findViewById(R.id.date);
        mCalendarView = (GridView) findViewById(R.id.calendar);
       /* mSave = (Button) findViewById(R.id.btn_save);
        mCancel = (Button) findViewById(R.id.btn_cancel);
        mSave.setVisibility(View.GONE);
        mCancel.setVisibility(View.GONE);*/
        SharedPreferences preferences = this.getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        tokenValue = preferences.getString("token", null);
        Log.i(TAG, "callPersonal: " + tokenValue);
        mProgress=new ProgressUtil(this);
        mEngeerId = getIntent().getExtras().getString("engineerId");
        // mCalendarView.setEnabled(false);


        Log.i(TAG, "onCreate: " + mEngeerId);
        //  String[] monthNames = getResources().getStringArray(R.array.Month);
        engineerCollapse = new EngineerProfileActivity();
        url=getResources().getString(R.string.url);
        attendenceURL   = getResources().getString(R.string.attendence_url);
        attendenceURL=url+attendenceURL;
        // long timestamp = getIntent().getLongExtra("timeStamp", 0);
        timestamp  = System.currentTimeMillis();

        //get current month and year
        Calendar _calendar = Calendar.getInstance(Locale.getDefault());
        mMonth = _calendar.get(Calendar.MONTH)+1;
        mYear = _calendar.get(Calendar.YEAR);
        mDate = _calendar.get(Calendar.DATE);

        mTextView.setText(months[mMonth-1]+""+","+" "+mYear);

/*
        if (getIntent().getIntExtra("year", 0) != 0) {
            mGetmonth = getIntent().getIntExtra("month", 0);
            mGetyear = getIntent().getIntExtra("year", 0);
            mTextView.setText(monthNames[mGetmonth] + "," + mGetyear);
        } else {
            // set data to textview
            mTextView.setText(monthNames[mMonth] + "," + mYear);
        }*/

        //Sending the required parameters to ViewModel
        AttendenceViewModel attendenceViewModel = new AttendenceViewModel();
        Log.i(TAG, "onCreate: timestamp..."+timestamp);
        mProgress.showProgress("Updating Calendar Please Wait...");
        attendenceViewModel.attendViewModelData(attendenceURL, timestamp, mEngeerId, tokenValue, this);
        Log.i(TAG, "engiId: " + mEngeerId);
        Log.i(TAG, "timeStamp: " + timestamp);
        Log.i(TAG, "token: " + tokenValue);

        //Sending the EngineerId to month Picker
        //  MonthYearPickerDialog month = new MonthYearPickerDialog();
        //   getFragmentManager().beginTransaction().replace(R.id.attendance_frame, month.newInstance(mEngeerId));
        //   Log.i("Attend", "onCreate: " + mEngeerId);


        mDown_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                MonthYearPickerDialog pd = MonthYearPickerDialog.newInstance(mMonth-1,
                        calendar.get(Calendar.DAY_OF_MONTH),mYear);

                pd.setListener(new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

                        mMonth=selectedMonth;
                        mYear=selectedYear;
                        mDate= selectedDay;

                        Calendar _calendar = Calendar.getInstance(Locale.getDefault());
                        int month = _calendar.get(Calendar.MONTH)+1;
                        int year = _calendar.get(Calendar.YEAR);

                        int day, second, minute, hour;
                        long epoch=0;
                        try {
                            GregorianCalendar calendar = new GregorianCalendar();
                            Log.i(TAG, "onClick:dat & year...."+mMonth+"  "+mYear);
                            day = calendar.get(Calendar.DAY_OF_MONTH);
                            second = calendar.get(Calendar.SECOND);
                            minute = calendar.get(Calendar.MINUTE);
                            hour = calendar.get(Calendar.HOUR);


                            epoch = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
                                    .parse(mMonth+"/"+day+"/"+mYear+" "+hour+":"+minute+":"+second)
                                    .getTime();
                            Log.i("Atten..", "onClick: ..Date to timestamp....."+epoch+"  "+mMonth+"  "+mYear);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if(mYear==year)
                        {
                            if(mMonth<=month) {
                                mTextView.setText(months[selectedMonth-1]+""+","+" "+selectedYear);
                                getData(epoch);
                            }else{
                                Log.i(TAG, "onClick: Date Shoulde be less than current time.....");
                                Toast.makeText(AttendanceDetailsActivity.this, "Month Shoulde be less than current...", Toast.LENGTH_SHORT).show();

                            }
                        }
                        else{
                            mTextView.setText(months[selectedMonth-1]+""+","+" "+selectedYear);
                            getData(epoch);
                        }
                    }
                });   pd.show(getFragmentManager(), " MonthYearPickerDialog");

            }
        });

        mCalendarView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                gridcell = (TextView) v.findViewById(R.id.calendar_day_gridcell);
                textMark = (ImageView) v.findViewById(R.id.text_mark);

                mDaySelected = gridcell.getText().toString();
                Log.i(TAG, "onItemClick: " + mDaySelected);
                String Reason = "";
                int size = attendenceModels.size();
                Log.i(TAG, "setReason: " + size);
                int i = 0;
                while (size > 0) {
                    if (attendenceModels.get(i).getDays().equals(mDaySelected)) {
                        Reason = attendenceModels.get(i).getReason();
                        Log.i(TAG, "setReason:  dssss" + Reason);
                    }
                    size--;
                    i++;
                }
                if ((gridcell.getText().toString().equals(mDate+""))) {

                    final String strdateSelected = mDaySelected + " ," + months[mMonth - 1] + "," + mYear;
                    final Dialog dialog = new Dialog(AttendanceDetailsActivity.this);
                    dialog.setContentView(R.layout.custom_dailog_icon);

                    dialog.show();
                    ImageView leave = (ImageView) dialog.findViewById(R.id.leave);
                    ImageView checkMark = (ImageView) dialog.findViewById(R.id.checkmark);
                    ImageView co_leave = (ImageView) dialog.findViewById(R.id.co_leave);

                    // leave Reason
                    final String finalReason = Reason;
                    leave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i(TAG, "onClick: " + strdateSelected);
                            dialog.dismiss();
                            setReason(strdateSelected, finalReason);
                            mAttendanceStatus = "Leave";
                        }
                    });

                    // co-leave Reason
                    co_leave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i(TAG, "onClick: " + strdateSelected);
                            setReason(strdateSelected, finalReason);
                            dialog.dismiss();
                            mAttendanceStatus = "CompLeave";

                        }
                    });
                    // in Time and out Time set
                    checkMark.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();

                            final Dialog inDialog = new Dialog(AttendanceDetailsActivity.this);
                            LinearLayout.LayoutParams inDialogParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT);
                            LayoutInflater inflater1 = (LayoutInflater) AttendanceDetailsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View view = inflater1.inflate(R.layout.custom_intime_outtime, null);
                            inDialog.setContentView(view, inDialogParams);
                            customTime = (TextView) inDialog.findViewById(R.id.custom_time);
                            inText = (TextView) inDialog.findViewById(R.id.time_in);
                            outText = (TextView) inDialog.findViewById(R.id.time_out);
                            customTime.setText(strdateSelected);
                            //in time set
                            inText.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    TimePickerDialog timePicker = new TimePickerDialog(AttendanceDetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                        @Override
                                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                            String AM_PM;
                                            if (hourOfDay < 12) {
                                                AM_PM = "AM";
                                            } else {
                                                AM_PM = "PM";
                                            }
                                            inText.setText("" + hourOfDay + ":" + minute + "" + AM_PM);

                                        }
                                    }, 12, 00, true);
                                    timePicker.show();
                                    // mStrInText=inText.getText().toString();

                                }
                            });

                            //out time set
                            outText.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    TimePickerDialog timePicker = new TimePickerDialog(AttendanceDetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                        @Override
                                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                            String AM_PM;
                                            if (hourOfDay < 12) {
                                                AM_PM = "AM";
                                            } else {
                                                AM_PM = "PM";
                                            }
                                            outText.setText("" + hourOfDay + ":" + minute + "" + AM_PM);

                                        }
                                    }, 12, 00, true);
                                    timePicker.show();
                                    //mStrOutText=outText.getText().toString();
                                }
                            });
                            TextView txt_ok = (TextView) inDialog.findViewById(R.id.txt_ok);
                            inDialog.show();

                            txt_ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                        if (!(inText.getText().toString().equals("00.00AM")) && !(outText.getText().toString().equals("00.00PM"))) {
                                            if((inText.getText().toString().contains("AM")&&outText.getText().toString().contains("AM"))||(inText.getText().toString().contains("PM")&&outText.getText().toString().contains("PM"))) {
                                                Toast.makeText(AttendanceDetailsActivity.this, "Please Select Valid Time....", Toast.LENGTH_SHORT).show();
                                            }
                                            else{

                                                inDialog.dismiss();
                                                mStrInText = inText.getText().toString();
                                                mStrOutText = outText.getText().toString();
                                                saveData();
                                            }
                                            Log.i(TAG, "onClick: inTime :" + mStrInText + " OutText :" + mStrOutText);
                                        } else {
                                            Toast.makeText(AttendanceDetailsActivity.this, "Select InTime And OutTime...", Toast.LENGTH_SHORT).show();
                                        }

                                }
                            });
                            mAttendanceStatus = "Present";
                        }
                    });
                }
                // Toast.makeText(getApplicationContext(), "" + position,Toast.LENGTH_SHORT).show();
            }
        });
        /*mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSave.setVisibility(v.INVISIBLE);
                mCancel.setVisibility(v.INVISIBLE);
            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSave.setVisibility(v.INVISIBLE);
                mCancel.setVisibility(v.INVISIBLE);
            }
        });*/

    }

    public  void saveData(){
        long epoch = 0;
        GregorianCalendar calendar = new GregorianCalendar();
        Log.i(TAG, "onClick:dat & year...."+mMonth+"  "+mYear);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int second = calendar.get(Calendar.SECOND);
        int minute = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR);
        int yr= calendar.get(Calendar.YEAR);
        int mnth=calendar.get(Calendar.MONTH)+1;

        try {
            epoch = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
                    .parse(mMonth + "/" + mDaySelected + "/" + mYear + " " + hour + ":" + minute + ":" + second)
                    .getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        RequestParams praParams = new RequestParams();
        praParams.put("engineerId",mEngeerId);
        praParams.put("timeStamp",epoch+"");
        praParams.put("attendanceStatus", mAttendanceStatus);
        praParams.put("markedStatus", mMarkedStatus+"");
        praParams.put("punchIn", mStrInText);
        praParams.put("punchOut", mStrOutText);
        praParams.put("reason", mStrReason);
        url=getResources().getString(R.string.url);
        String hr_url=getResources().getString(R.string.attendence_updatee_url);
        hr_url=url+hr_url;
        Log.i(TAG, "onClick: "+praParams);
        Log.i(TAG, "onClick: saved attendance :day :"+mDaySelected+" timestamp :"+hr_url);
        int dy=Integer.parseInt(mDaySelected);
        Log.i(TAG, "onClick: "+yr+": "+mYear);
        if(mYear<yr) {
            AttendenceViewModel attendanceVmodel =new AttendenceViewModel();
            mProgress.showProgress("Updating Day Attendance ...");
            final long finalEpoch = epoch;

            attendanceVmodel.updateDayAtendance(tokenValue,hr_url,praParams, new DayAttendanceInterface() {
                @Override
                public void dayattendanceArrayList(byte[] bytes) {

                }
                @Override
                public void updateDayAttendance(UpdatePersonalModel updatePersonalModel) {
                    Toast.makeText(getApplicationContext(), ""+updatePersonalModel.getmMessage(), Toast.LENGTH_SHORT).show();
                    mProgress.dismissProgress();
                    Log.i(TAG, "updateDayAttendance:end");
                     getData(finalEpoch);
                }

                @Override
                public void closeDialog() {

                    mProgress.dismissProgress();
                }
            });
        }
        else {
            Log.i(TAG, "onClick: "+mMonth+": "+mnth);
            if(mMonth<mnth)
            {
                AttendenceViewModel attendanceVmodel =new AttendenceViewModel();
                mProgress.showProgress("Updating Day Attendance ...");
                final long finalEpoch = epoch;

                attendanceVmodel.updateDayAtendance(tokenValue,hr_url,praParams, new DayAttendanceInterface() {
                    @Override
                    public void dayattendanceArrayList(byte[] bytes) {

                    }
                    @Override
                    public void updateDayAttendance(UpdatePersonalModel updatePersonalModel) {
                        Toast.makeText(getApplicationContext(), ""+updatePersonalModel.getmMessage(), Toast.LENGTH_SHORT).show();
                        mProgress.dismissProgress();
                        Log.i(TAG, "updateDayAttendance:end");
                          getData(finalEpoch);
                    }

                    @Override
                    public void closeDialog() {
                        mProgress.dismissProgress();
                    }
                });

            }
            else{
                Log.i(TAG, "onClick: "+dy+": "+day);
                if(mMonth==mnth && dy<=day)
                {
                    AttendenceViewModel attendanceVmodel =new AttendenceViewModel();
                    mProgress.showProgress("Updating Day Attendance ...");
                    final long finalEpoch = epoch;

                    attendanceVmodel.updateDayAtendance(tokenValue,hr_url,praParams, new DayAttendanceInterface() {
                        @Override
                        public void dayattendanceArrayList(byte[] bytes) {

                        }
                        @Override
                        public void updateDayAttendance(UpdatePersonalModel updatePersonalModel) {
                            Toast.makeText(getApplicationContext(), ""+updatePersonalModel.getmMessage(), Toast.LENGTH_SHORT).show();
                            mProgress.dismissProgress();
                            Log.i(TAG, "updateDayAttendance:end");
                             getData(finalEpoch);
                        }

                        @Override
                        public void closeDialog() {
                            mProgress.dismissProgress();
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), " Greter Tha current Date ...", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    //get data after select month and year
    public void  getData(long timestamp1){
        long timestamps=timestamp1;
        AttendenceViewModel attendenceViewModel = new AttendenceViewModel();
        Log.i(TAG, "getData: timestamp13.."+timestamps);
        mProgress.showProgress("Updating Calendar Please Wait...");
        attendenceViewModel.attendViewModelData(attendenceURL, timestamps, mEngeerId, tokenValue, this);
    }
    //Getting back the data from View Model were the data is obtained from Controller
    @Override
    public void getAttendArrayData(ArrayList<AttendenceModel> attendenceModels) {
        this.attendenceModels = attendenceModels;
        String []days;
        Log.i(TAG, "getAttendArrayData: hellow " + attendenceModels.size());
        days=new String[attendenceModels.size()];
        for (int i = 0; i < attendenceModels.size(); i++) {
            days[i]=attendenceModels.get(i).getDays();
            Log.i(TAG, "getAttendArrayData: days " + attendenceModels.get(i).getDays());

        }
        //call to Grid layout Display
        if (getIntent().getIntExtra("year", 0) != 0) {
            mGetmonth = getIntent().getIntExtra("month", 0);
            mGetyear = getIntent().getIntExtra("year", 0);
            GridCalenderAdapter adapter = new GridCalenderAdapter(AttendanceDetailsActivity.this, attendenceModels,
                    mMonth , mYear,days,mEngeerId);

            adapter.notifyDataSetChanged();
            mCalendarView.setAdapter(adapter);
        } else {
            // set data to textview
            GridCalenderAdapter adapter = new GridCalenderAdapter(AttendanceDetailsActivity.this, attendenceModels,
                    mMonth , mYear,days,mEngeerId);
            adapter.notifyDataSetChanged();
            mCalendarView.setAdapter(adapter);
        }
        mProgress.dismissProgress();
    }

    @Override
    public void onFailureGetData(UpdatePersonalModel updatePersonalModel) {
        mProgress.dismissProgress();
        Toast.makeText(mContext, ""+updatePersonalModel.getmMessage().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeDialog() {
        mProgress.dismissProgress();
    }

    private void setReason(String date,String reason) {
        Log.i(TAG, "setReason: "+date+"  "+reason);
        final Dialog reasonDialog = new Dialog(AttendanceDetailsActivity.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        LayoutInflater inflaterReason = (LayoutInflater)AttendanceDetailsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View reasonView = inflaterReason.inflate(R.layout.custom_dailog_reason, null);
        mEditTextReason =(EditText) reasonView.findViewById(R.id.edit_reason);
        mEditTextTitleReason =(TextView) reasonView.findViewById(R.id.txt_date_reason);

        mEditTextTitleReason.setText(date);
        mEditTextReason.setText("  "+reason);
        mTextViewOkReason =(TextView)reasonView.findViewById(R.id.dialogtextreason);
        reasonDialog.setContentView(reasonView, params);
        reasonDialog.show();
        mTextViewOkReason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mStrReason=mEditTextReason.getText().toString();
                Log.i(TAG, "onClick:  set reason :"+mStrReason);
                reasonDialog.dismiss();
                saveData();
            }
        });
    }


}