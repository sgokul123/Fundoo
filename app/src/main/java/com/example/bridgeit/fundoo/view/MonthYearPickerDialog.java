package com.example.bridgeit.fundoo.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bridgeit.fundoo.R;

import java.util.Calendar;

/**
 * Created by bridgeit on 30/12/16.
 */
public class MonthYearPickerDialog extends DialogFragment {
    TextView datetextview;
    private static final int MAX_YEAR = 2099;
    private DatePickerDialog.OnDateSetListener listener;
    private Context mContext;

    public MonthYearPickerDialog(){
    }

    public void setListener(Context context,DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
        mContext = context;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        Calendar cal = Calendar.getInstance();

        View dialog = inflater.inflate(R.layout.date_picker_dialog, null);
        final NumberPicker monthPicker = (NumberPicker) dialog.findViewById(R.id.picker_month);
        final NumberPicker yearPicker = (NumberPicker) dialog.findViewById(R.id.picker_year);

        String[] monthNames = new String[] {"January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November", "December"};
        //monthPicker.setMinValue(1);
        /// monthPicker.setMaxValue(12);
        monthPicker.setDisplayedValues(monthNames);
        monthPicker.setMaxValue(monthNames.length-1);
        monthPicker.setValue(cal.get(Calendar.MONTH) + 1);

        int year = cal.get(Calendar.YEAR);
        yearPicker.setMinValue(year);
        yearPicker.setMaxValue(MAX_YEAR);
        yearPicker.setValue(year);

        builder.setView(dialog)
                // Add action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(mContext, Attendance_Details.class);
                        intent.putExtra("month", monthPicker.getValue());
                        intent.putExtra("year", yearPicker.getValue());
                        startActivity(intent);

                        /*
                        CalendarFragment p = new CalendarFragment();
                        Fragment fragment = p.newInstance(obj);
                        FragmentManager fragmngr = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fragmngr.beginTransaction();
                        transaction.replace(R.id.recyclerviewdata, fragment).addToBackStack(null).commit();*/
                       /* CalendarFragment ldf = new CalendarFragment ();
                        Bundle args = new Bundle();
                        args.putInt("month", monthPicker.getValue());
                        args.putInt("year", yearPicker.getValue());
                        ldf.setArguments(args);
                        getFragmentManager().beginTransaction().add(R.id.frame,ldf).commit();
*/

                        try {
                            listener.onDateSet(null, yearPicker.getValue(), monthPicker.getValue(), 0);
                            Toast.makeText(getActivity(), "hiii" + yearPicker.getValue(), Toast.LENGTH_SHORT).show();
                            Log.i("hello", "jjjj");
                            Log.i("hello", "" + yearPicker.getValue());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MonthYearPickerDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


}