package com.fundoohr.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.model.EnggFragModel;
import com.fundoohr.utility.Debug;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bridgeit on 10/12/16.
 */
public class EngineerSideBarAdapter extends BaseAdapter implements SectionIndexer {

    private static final String TAG = "EngineerSideBarAdapter";

    private List<EnggFragModel> mStringArray1;
    private List<String> mSortList;
    LayoutInflater inflate;

    private Context mContext;

    public EngineerSideBarAdapter(ArrayList<EnggFragModel> mData, Context context, ArrayList<String> sortList) {
        this.mStringArray1 = mData;
        this.mContext = context;
        this.mSortList = sortList;
        Debug.showLog(TAG,mStringArray1.get(0).getEmployeeName());
        inflate = ((Activity) mContext).getLayoutInflater();
    }


    @Override
    public int getCount() {
        return mStringArray1.size();
    }

    @Override
    public Object getItem(int i) {
        return mStringArray1.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View v, ViewGroup viewGroup) {

        View view1 = (View) inflate.inflate(R.layout.activity_card_engineer, null);

        LinearLayout header = (LinearLayout) view1.findViewById(R.id.section);

        //Searching Code..........-------
        EnggFragModel enggFragModel = mStringArray1.get(position);
        String name = enggFragModel.getEmployeeName();
        char firstChar = name.toUpperCase().charAt(0);
        if (position == 0) {
            setSection(header, name);
        } else {
            EnggFragModel preLabel = mStringArray1.get(position - 1);
            String empname = preLabel.getEmployeeName();
            char preFirstChar = empname.toUpperCase().charAt(0);
            if (firstChar != preFirstChar) {
                setSection(header, empname);
            } else {
                header.setVisibility(View.GONE);
            }
        }
        TextView textView = (TextView) view1.findViewById(R.id.name_engineer);
        textView.setText(name);
        ImageView imageView = (ImageView) view1.findViewById(R.id.imageEmployee);
        TextView textViewn = (TextView) view1.findViewById(R.id.name_engineer);
        TextView textView1 = (TextView) view1.findViewById(R.id.fellow_engineer);
        TextView textView2 = (TextView) view1.findViewById(R.id.company_engineer);
        TextView textView3 = (TextView) view1.findViewById(R.id.mob_engineer);
        TextView textView4 = (TextView) view1.findViewById(R.id.email_engineer);

        textViewn.setText(enggFragModel.getEmployeeName());
        textView1.setText(enggFragModel.getEmployeeStatus());
        textView2.setText(enggFragModel.getCompany());
        textView3.setText(enggFragModel.getEmployeeMobile());
        textView4.setText(enggFragModel.getEmployeeEmail());

        return view1;
    }

    private void setSection(LinearLayout header, String label) {
        TextView text = new TextView(mContext);
        header.setBackgroundColor(0xFFFFFF);
        //text.setTextColor(Color.WHITE);
        text.setText(label.substring(0, 1).toUpperCase());
        text.setTextSize(0);
        text.setPadding(0, 0, 0, 0);
        //text.setGravity(Gravity.CENTER_VERTICAL);
        header.addView(text);
    }

    @Override

    public int getPositionForSection(int section) {
        if (section == 35) {
            return 0;
        }
        for (int i = 0; i < mStringArray1.size(); i++) {
            EnggFragModel l = mStringArray1.get(i);

            String employee = l.getEmployeeName();
            char firstChar = employee.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int i) {
        return 0;
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}
