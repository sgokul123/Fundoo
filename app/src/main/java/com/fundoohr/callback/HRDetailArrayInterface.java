package com.fundoohr.callback;

import com.fundoohr.model.HRDetailsModel;

import java.util.ArrayList;

/**
 * Created by bridgeit on 23/1/17.
 */
public interface HRDetailArrayInterface {
    public void hrDetailData(ArrayList<HRDetailsModel> hrDetailModels);
   public void  isDataUpdated(String status);
    public void  closeDilog();
}
