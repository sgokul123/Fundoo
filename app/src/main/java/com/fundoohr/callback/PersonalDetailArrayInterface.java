package com.fundoohr.callback;

import com.fundoohr.model.PersonalDetailsModel;
import com.fundoohr.model.UpdatePersonalModel;

import java.util.ArrayList;

/**
 * Created by bridgeit on 20/1/17.
 */
public interface PersonalDetailArrayInterface {
    public void personalArrayListDetail(ArrayList <PersonalDetailsModel> personalDetailsModels );
    public void updatePersonalDetail(UpdatePersonalModel updatePersonalModel);
    public void closeDialog();
}
