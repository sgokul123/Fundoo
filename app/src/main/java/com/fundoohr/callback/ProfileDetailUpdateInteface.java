package com.fundoohr.callback;

import com.fundoohr.model.UpdatePersonalModel;

/**
 * Created by bridgeit on 22/2/17.
 */

public interface ProfileDetailUpdateInteface {
    public void callforUpdate(UpdatePersonalModel updatePersonalModel);
    public void  closeDilog();
}
