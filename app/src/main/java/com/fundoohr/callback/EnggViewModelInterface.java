package com.fundoohr.callback;

import com.fundoohr.model.EnggFragModel;

import java.util.ArrayList;

/**
 * Created by bridgeit on 10/1/17.
 */
public interface EnggViewModelInterface {
    public void enggViewMInterface(ArrayList<EnggFragModel> enggFragModels);
    public void getCharClicked(int position, char c);
    public  void closeDialog();
}
