package com.fundoohr.bridgeit.fundoohr.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.bridgeit.fundoohr.callback.BankingDetailArrayInterface;
import com.fundoohr.bridgeit.fundoohr.model.BankingDetailsModel;
import com.fundoohr.bridgeit.fundoohr.viewmodel.BankingViewModel;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

public class BankingDetails extends Fragment implements BankingDetailArrayInterface {
    String mBank_url;
    ImageButton mImageButton;
    SharedPreferences mSharedPreferences;
    EditText mBank_AccNo, mBank_Bankname, mBank_IFSC, mBank_Pan, mBank_Pay, mBank_Reason;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_banking_details, container, false);
        mImageButton = (ImageButton) view.findViewById(R.id.bank_edit);
        mBank_AccNo = (EditText) view.findViewById(R.id.bank_AccNo);
        mBank_Bankname = (EditText) view.findViewById(R.id.bank_bankname);
        mBank_IFSC = (EditText) view.findViewById(R.id.bank_ifsc);
        mBank_Pan = (EditText) view.findViewById(R.id.bank_pan);
        mBank_Pay = (EditText) view.findViewById(R.id.bank_pay_salary);
        mBank_Reason = (EditText) view.findViewById(R.id.bank_reason);

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
        mSharedPreferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String token = mSharedPreferences.getString("token", null);
        String engineerId = getArguments().getString("id");
        mBank_url=getResources().getString(R.string.Bank_url);
        RequestParams requestParams =new RequestParams();
        requestParams.put("token",token);
        requestParams.put("engineerId",engineerId);

        BankingViewModel bankViewModel = new BankingViewModel();
        bankViewModel.bankDataList(mBank_url,requestParams,this);
        return view;
    }

    @Override
    public void bankArrayData(ArrayList<BankingDetailsModel> bankingDModel) {
        BankingDetailsModel bankingDetailsModel = bankingDModel.get(0);
        Log.i("BankView", "bankArrayData: "+bankingDetailsModel.getAccountNumber());
        mBank_AccNo.setText(bankingDetailsModel.getAccountNumber());
        mBank_Bankname.setText(bankingDetailsModel.getBankName());
        mBank_IFSC.setText(bankingDetailsModel.getIfscCode());
        mBank_Pan.setText(bankingDetailsModel.getPan());
        mBank_Reason.setText(bankingDetailsModel.getReason());
        mBank_Pay.setText(bankingDetailsModel.getPaySalay());
    }
}
