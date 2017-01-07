package com.example.bridgeit.fundoo.adapter;

/**
 * Created by bridgeit on 26/12/16.
 */

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.bridgeit.fundoo.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by bridgeit on 20/12/16.
 */

public class GridCalenderAdapter extends BaseAdapter {
    private final Context _context;
    private final List<String> list;
    private final String[] months = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, 29};
    private TextView inText, outText;
    private Button save, cancel;
    private int daysInMonth;
    private TextView gridcell;
    private ImageView textMark;
    private int mYear;
    private int currentDayOfMonth;

    public GridCalenderAdapter(Context context, int month, int year, Button save, Button cancel) {
        super();
        this.save = save;
        this.cancel = cancel;
        this._context = context;
        this.mYear = year;
        this.list = new ArrayList<String>();
        // Print Month
        printMonth(month, year);
    }

    private void printMonth(int mm, int yy) {
        int trailingSpaces = 0;
        int currentMonth = mm - 1;
        int prevMonth = 0;

        daysInMonth = getNumberOfDaysOfMonth(currentMonth);


        // Gregorian Calendar : MINUS 1, set to FIRST OF MONTH
        GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);

        if (currentMonth == 11) {
            prevMonth = currentMonth - 1;
        } else if (currentMonth == 0) {
            prevMonth = 11;
        } else {
            prevMonth = currentMonth - 1;
        }

        int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
        trailingSpaces = currentWeekDay;

        if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 1) {
            ++daysInMonth;
        }

        // Trailing Month days
        //25-WHITE-December-2016
        for (int i = 0; i < trailingSpaces; i++) {
            //list.add(String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i) + "-GREY" + "-"+ getMonthAsString(prevMonth) + "-" + prevYear);
            list.add(" " + "-" + " " + "-" + " " + "-" + " ");
        }

        // Current Month Days
        for (int i = 1; i <= daysInMonth; i++) {
            if (i == getCurrentDayOfMonth())
                list.add(String.valueOf(i) + "-BLUE" + "-" + getMonthAsString(currentMonth) + "-" + yy);

            else
                list.add(String.valueOf(i) + "-WHITE" + "-" + getMonthAsString(currentMonth) + "-" + yy);
            // Log.i("current", list.toString());
        }

        // Leading Month days
        for (int i = 0; i < list.size() % 7; i++) {
            //list.add(String.valueOf(i + 1) + "-GREY" + "-" + getMonthAsString(nextMonth) + "-" + nextYear);
            list.add(" " + "-" + " " + "-" + " " + "-" + " ");
        }
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.custom_grid_calender, parent, false);
        }

        // Get a reference to the Day gridcell
        gridcell = (TextView) row.findViewById(R.id.calendar_day_gridcell);
        textMark = (ImageView) row.findViewById(R.id.text_mark);
        //gridcell.setOnClickListener(this);

        // ACCOUNT FOR SPACING
        // 25-WHITE-December-2016
        String[] day_color = list.get(position).split("-");
        String theday = day_color[0];
        String themonth = day_color[2];
        String theyear = day_color[3];

        // Set the Day GridCell
        gridcell.setText(theday);
        if (!theday.equals(" ")) {
            textMark.setImageResource(R.drawable.checkmark);
        }
        gridcell.setTag(theday + "-" + themonth + "-" + theyear);

        textMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(_context);
                // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                // dialog.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.string.app_name );
                LinearLayout.LayoutParams dialogParams = new LinearLayout.LayoutParams(350, 120);
                final LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dislogView = inflater.inflate(R.layout.custom_dailog_icon, null);

                dialog.setContentView(dislogView, dialogParams);
                //popup symbols
                /*final Dialog dialog = new Dialog(_context);
                dialog.setContentView(R.layout.custom_dialog_icon);*/
                dialog.show();

                ImageView leave = (ImageView) dialog.findViewById(R.id.leave);
                ImageView checkMark = (ImageView) dialog.findViewById(R.id.checkmark);
                final ImageView co_leave = (ImageView) dialog.findViewById(R.id.co_leave);

                // leave Reason
                leave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        setReason();
                    }
                });

                // co-leave Reason
                co_leave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        setReason();
                    }
                });
                // in Time and out Time set
                checkMark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        save.setVisibility(v.VISIBLE);
                        cancel.setVisibility(v.VISIBLE);
                        final Dialog inDialog = new Dialog(_context);
                        LinearLayout.LayoutParams inDialogParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT);
                        LayoutInflater inflater1 = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View view = inflater1.inflate(R.layout.custom_intime_outtime, null);
                        inDialog.setContentView(view, inDialogParams);
                        inText = (TextView) inDialog.findViewById(R.id.time_in);
                        outText = (TextView) inDialog.findViewById(R.id.time_out);

                        //in time set
                        inText.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                TimePickerDialog timePicker = new TimePickerDialog(_context, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        String AM_PM;
                                        if (hourOfDay < 12) {
                                            AM_PM = "AM";
                                        } else {
                                            AM_PM = "PM";
                                        }
                                        inText.setText("" + hourOfDay + ":" + minute);

                                    }
                                }, 12, 00, true);
                                timePicker.show();


                            }
                        });

                        //out time set
                        outText.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                TimePickerDialog timePicker = new TimePickerDialog(_context, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        String AM_PM;
                                        if (hourOfDay < 12) {
                                            AM_PM = "AM";
                                        } else {
                                            AM_PM = "PM";
                                        }
                                        outText.setText("" + hourOfDay + ":" + minute);

                                    }
                                }, 12, 00, true);
                                timePicker.show();

                            }
                        });
                        TextView txt_ok = (TextView) inDialog.findViewById(R.id.txt_ok);
                        inDialog.show();



                        /*AlertDialog.Builder builder = new AlertDialog.Builder(_context);

                        builder.setTitle("22 November 2016");
                        LinearLayout layout = new LinearLayout(_context);
                        TextView intime = new TextView(_context);
                        TextView outTime = new TextView(_context);
                        Button ok = new Button(_context);

                        layout.setOrientation(LinearLayout.VERTICAL);
                        intime.setText("IN Time");
                        intime.setTextSize(20.0f);

                        outTime.setText("out Time");
                        outTime.setTextSize(20.0f);

                        ok.setText("Ok");
                        layout.addView(intime);
                        layout.addView(outTime);
                        layout.addView(ok);

                        builder.setView(layout);


                        builder.show();
*/
                    }
                });
            }
        });
        return row;
    }

    private void setReason() {
        Dialog reasonDialog = new Dialog(_context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(950, 550);
        LayoutInflater inflaterReason = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View reasonView = inflaterReason.inflate(R.layout.custom_dailog_reason, null);
        reasonDialog.setContentView(reasonView, params);
        reasonDialog.show();
    }

    private String getMonthAsString(int i) {
        return months[i];
    }

    private int getNumberOfDaysOfMonth(int i) {
        if (i == 1) {

            int a = leapYear(mYear);
            if (a == 29) {
                Log.i("if loop", "" + a);
                return daysOfMonth[12];
            } else
                return daysOfMonth[i];

        } else
            return daysOfMonth[i];
    }

    public int getCurrentDayOfMonth() {
        return currentDayOfMonth;
    }

    //leap year
    public int leapYear(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            System.out.println(year + " is a leap year");
            //   Log.i("leap year",""+daysOfMonth[12]);
            return daysOfMonth[12];

        } else
            System.out.println(year + " is not a leap year");
        // Log.i("not a leap year",""+daysOfMonth[1]);
        return daysOfMonth[1];
    }
}
