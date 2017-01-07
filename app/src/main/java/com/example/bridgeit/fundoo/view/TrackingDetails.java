package com.example.bridgeit.fundoo.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.bridgeit.fundoo.R;

public class TrackingDetails extends Fragment {
    ImageButton mEditbutton;
    EditText mEditText,mEditText1,mEditText2,mEditText3,mEditText4,mEditText5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_tracking_details,container,false);
        mEditbutton= (ImageButton) view.findViewById(R.id.track_edit);
        mEditText = (EditText) view.findViewById(R.id.track_stack);
        mEditText1= (EditText) view.findViewById(R.id.track_startdate);
        mEditText2= (EditText) view.findViewById(R.id.track_enddate);
        mEditText3= (EditText) view.findViewById(R.id.track_currweek);
        mEditText4= (EditText) view.findViewById(R.id.track_weekleft);
        mEditText5= (EditText) view.findViewById(R.id.track_week1);

        mEditText.setFocusable(false); //to disable it
        mEditText1.setFocusable(false);
        mEditText2.setFocusable(false);
        mEditText3.setFocusable(false);
        mEditText4.setFocusable(false);
        mEditText5.setFocusable(false);
        mEditbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // mEditText.setEnabled(true); // to enable it
                mEditText.setFocusableInTouchMode(true); //to enable it
                mEditText1.setFocusableInTouchMode(true);
                mEditText2.setFocusableInTouchMode(true);
                mEditText3.setFocusableInTouchMode(true);
                mEditText4.setFocusableInTouchMode(true);
                mEditText5.setFocusableInTouchMode(true);

            }
        });
        return view;
    }



}
