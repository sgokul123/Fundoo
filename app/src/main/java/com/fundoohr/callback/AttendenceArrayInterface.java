package com.fundoohr.callback;

import com.fundoohr.model.AttendenceModel;
import com.fundoohr.model.UpdatePersonalModel;

import java.util.ArrayList;

/**
 * Created by bridgeit on 25/1/17.
 */
public interface AttendenceArrayInterface {
    public void getAttendArrayData(ArrayList<AttendenceModel> attendenceModels);
    public  void onFailureGetData(UpdatePersonalModel updatePersonalModel);
    public void closeDialog();
}
