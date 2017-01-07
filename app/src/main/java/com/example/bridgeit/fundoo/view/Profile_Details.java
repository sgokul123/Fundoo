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
import android.widget.RelativeLayout;

import com.example.bridgeit.fundoo.R;

public class Profile_Details extends Fragment {
    ImageButton mImageButton;
   // RelativeLayout relativeLayout;
    EditText mProfile_diploma, mProfile_degree, mProfile_disciplane, mProfile_yop, mProfile_aggregate,mProfile_finalyear,
            mProfile_training_institute, mProfile_training_duration, mProfile_training;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_profile__details,container,false);
       // relativeLayout= (RelativeLayout) view.findViewById(R.id.profile_relative);
        mImageButton= (ImageButton) view.findViewById(R.id.profile_edit);
        mProfile_diploma= (EditText) view.findViewById(R.id.profile_diploma);
        mProfile_degree= (EditText) view.findViewById(R.id.profile_degree);
        mProfile_disciplane= (EditText) view.findViewById(R.id.profile_disciplane);
        mProfile_yop= (EditText) view.findViewById(R.id.profile_yop);
        mProfile_aggregate= (EditText) view.findViewById(R.id.profile_aggregate);
        mProfile_finalyear= (EditText) view.findViewById(R.id.profile_final_year);
        mProfile_training_institute= (EditText) view.findViewById(R.id.profile_training_institute);
        mProfile_training_duration= (EditText) view.findViewById(R.id.profile_training_duration);
        mProfile_training= (EditText) view.findViewById(R.id.profile_training);

        mProfile_diploma.setFocusable(false);
        mProfile_training.setFocusable(false);
        mProfile_training_institute.setFocusable(false);
        mProfile_training_duration.setFocusable(false);
        mProfile_degree.setFocusable(false);
        mProfile_aggregate.setFocusable(false);
        mProfile_finalyear.setFocusable(false);
        mProfile_yop.setFocusable(false);
        mProfile_disciplane.setFocusable(false);
        /*relativeLayout.setFocusable(false);*/
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProfile_diploma.setFocusableInTouchMode(true);
                mProfile_degree.setFocusableInTouchMode(true);
                mProfile_disciplane.setFocusableInTouchMode(true);
                mProfile_yop.setFocusableInTouchMode(true);
                mProfile_aggregate.setFocusableInTouchMode(true);
                mProfile_finalyear.setFocusableInTouchMode(true);
                mProfile_training_institute.setFocusableInTouchMode(true);
                mProfile_training_duration.setFocusableInTouchMode(true);
                mProfile_training.setFocusableInTouchMode(true);

            }
        });
        return view;
    }
}
