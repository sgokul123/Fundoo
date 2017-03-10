package com.fundoohr.callback;

import com.fundoohr.model.BankingDetailsModel;

import java.util.ArrayList;

/**
 * Created by bridgeit on 24/1/17.
 */
public interface BankingDetailArrayInterface {
    public void bankArrayData(ArrayList<BankingDetailsModel> bankingDModel);
    public  void  closeDialog();
}
