package com.fundoohr.adapter;

/**
 * Created by bridgeit on 26/12/16.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.model.AttendenceModel;
import com.fundoohr.utility.Debug;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by bridgeit on 20/12/16.
 */

public class GridCalenderAdapter extends BaseAdapter {
    private static String TAG ="GridCalenderAdapter";
    private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, 29};
    ArrayList<AttendenceModel> attendenceModels;
    private Context mContext;
    private List<String> list;
    private final String[] months = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
  ///  private String[] months;
    private TextView inText, outText, mTextViewOkReason;
    private EditText mEditTextReason;
    private int daysInMonth;
    private String mStrInText="-",mStrOutText="-",mStrReason="-",mAttendanceStatus="-",mMarkedStatus="true";
    private String mEngId,mTimeStamp;
    private TextView gridcell;
    private ImageView textMark;
    private int mYear, mMonth;
    private int currentDayOfMonth;
    private RelativeLayout relativeRootCalenterItem;
    private  int size,index=0;
    private  int curdatetovisible;
    private List<String> daylist;
    public GridCalenderAdapter() {
    }

    public GridCalenderAdapter(Context context, ArrayList<AttendenceModel> mAttendenceModels, int month, int year,String[] days,String engId) {
        super();
        this.mContext = context;
        this.mYear = year;
        this.mMonth = month;
        this.mEngId=engId;
        daylist = Arrays.asList(days);
        this.attendenceModels = mAttendenceModels;
        Debug.showLog(TAG ,"   data first size : "+attendenceModels.size());
        this.list = new ArrayList<String>();
        // Print Month
        size=attendenceModels.size();
        printMonth(mMonth, mYear);
    }

    public void printMonth(int mm, int yy) {
        int trailingSpaces = 0;
        int currentMonth = mm - 1;
        int prevMonth = 0;
       // months = mContext.getResources().getStringArray(R.array.Month);
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.custom_grid_calender, parent, false);
        }
        Calendar _calendar = Calendar.getInstance(Locale.getDefault());
        curdatetovisible = _calendar.get(Calendar.DATE);
        //Data from view class to adtapter
      //  AttendenceModel model = attendenceModels.get(position);
        // Get a reference to the Day gridcell
        relativeRootCalenterItem = (RelativeLayout) row.findViewById(R.id.relativeRootCalenterItem);
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
        Debug.showLog(TAG ,"  date    print: "+day_color[0]);

       /* if (!theday.equals(" ") && model.getAttendenceStatus().equalsIgnoreCase("Present")) {
            textMark.setImageResource(R.drawable.checkmark);
        } else if (!theday.equals(" ") && model.getAttendenceStatus().equalsIgnoreCase("Leave")) {
            textMark.setImageResource(R.drawable.leave);
        } else {
            textMark.setImageResource(R.drawable.coleave);
        }*/

        gridcell.setTag(theday + "-" + themonth + "-" + theyear);


      // Debug.showLog(TAG,"view set "+attendenceModels);

       /* if(position > mMonth){
            gridcell.setText("-");
        }
        else{

        }*/

        if (gridcell.getText().toString().equals(curdatetovisible+"")){
            gridcell.setBackgroundResource(R.color.back);
            textMark.setBackgroundResource(R.color.back);
            relativeRootCalenterItem.setBackgroundResource(R.color.back);
        }
        else{
            relativeRootCalenterItem.setEnabled(false);
        }
        if((gridcell.getText().toString().equals(" ")) ){
        }
        else{
               if(size==0){
                   textMark.setImageResource(R.drawable.dash);
                   Debug.showLog(TAG ,"  index : "+theday+"-Dash ");
               }
               else{

                   try {

                        Debug.showLog(TAG, "  index : " + theday);
                       if (index < size)
                       {
                           if (daylist.contains("" + theday + ""))
                           {
                               if (gridcell.getText().toString().equals(daylist.get(index)))
                               {
                                   if (attendenceModels.get(index).getAttendenceStatus().equals("Leave")) {
                                               textMark.setImageResource(R.drawable.leave);
                                               Debug.showLog(TAG, "  index : " + theday + "-Leave");
                                   } else if (attendenceModels.get(index).getAttendenceStatus().equals("CompLeave")) {
                                               Debug.showLog(TAG, "  index : " + theday + "-CompLeave");
                                               textMark.setImageResource(R.drawable.coleave);
                                   } else if (attendenceModels.get(index).getAttendenceStatus().equals("Present")) {
                                               Debug.showLog(TAG, "  index : " + theday + "-Present");
                                               textMark.setImageResource(R.drawable.checkmark);
                                   }
                                           index = index + 1;
                               }
                           } else {
                                       textMark.setImageResource(R.drawable.dash);
                                       Debug.showLog(TAG, "  index : " + theday + "Dash");
                           }
                       } else
                       {
                           textMark.setImageResource(R.drawable.dash);
                           Debug.showLog(TAG, "  index : " + theday + "Dash");
                           size = 0;
                       }

                   }catch(ArrayIndexOutOfBoundsException e){
                           Debug.showLog(TAG, "  index  out of bound: ");
                   }

            }

        }

       /* if(attendenceModels!=null && attendenceModels.size()>0 && position < attendenceModels.size() ) {
            if (attendenceModels.get(position).getMarkedStatus().equalsIgnoreCase("true")) {
                relativeRootCalenterItem.setBackground(ContextCompat.getDrawable(mContext, R.drawable.rect));
            } else {
                relativeRootCalenterItem.setBackground(ContextCompat.getDrawable(mContext, R.drawable.boarder_red));
            }
        }
        else{

        }*/


        return row;
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
