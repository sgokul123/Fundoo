package com.example.bridgeit.fundoo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.bridgeit.fundoo.R;

public class BankingDetails extends Fragment{
    ImageButton mImageButton;
    EditText mBank_AccNo,mBank_Bankname,mBank_IFSC,mBank_Pan,mBank_Pay,mBank_Reason;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_banking_details,container,false);
        mImageButton= (ImageButton) view.findViewById(R.id.bank_edit);
        mBank_AccNo= (EditText) view.findViewById(R.id.bank_AccNo);
        mBank_Bankname= (EditText) view.findViewById(R.id.bank_bankname);
        mBank_IFSC= (EditText) view.findViewById(R.id.bank_ifsc);
        mBank_Pan= (EditText) view.findViewById(R.id.bank_pan);
        mBank_Pay= (EditText) view.findViewById(R.id.bank_pay_salary);
        mBank_Reason= (EditText) view.findViewById(R.id.bank_reason);

        mBank_AccNo.setFocusable(false);
        mBank_Bankname.setFocusable(false);
        mBank_IFSC.setFocusable(false);
        mBank_Pan.setFocusable(false);
        mBank_Pay.setFocusable(false);
        mBank_Reason.setFocusable(false);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBank_AccNo.setFocusableInTouchMode(true);
                mBank_Bankname.setFocusableInTouchMode(true);
                mBank_IFSC.setFocusableInTouchMode(true);
                mBank_Pan.setFocusableInTouchMode(true);
                mBank_Pay.setFocusableInTouchMode(true);
                mBank_Reason.setFocusableInTouchMode(true);
            }
        });
        return view;
    }
}
