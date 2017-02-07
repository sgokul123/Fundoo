package com.fundoohr.bridgeit.fundoohr.callback;

import com.fundoohr.bridgeit.fundoohr.model.PersonalDetailsModel;
import com.fundoohr.bridgeit.fundoohr.model.UpdatePersonalModel;

import java.util.ArrayList;

/**
 * Created by bridgeit on 20/1/17.
 */
public interface PersonalDetailArrayInterface {
    public void personalArrayListDetail(ArrayList <PersonalDetailsModel> personalDetailsModels );
    public void updatePersonalDetail(UpdatePersonalModel updatePersonalModel);
}
