package com.fundoohr.bridgeit.fundoohr.view.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.bridgeit.fundoohr.callback.BankingDetailArrayInterface;
import com.fundoohr.bridgeit.fundoohr.model.BankingDetailsModel;
import com.fundoohr.bridgeit.fundoohr.viewmodel.BankingViewModel;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

public class BankingDetails extends Fragment implements BankingDetailArrayInterface {
    ProgressDialog mProgressDialog;
    String mBankurl;
    Button mSave,mCancel;
    ImageButton mImageButton;
    SharedPreferences mSharedPreferences;
    EditText mBankAccNo, mBankBankname, mBankIFSC, mBankPan, mBankPay, mBankReason;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_banking_details, container, false);
        mSave= (Button) view.findViewById(R.id.save);
        mCancel = (Button) view.findViewById(R.id.cancel);
        mImageButton = (ImageButton) view.findViewById(R.id.bank_edit);
        mBankAccNo = (EditText) view.findViewById(R.id.bank_AccNo);
        mBankBankname = (EditText) view.findViewById(R.id.bank_bankname);
        mBankIFSC = (EditText) view.findViewById(R.id.bank_ifsc);
        mBankPan = (EditText) view.findViewById(R.id.bank_pan);
        mBankPay = (EditText) view.findViewById(R.id.bank_pay_salary);
        mBankReason = (EditText) view.findViewById(R.id.bank_reason);

        mBankAccNo.setFocusable(false);
        mBankBankname.setFocusable(false);
        mBankIFSC.setFocusable(false);
        mBankPan.setFocusable(false);
        mBankPay.setFocusable(false);
        mBankReason.setFocusable(false);
        mSave.setVisibility(View.INVISIBLE);
        mCancel.setVisibility(view.INVISIBLE);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBankAccNo.setFocusableInTouchMode(true);
                mBankBankname.setFocusableInTouchMode(true);
                mBankIFSC.setFocusableInTouchMode(true);
                mBankPan.setFocusableInTouchMode(true);
                mBankPay.setFocusableInTouchMode(true);
                mBankReason.setFocusableInTouchMode(true);
                //Button to save and Cancel to Update the Data to the Server
                mSave.setVisibility(View.VISIBLE);
                mCancel.setVisibility(View.VISIBLE);
            }
        });

        mSharedPreferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String token = mSharedPreferences.getString("token", null);
        String engineerId = getArguments().getString("id");
        mBankurl =getResources().getString(R.string.Bank_url);
        RequestParams requestParams =new RequestParams();
        requestParams.put("engineerId",engineerId);

        BankingViewModel bankViewModel = new BankingViewModel();
        bankViewModel.bankDataList(token, mBankurl,requestParams,this);
        mProgressDialog= new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestParams paramData =new RequestParams();
                paramData.put("accountNumber", mBankAccNo.getText().toString());
                paramData.put("bankName", mBankBankname.getText().toString());
                paramData.put("ifscCode", mBankIFSC.getText().toString());
                paramData.put("pan", mBankPan.getText().toString());
                paramData.put("paySalary", mBankPay.getText().toString());
                paramData.put("reason", mBankReason.getText().toString());

            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCancel.setVisibility(View.INVISIBLE);
                mSave.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }

    @Override
    public void bankArrayData(ArrayList<BankingDetailsModel> bankingDModel) {
        mProgressDialog.dismiss();

        BankingDetailsModel bankingDetailsModel = bankingDModel.get(0);
        Log.i("BankView", "bankArrayData: "+bankingDetailsModel.getAccountNumber());
        mBankAccNo.setText(bankingDetailsModel.getAccountNumber());
        mBankBankname.setText(bankingDetailsModel.getBankName());
        mBankIFSC.setText(bankingDetailsModel.getIfscCode());
        mBankPan.setText(bankingDetailsModel.getPan());
        mBankReason.setText(bankingDetailsModel.getReason());
        mBankPay.setText(bankingDetailsModel.getPaySalay());
    }
}
