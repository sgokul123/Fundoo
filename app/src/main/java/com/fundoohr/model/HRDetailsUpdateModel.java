package com.fundoohr.model;

/**
 * Created by bridgeit on 20/2/17.
 */

public class HRDetailsUpdateModel {
    private String mHiringCity;
    private String mFellowshipPeriod;

    private String mBlStartDate;
    private String mCompanyJoinDate;
    private String mCompanyLeaveDate;
    private String mEnggContractInitiated;
    private String mEnggContractSigned;
    private String mCompContractInitiated;
    private String mCompContractSigned;
    private String mConntractSignDate;
    private String mInitiateTransfer;
    private String  mCompany;
    private String mEmployeeStatus;

    public HRDetailsUpdateModel(String mHiringCity, String mFellowshipPeriod, String mBlStartDate, String mCompanyJoinDate, String mCompanyLeaveDate, String mEnggContractInitiated, String mEnggContractSigned, String mCompContractInitiated, String mCompContractSigned, String mConntractSignDate, String mInitiateTransfer, String mCompany, String mEmployeeStatus) {
        this.mHiringCity = mHiringCity;
        this.mFellowshipPeriod = mFellowshipPeriod;
        this.mBlStartDate = mBlStartDate;
        this.mCompanyJoinDate = mCompanyJoinDate;
        this.mCompanyLeaveDate = mCompanyLeaveDate;
        this.mEnggContractInitiated = mEnggContractInitiated;
        this.mEnggContractSigned = mEnggContractSigned;
        this.mCompContractInitiated = mCompContractInitiated;
        this.mCompContractSigned = mCompContractSigned;
        this.mConntractSignDate = mConntractSignDate;
        this.mInitiateTransfer = mInitiateTransfer;
        this.mCompany = mCompany;
        this.mEmployeeStatus = mEmployeeStatus;
    }

    public String getmInitiateTransfer() {
        return mInitiateTransfer;
    }

    public void setmInitiateTransfer(String mInitiateTransfer) {
        this.mInitiateTransfer = mInitiateTransfer;
    }

    public String getmHiringCity() {
        return mHiringCity;
    }

    public void setmHiringCity(String mHiringCity) {
        this.mHiringCity = mHiringCity;
    }

    public String getmFellowshipPeriod() {
        return mFellowshipPeriod;
    }

    public void setmFellowshipPeriod(String mFellowshipPeriod) {
        this.mFellowshipPeriod = mFellowshipPeriod;
    }

    public String getmBlStartDate() {
        return mBlStartDate;
    }

    public void setmBlStartDate(String mBlStartDate) {
        this.mBlStartDate = mBlStartDate;
    }

    public String getmCompanyJoinDate() {
        return mCompanyJoinDate;
    }

    public void setmCompanyJoinDate(String mCompanyJoinDate) {
        this.mCompanyJoinDate = mCompanyJoinDate;
    }

    public String getmCompanyLeaveDate() {
        return mCompanyLeaveDate;
    }

    public void setmCompanyLeaveDate(String mCompanyLeaveDate) {
        this.mCompanyLeaveDate = mCompanyLeaveDate;
    }

    public String getmEnggContractInitiated() {
        return mEnggContractInitiated;
    }

    public void setmEnggContractInitiated(String mEnggContractInitiated) {
        this.mEnggContractInitiated = mEnggContractInitiated;
    }

    public String getmEnggContractSigned() {
        return mEnggContractSigned;
    }

    public void setmEnggContractSigned(String mEnggContractSigned) {
        this.mEnggContractSigned = mEnggContractSigned;
    }

    public String getmCompContractInitiated() {
        return mCompContractInitiated;
    }

    public void setmCompContractInitiated(String mCompContractInitiated) {
        this.mCompContractInitiated = mCompContractInitiated;
    }

    public String getmCompContractSigned() {
        return mCompContractSigned;
    }

    public void setmCompContractSigned(String mCompContractSigned) {
        this.mCompContractSigned = mCompContractSigned;
    }

    public String getmConntractSignDate() {
        return mConntractSignDate;
    }

    public void setmConntractSignDate(String mConntractSignDate) {
        this.mConntractSignDate = mConntractSignDate;
    }

    public String getmCompany() {
        return mCompany;
    }

    public void setmCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    public String getmEmployeeStatus() {
        return mEmployeeStatus;
    }

    public void setmEmployeeStatus(String mEmployeeStatus) {
        this.mEmployeeStatus = mEmployeeStatus;
    }

}
