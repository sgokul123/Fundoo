package com.fundoohr.callback;

import com.fundoohr.model.TrackingDetailsModel;

import java.util.ArrayList;

/**
 * Created by bridgeit on 24/1/17.
 */
public interface TrackingDetailArrayInterface {
    public void trackingData(ArrayList<TrackingDetailsModel> trackingDetailsModels);
    public void closeDialog();
}
