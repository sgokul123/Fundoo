package com.fundoohr.model;

/**
 * Created by bridgeit on 25/1/17.
 * * Purpose:
 * It Will Contains The Data Object Only WhereIn If You Declare The Object
 * Private You Need To Use Getter And Setter.It Will Have The State And
 * Behaviour Of The Class.
 */
public class AttendenceModel {
    String days;
    String attendenceStatus;
    String markedStatus;
    String punchIn;
    String punchOut;
    String reason;

    public AttendenceModel() {
    }

    public String getAttendenceStatus() {
        return attendenceStatus;
    }

    public void setAttendenceStatus(String attendenceStatus) {
        this.attendenceStatus = attendenceStatus;
    }

    @Override
    public String toString() {
        return "AttendenceModel{" +
                "days='" + days + '\'' +
                ", attendenceStatus='" + attendenceStatus + '\'' +
                ", markedStatus='" + markedStatus + '\'' +
                ", punchIn='" + punchIn + '\'' +
                ", punchOut='" + punchOut + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }

    public String getDays(){
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getMarkedStatus() {
        return markedStatus;
    }

    public void setMarkedStatus(String markedStatus) {
        this.markedStatus = markedStatus;
    }

    public String getPunchIn() {
        return punchIn;
    }

    public void setPunchIn(String punchIn) {
        this.punchIn = punchIn;
    }

    public String getPunchOut() {
        return punchOut;
    }

    public void setPunchOut(String punchOut) {
        this.punchOut = punchOut;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
