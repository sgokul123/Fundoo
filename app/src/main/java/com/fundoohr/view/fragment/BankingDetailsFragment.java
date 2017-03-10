package com.fundoohr.view.fragment;

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
import android.widget.Toast;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.callback.BankingDetailArrayInterface;
import com.fundoohr.callback.ProfileDetailUpdateInteface;
import com.fundoohr.model.BankingDetailsModel;
import com.fundoohr.model.UpdatePersonalModel;
import com.fundoohr.utility.Constants;
import com.fundoohr.utility.ProgressUtil;
import com.fundoohr.viewmodel.BankingViewModel;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
/**
 * * Purpose:
 * It Is The View Of MVVM Design Pattern.
 * It Is The UI Class Which Hold The UI Elements.
 * It Listens To Action Performed In UI class.
 * It Implements And The Observer Pattern To Listen Changes In The View model.
 * It Holds The View model To Update Its State Of The UI.
 * It is The Activity Which Need To Be Included In Manifest.xml File.
 **/

public class BankingDetailsFragment extends Fragment implements BankingDetailArrayInterface,View.OnClickListener {

    public static final String TAG = "BankingDetailsFragment";

    ProgressDialog mProgressDialog;
    String mBankurl, engineerId, token;
    Button mSave,mCancel;
    ImageButton mImageButton;
    SharedPreferences mSharedPreferences;
    EditText mBankAccNo, mBankBankname, mBankIFSC, mBankPan, mBankPay, mBankReason;
    ProgressUtil prog;
    private String url;


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

        setVisibility(false);
        mSave.setVisibility(View.INVISIBLE);
        mCancel.setVisibility(view.INVISIBLE);

        mImageButton.setOnClickListener(this);


        mSharedPreferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        token= mSharedPreferences.getString("token", null);
        engineerId= getArguments().getString("id");
        url=getResources().getString(R.string.url);
        mBankurl =getResources().getString(R.string.Bank_url);
        mBankurl=url+mBankurl;
        RequestParams requestParams =new RequestParams();
        requestParams.put("engineerId",engineerId);

        BankingViewModel bankViewModel = new BankingViewModel();
        bankViewModel.bankDataList(token, mBankurl,requestParams,this);
        prog = new ProgressUtil(getActivity());
        prog.showProgress("Loading...");
       /* mProgressDialog= new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();*/
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prog.showProgress("Updating Employee Bank Details ...");
                RequestParams paramData =new RequestParams();
                paramData.put("accountNumber", mBankAccNo.getText().toString());
                paramData.put("bankName", mBankBankname.getText().toString());
                paramData.put("ifscCode", mBankIFSC.getText().toString());
                paramData.put("pan", mBankPan.getText().toString());
                paramData.put("paySalary", mBankPay.getText().toString());
                paramData.put("reason", mBankReason.getText().toString());
                paramData.put("engineerId", engineerId);
                String hr_url=getResources().getString(R.string.Bank_update_url);
                hr_url=url+hr_url;
                BankingViewModel bankingViewModel=new BankingViewModel();
                bankingViewModel.updateProfile(token,hr_url,paramData, new ProfileDetailUpdateInteface() {
                    @Override
                    public void callforUpdate(UpdatePersonalModel updatePersonalModel) {
                        prog.dismissProgress();
                        Log.i(TAG, "Status : "+updatePersonalModel.getmStatus());
                        Log.i(TAG, "Message  : "+updatePersonalModel.getmMessage());
                        Toast.makeText(getActivity(), updatePersonalModel.getmMessage()+"...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void closeDilog() {
                        prog.dismissProgress();
                    }
                });
                setVisibility(false);
                mCancel.setVisibility(View.INVISIBLE);
                mSave.setVisibility(View.INVISIBLE);

            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCancel.setVisibility(View.INVISIBLE);
                mSave.setVisibility(View.INVISIBLE);
                setVisibility(false);

                /* mBankAccNo.setFocusableInTouchMode(false);

                mBankBankname.setFocusableInTouchMode(false);
                mBankIFSC.setFocusableInTouchMode(false);
                mBankPan.setFocusableInTouchMode(false);
                mBankPay.setFocusableInTouchMode(false);
                mBankReason.setFocusableInTouchMode(false);
*/
            }
        });

        return view;
    }

    @Override
    public void bankArrayData(ArrayList<BankingDetailsModel> bankingDModel) {
        prog.dismissProgress();
        BankingDetailsModel bankingDetailsModel = bankingDModel.get(0);
        Log.i(TAG, "bankArrayData: "+bankingDetailsModel.getAccountNumber());
        mBankAccNo.setText(bankingDetailsModel.getAccountNumber());
        mBankBankname.setText(bankingDetailsModel.getBankName());
        mBankIFSC.setText(bankingDetailsModel.getIfscCode());
        mBankPan.setText(bankingDetailsModel.getPan());
        mBankReason.setText(bankingDetailsModel.getReason());
        mBankPay.setText(bankingDetailsModel.getPaySalay());
    }

    @Override
    public void closeDialog() {
        prog.dismissProgress();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bank_edit :
                setVisibility(true);
                //Button to save and Cancel to Update the Data to the Server
                mSave.setVisibility(View.VISIBLE);
                mCancel.setVisibility(View.VISIBLE);
                break;
        }
    }
    public  void setVisibility(Boolean stat){
        mBankAccNo.setEnabled(stat);
        mBankBankname.setEnabled(stat);
        mBankIFSC.setEnabled(stat);
        mBankPan.setEnabled(stat);
        mBankPay.setEnabled(stat);
        mBankReason.setEnabled(stat);
    }
}
