package com.fundoohr.bridgeit.fundoohr.model;

/**
 * Created by bridgeit on 12/1/17.
 */
public class PersonalDetailsModel {
    String engineerId;
    String emailId;
    String mobile;
    String dateOfBirth;
    String fatherName;
    String fatherMobile;
    String occupation;
    String annualSalary;
    String mumbaiAddress;
    String premanentAddress;

    public PersonalDetailsModel() {
    }

    public PersonalDetailsModel(String engineerId, String emailId, String mobile,
                                String dateOfBirth, String fatherName, String fatherMobile,
                                String occupation, String annualSalary, String mumbaiAddress,
                                String premanentAddress) {
        this.engineerId = engineerId;
        this.emailId = emailId;
        this.mobile = mobile;
        this.dateOfBirth = dateOfBirth;
        this.fatherName = fatherName;
        this.fatherMobile = fatherMobile;
        this.occupation = occupation;
        this.annualSalary = annualSalary;
        this.mumbaiAddress = mumbaiAddress;
        this.premanentAddress = premanentAddress;
    }

    public String getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(String engineerId) {
        this.engineerId = engineerId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherMobile() {
        return fatherMobile;
    }

    public void setFatherMobile(String fatherMobile) {
        this.fatherMobile = fatherMobile;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(String annualSalary) {
        this.annualSalary = annualSalary;
    }

    public String getMumbaiAddress() {
        return mumbaiAddress;
    }

    public void setMumbaiAddress(String mumbaiAddress) {
        this.mumbaiAddress = mumbaiAddress;
    }

    public String getPremanentAddress() {
        return premanentAddress;
    }

    public void setPremanentAddress(String premanentAddress) {
        this.premanentAddress = premanentAddress;
    }
}
