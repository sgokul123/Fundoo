package com.fundoohr.bridgeit.fundoohr.viewmodel;

import com.fundoohr.bridgeit.fundoohr.controller.UpdatePersonalController;
import com.loopj.android.http.RequestParams;

/**
 * Created by bridgeit on 27/1/17.
 */
public class UpdatePersonalViewMOdel {
    public void updatePersonData(String token,RequestParams requestParams){
        UpdatePersonalController updatePersonalController = new UpdatePersonalController();
        updatePersonalController.updateController(token,requestParams);

    }
}
