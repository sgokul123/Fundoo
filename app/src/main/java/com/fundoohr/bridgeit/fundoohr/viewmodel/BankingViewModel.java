package com.fundoohr.bridgeit.fundoohr.viewmodel;

import android.content.Context;
import android.util.Log;

import com.fundoohr.bridgeit.fundoohr.callback.BankingDetailArrayInterface;
import com.fundoohr.bridgeit.fundoohr.callback.BankingDetailBInterface;
import com.fundoohr.bridgeit.fundoohr.controller.BankingController;
import com.fundoohr.bridgeit.fundoohr.model.BankingDetailsModel;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bridgeit on 24/1/17.
 * * Purpose:
 * 1.This Class Is The ViewModel Of MVVM Design Pattern.
 * 2.Holding The Model Required For The Content View List.
 * 3.This Class Has The ContentListController Object To Retrieve The Necessary
 * Model.
 * Carries The Required Field Data To The View That Is If You Want Data From
 * Multiple Tables.
 * As In Eg:
 * Here We Can Take The Necessary Data And Pass It To The View.
 */
public class BankingViewModel {
    Context mContext;
    public BankingViewModel() {
    }

    public BankingViewModel(Context mContext) {this.mContext = mContext;}

    public void bankDataList(String token, String mBank_url, RequestParams requestParams, final BankingDetailArrayInterface bankingDetails){
        final ArrayList<BankingDetailsModel> bankDetail = new ArrayList<>();
        BankingController bankController = new BankingController();
        bankController.getBankControllerData(token,mBank_url,requestParams, new BankingDetailBInterface() {
            @Override
            public void bankByteData(byte[] bytes) {
                try {
                        JSONObject jsonObject = new JSONObject(new String(bytes));
                        JSONObject jsonObjectChild = jsonObject.getJSONObject("bankData");
                        Log.i("bankViewModel", "bankByteData: "+jsonObject);
                        for (int i = 0; i <jsonObjectChild.length(); i++) {
                            BankingDetailsModel bankModel = new BankingDetailsModel();
                            bankModel.setAccountNumber(jsonObjectChild.getString("accountNumber"));
                            bankModel.setBankName(jsonObjectChild.getString("bankName"));
                            bankModel.setIfscCode(jsonObjectChild.getString("ifscCode"));
                            bankModel.setPan(jsonObjectChild.getString("pan"));
                            bankModel.setPaySalay(jsonObjectChild.getString("paySalary"));
                            bankModel.setReason(jsonObjectChild.getString("reason"));
                            Log.i("bankViewModel", "bankByteData: "+jsonObjectChild.getString("reason"));
                            bankDetail.add(bankModel);
                        }
                        bankingDetails.bankArrayData(bankDetail);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        });

    }
}
