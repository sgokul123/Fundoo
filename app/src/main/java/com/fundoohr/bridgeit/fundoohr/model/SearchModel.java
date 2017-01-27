package com.fundoohr.bridgeit.fundoohr.model;

/**
 * Created by bridgeit on 2/1/17.
 */
public class SearchModel {
    public String mName;
    public String mStatus;
    public String mCompany;
    public String mEmail;
    public String mMobile;

    public SearchModel(String mName, String mStatus, String mCompany, String mEmail, String mMobile) {
        this.mName = mName;
        this.mStatus = mStatus;
        this.mCompany = mCompany;
        this.mEmail = mEmail;
        this.mMobile = mMobile;
    }

    public SearchModel(String mName) {
        this.mName = mName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmCompany() {
        return mCompany;
    }

    public void setmCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmMobile() {
        return mMobile;
    }

    public void setmMobile(String mMobile) {
        this.mMobile = mMobile;
    }
}
