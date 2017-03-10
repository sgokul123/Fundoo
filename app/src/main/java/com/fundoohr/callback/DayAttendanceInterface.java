package com.fundoohr.callback;

import com.fundoohr.model.PersonalDetailsModel;
import com.fundoohr.model.UpdatePersonalModel;

import java.util.ArrayList;

/**
 * Created by bridgeit on 27/2/17.
 */

public interface DayAttendanceInterface {
    public void dayattendanceArrayList(byte[] bytes );

    public void updateDayAttendance(UpdatePersonalModel updatePersonalModel);
    public  void closeDialog();
}
