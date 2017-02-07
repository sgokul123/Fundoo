package com.fundoohr.bridgeit.fundoohr.model;

/**
 * Created by bridgeit on 13/1/17.
 * Purpose:
 * It Will Contains The Data Object Only WhereIn If You Declare The Object
 * Private You Need To Use Getter And Setter.It Will Have The State And
 * Behaviour Of The Class.
 */
public class BankingDetailsModel {
    String accountNumber;
    String bankName;
    String ifscCode;
    String pan;
    String paySalay;
    String reason;

    public BankingDetailsModel() {
    }

    public BankingDetailsModel(String accountNumber, String bankName,
                               String ifscCode, String pan, String paySalay, String reason) {
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.ifscCode = ifscCode;
        this.pan = pan;
        this.paySalay = paySalay;
        this.reason = reason;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPaySalay() {
        return paySalay;
    }

    public void setPaySalay(String paySalay) {
        this.paySalay = paySalay;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
