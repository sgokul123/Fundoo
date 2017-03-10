package com.fundoohr.model;

/**
 * Created by bridgeit on 13/1/17.
 * Purpose:
 * It Will Contains The Data Object Only WhereIn If You Declare The Object
 * Private You Need To Use Getter And Setter.It Will Have The State And
 * Behaviour Of The Class.
 */
public class HRDetailsModel {
    String employeeID;
    String hiringCity;
    String fellowshipPeriod;
    String blStartDate;
    String companyJoinDate;
    String companyLeaveDate;
    String enggContractInitiated;
    String enggContractSigned;
    String compContractInitiated;
    String compContractSigned;
    String conntractSignDate;
    String initiateTransfer;
    private String  mCompany;

    public String getmEmployeeStatus() {
        return mEmployeeStatus;
    }

    public void setmEmployeeStatus(String mEmployeeStatus) {
        this.mEmployeeStatus = mEmployeeStatus;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getmCompany() {
        return mCompany;
    }

    public void setmCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    private String mEmployeeStatus;


    public HRDetailsModel() {
    }

    public HRDetailsModel(String hiringCity, String fellowshipPeriod, String blStartDate,
                          String companyJoinDate, String companyLeaveDate, String enggContractInitiated,
                          String enggContractSigned, String compContractInitiated,
                          String compContractSigned, String conntractSignDate, String initiateTransfer) {
        this.hiringCity = hiringCity;
        this.fellowshipPeriod = fellowshipPeriod;
        this.blStartDate = blStartDate;
        this.companyJoinDate = companyJoinDate;
        this.companyLeaveDate = companyLeaveDate;
        this.enggContractInitiated = enggContractInitiated;
        this.enggContractSigned = enggContractSigned;
        this.compContractInitiated = compContractInitiated;
        this.compContractSigned = compContractSigned;
        this.conntractSignDate = conntractSignDate;
        this.initiateTransfer = initiateTransfer;
    }

    public String getHiringCity() {
        return hiringCity;
    }

    public void setHiringCity(String hiringCity) {
        this.hiringCity = hiringCity;
    }

    public String getFellowshipPeriod() {
        return fellowshipPeriod;
    }

    public void setFellowshipPeriod(String fellowshipPeriod) {
        this.fellowshipPeriod = fellowshipPeriod;
    }

    public String getBlStartDate() {
        return blStartDate;
    }

    public void setBlStartDate(String blStartDate) {
        this.blStartDate = blStartDate;
    }

    public String getCompanyJoinDate() {
        return companyJoinDate;
    }

    public void setCompanyJoinDate(String companyJoinDate) {
        this.companyJoinDate = companyJoinDate;
    }

    public String getCompanyLeaveDate() {
        return companyLeaveDate;
    }

    public void setCompanyLeaveDate(String companyLeaveDate) {
        this.companyLeaveDate = companyLeaveDate;
    }

    public String getEnggContractInitiated() {
        return enggContractInitiated;
    }

    public void setEnggContractInitiated(String enggContractInitiated) {
        this.enggContractInitiated = enggContractInitiated;
    }

    public String getEnggContractSigned() {
        return enggContractSigned;
    }

    public void setEnggContractSigned(String enggContractSigned) {
        this.enggContractSigned = enggContractSigned;
    }

    public String getCompContractInitiated() {
        return compContractInitiated;
    }

    public void setCompContractInitiated(String compContractInitiated) {
        this.compContractInitiated = compContractInitiated;
    }

    public String getCompContractSigned() {
        return compContractSigned;
    }

    public void setCompContractSigned(String compContractSigned) {
        this.compContractSigned = compContractSigned;
    }

    public String getConntractSignDate() {
        return conntractSignDate;
    }

    public void setConntractSignDate(String conntractSignDate) {
        this.conntractSignDate = conntractSignDate;
    }

    public String getInitiateTransfer() {
        return initiateTransfer;
    }

    public void setInitiateTransfer(String initiateTransfer) {
        this.initiateTransfer = initiateTransfer;
    }
}
