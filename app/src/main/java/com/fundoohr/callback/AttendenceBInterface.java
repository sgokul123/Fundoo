package com.fundoohr.callback;

import com.fundoohr.model.UpdatePersonalModel;

/**
 * Created by bridgeit on 25/1/17.
 */
public interface AttendenceBInterface {
    public void getAttendBData(byte[] bytes);
    public  void onFailuarBack(byte [] bytes);
    public void  closeDialog();


}
