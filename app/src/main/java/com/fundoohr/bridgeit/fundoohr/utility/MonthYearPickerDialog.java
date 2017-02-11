package com.fundoohr.bridgeit.fundoohr.utility;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.NumberPicker;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.bridgeit.fundoohr.adapter.GridCalenderAdapter;
import com.fundoohr.bridgeit.fundoohr.callback.AttendenceArrayInterface;
import com.fundoohr.bridgeit.fundoohr.model.AttendenceModel;
import com.fundoohr.bridgeit.fundoohr.view.activity.AttendanceDetailsActivity;
import com.fundoohr.bridgeit.fundoohr.viewmodel.AttendenceViewModel;
import com.loopj.android.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by bridgeit on 30/12/16.
 */
public class MonthYearPickerDialog extends DialogFragment implements AttendenceArrayInterface{
    SharedPreferences mSharedPreferences;
    String ValueId;
    AttendanceDetailsActivity attendanceDetailsActivity;
    // private static final int MAX_YEAR = 2099;
    private static final int MIN_YEAR = 2016;
    private DatePickerDialog.OnDateSetListener listener;
    private Context mContext;
    private GridView gridView;

    public MonthYearPickerDialog(){
    }

    public void setListener(Context context, DatePickerDialog.OnDateSetListener listener, GridView calendarView, String mEngeerId) {
        this.listener = listener;
        this.ValueId=mEngeerId;
        this.mContext = context;
        this.gridView = calendarView;
    }


    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        Calendar cal = Calendar.getInstance();

        View dialog = inflater.inflate(R.layout.date_picker_dialog, null);
        final NumberPicker monthPicker = (NumberPicker) dialog.findViewById(R.id.picker_month);
        final NumberPicker yearPicker = (NumberPicker) dialog.findViewById(R.id.picker_year);

        String[] monthNames;

        //Getting the Months from the String xml file
        monthNames = getResources().getStringArray(R.array.Month);
        monthPicker.setDisplayedValues(monthNames);
        monthPicker.setMaxValue(monthNames.length-1);
      //  monthPicker.setValue(cal.get(Calendar.MONTH) + 1);
        // String value =getArguments().getString("ID");
        //Log.i("ValueId", "onCreateDialog: " +value);
        int year = cal.get(Calendar.YEAR);
        yearPicker.setMinValue(MIN_YEAR);
        yearPicker.setMaxValue(year);
       // yearPicker.setValue(year);

        builder.setView(dialog)
                // Add action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Creating the TimeStamp by converting the day picked on the MonthPicker
                        int day, second, minute, hour;
                        long epoch=0;
                        GregorianCalendar calendar = new GregorianCalendar();

                        day = calendar.get(Calendar.DAY_OF_MONTH);
                        second = calendar.get(Calendar.SECOND);
                        minute = calendar.get(Calendar.MINUTE);
                        hour = calendar.get(Calendar.HOUR);

                       // String ValueId="40001EI";

                        try {
                            epoch = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
                                    .parse(monthPicker.getValue()+"/"+day+"/"+yearPicker.getValue()+" " +
                                            ""+hour+":"+minute+":"+second)
                                    .getTime();
                            Log.i("ghgj", "onClick: "+ValueId);
                            Log.i("Atten..", "onClick: ..epochfgfhbgnjhnvbn....."+epoch);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // Onclick of the ok Button in the MonthPickker dailog box
                        //the call is made to the REST Api to get the data ,
                        //and pass to the Attendence detials
                        //using the shared preference to get the token
                        mSharedPreferences = getActivity().getSharedPreferences("RECORDS",Context.MODE_PRIVATE);
                        String token= mSharedPreferences.getString("token",null);
                        String attendence_url=getResources().getString(R.string.attendence_url);

                        RequestParams param = new RequestParams();
                        param.put("",token);
                        // Getting the viewmodel class obeject
                        AttendenceViewModel attendenceViewModel = new AttendenceViewModel();
                        attendenceViewModel.attendViewModelData(attendence_url,epoch,ValueId,token,
                                attendanceDetailsActivity); /*{
                            @Override
                            public void getAttendArrayData(ArrayList<AttendenceModel> attendenceModels) {

                            }
                        });*/
                       /* Intent intent = new Intent(mContext, AttendanceDetailsActivity.class);
                        intent.putExtra("timeStamp", epoch);
                        intent.putExtra("month", monthPicker.getValue());
                        intent.putExtra("year", yearPicker.getValue());
                        startActivity(intent)*/;
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MonthYearPickerDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

  /*  public android.app.Fragment newInstance(String engineerId) {
        MonthYearPickerDialog month = new MonthYearPickerDialog();
        Bundle bundle = new Bundle();
        bundle.putString("ID", engineerId);
        month.setArguments(bundle);
        Log.i("newInsta", "newInstance: "+bundle);
        return month;
    }*/
    @Override
    public void getAttendArrayData(ArrayList<AttendenceModel> attendenceModels) {
        ArrayList<AttendenceModel> modelArrayList = attendenceModels;
        /*GridCalenderAdapter adapter = new GridCalenderAdapter(getActivity(),modelArrayList, getmonth + 1, getyear);
        adapter.notifyDataSetChanged();
        gridView.setAdapter(adapter);*/
    }
}