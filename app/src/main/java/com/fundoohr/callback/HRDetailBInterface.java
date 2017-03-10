package com.fundoohr.callback;

/**
 * Created by bridgeit on 23/1/17.
 */
public interface HRDetailBInterface {
    public void getHrData(byte[] bytes);
    public  void getStatusofUpdate(String msg);
    public  void  closeDialog();
}
