package com.fundoohr.bridgeit.fundoohr.model;

/**
 * Created by bridgeit on 13/1/17.
 * Purpose:
 * It Will Contains The Data Object Only WhereIn If You Declare The Object
 * Private You Need To Use Getter And Setter.It Will Have The State And
 * Behaviour Of The Class.
 */
public class TrackingDetailsModel {
    String techStack;
    String bridgelabzStartDate;
    String bridgelabzEndDate;
    String currentWeek;
    String Weeks;
    String numberOfWeeksLeft;
    String numberWeeksLeft;
    String week1;

    public TrackingDetailsModel() {

    }

    public TrackingDetailsModel(String techStack, String bridgelabzStartDate, String bridgelabzEndDate,
                                String currentWeek, String weeks, String numberOfWeeksLeft,
                                String numberWeeksLeft, String week1) {
        this.techStack = techStack;
        this.bridgelabzStartDate = bridgelabzStartDate;
        this.bridgelabzEndDate = bridgelabzEndDate;
        this.currentWeek = currentWeek;
        Weeks = weeks;
        this.numberOfWeeksLeft = numberOfWeeksLeft;
        this.numberWeeksLeft = numberWeeksLeft;
        this.week1 = week1;
    }

    public String getNumberWeeksLeft() {
        return numberWeeksLeft;
    }

    public void setNumberWeeksLeft(String numberWeeksLeft) {
        this.numberWeeksLeft = numberWeeksLeft;
    }

    public String getTechStack() {
        return techStack;
    }

    public void setTechStack(String techStack) {
        this.techStack = techStack;
    }

    public String getBridgelabzStartDate() {
        return bridgelabzStartDate;
    }

    public void setBridgelabzStartDate(String bridgelabzStartDate) {
        this.bridgelabzStartDate = bridgelabzStartDate;
    }

    public String getBridgelabzEndDate() {
        return bridgelabzEndDate;
    }

    public void setBridgelabzEndDate(String bridgelabzEndDate) {
        this.bridgelabzEndDate = bridgelabzEndDate;
    }

    public String getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(String currentWeek) {
        this.currentWeek = currentWeek;
    }

    public String getWeeks() {
        return Weeks;
    }

    public void setWeeks(String weeks) {
        Weeks = weeks;
    }

    public String getNumberOfWeeksLeft() {
        return numberOfWeeksLeft;
    }

    public void setNumberOfWeeksLeft(String numberOfWeeksLeft) {
        this.numberOfWeeksLeft = numberOfWeeksLeft;
    }

    public String getWeek1() {
        return week1;
    }

    public void setWeek1(String week1) {
        this.week1 = week1;
    }
}
