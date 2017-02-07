package com.fundoohr.bridgeit.fundoohr.model;

/**
 * Created by bridgeit on 8/1/17.
 * Purpose:
 * It Will Contains The Data Object Only WhereIn If You Declare The Object
 * Private You Need To Use Getter And Setter.It Will Have The State And
 * Behaviour Of The Class.
 */
public class EnggFragModel {
    String imageUrl;
    String engineerID;
    String employeeName;
    String employeeStatus;
    String company;
    String employeeEmail;
    String employeeMobile;
    String blStartDate;
    String companyStartDate;
    String companyLeaveDate;
    String leaveTaken;


    public EnggFragModel() {
    }

    public EnggFragModel(String imageUrl,String engineerID, String employeeName, String employeeStatus,
                         String company, String employeeEmail, String employeeMobile,
                         String blStartDate,String companyStartDate,String companyLeaveDate, String leaveTaken) {
        this.imageUrl = imageUrl;
        this.engineerID = engineerID;
        this.employeeName = employeeName;
        this.employeeStatus = employeeStatus;
        this.company = company;
        this.employeeEmail = employeeEmail;
        this.employeeMobile = employeeMobile;
        this.blStartDate=blStartDate;
        this.companyStartDate=companyStartDate;
        this.companyLeaveDate=companyLeaveDate;
        this.leaveTaken=leaveTaken;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public void setImageurl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getEngineerID() {
        return engineerID;
    }

    public void setEngineerID(String engineerID) {
        this.engineerID = engineerID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {

        this.employeeName = employeeName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeMobile() {
        return employeeMobile;
    }

    public void setEmployeeMobile(String employeeMobile) {
        this.employeeMobile = employeeMobile;
    }
    public String getBlStartDate() {
        return blStartDate;
    }

    public void setBlStartDate(String blStartDate) {
        this.blStartDate = blStartDate;
    }

    public String getCompanyStartDate() {
        return companyStartDate;
    }

    public void setCompanyStartDate(String companyStartDate) {
        this.companyStartDate = companyStartDate;
    }

    public String getCompanyLeaveDate() {
        return companyLeaveDate;
    }

    public void setCompanyLeaveDate(String companyLeaveDate) {
        this.companyLeaveDate = companyLeaveDate;
    }

    public String getLeaveTaken() {
        return leaveTaken;
    }

    public void setLeaveTaken(String leaveTaken) {
        this.leaveTaken = leaveTaken;
    }

}
