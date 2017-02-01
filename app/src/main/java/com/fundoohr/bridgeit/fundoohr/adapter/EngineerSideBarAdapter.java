package com.fundoohr.bridgeit.fundoohr.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.bridgeit.fundoohr.model.EnggFragModel;
import com.fundoohr.bridgeit.fundoohr.view.activity.EngineerProfileActivity;
import com.fundoohr.bridgeit.fundoohr.view.fragment.Personaldetails;

import java.util.ArrayList;

/**
 * Created by bridgeit on 10/12/16.
 */
public class EngineerSideBarAdapter extends BaseAdapter implements SectionIndexer {
    private ArrayList<EnggFragModel> mStringArray1;
    ArrayList<String> mStringArray;
    private Context mContext;

    public EngineerSideBarAdapter(ArrayList<EnggFragModel> mData , Context context) {
         this.mStringArray1 = mData;
         this.mContext = context;
         Log.i("EnggSideBar", "EngineerSideBarAdapter: ghnjnj   "+mStringArray1.get(0).getEmployeeName());

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

        LayoutInflater inflate = ((Activity) mContext).getLayoutInflater();
        View view1 = (View) inflate.inflate(R.layout.activity_card_engineer, null);

        LinearLayout header = (LinearLayout) view1.findViewById(R.id.section);
        CardView cardview = (CardView) header.findViewById(R.id.engineer_card_data);
        //On Click of Cardview the data is displayed on Collasping Layout
        //Which Contains The Engineers Info,Hence to Send the data to that layout
        //Bundle is used and data is passed through bundle using intent
        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,EngineerProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",mStringArray1.get(position).getEmployeeName());
                bundle.putString("id",mStringArray1.get(position).getEngineerID());
                bundle.putString("status",mStringArray1.get(position).getEmployeeStatus());
                bundle.putString("company",mStringArray1.get(position).getCompany());
                bundle.putString("mobile",mStringArray1.get(position).getEmployeeMobile());
                bundle.putString("emailid",mStringArray1.get(position).getEmployeeEmail());
                bundle.putString("blStart",mStringArray1.get(position).getBlStartDate());
                bundle.putString("companyStart",mStringArray1.get(position).getCompanyStartDate());
                bundle.putString("CompanyLeave",mStringArray1.get(position).getCompanyLeaveDate());
                bundle.putString("leave",mStringArray1.get(position).getLeaveTaken());
                intent.putExtra("data",bundle);



                Personaldetails personaldetails = new Personaldetails();
                Bundle bundle1 = new Bundle();
                bundle1.putString("Id", mStringArray1.get(position).getEngineerID());
                // getFragmentManager().beginTransaction().replace(R.id.attendance_frame,personaldetails).commit();
                personaldetails.setArguments(bundle1);
                Log.i("enggid", "enggViewMInterface: "+bundle);




                mContext.startActivity(intent);


            }
        });
        //Searching Code..........-------
        EnggFragModel label = mStringArray1.get(position);
        String name = label.getEmployeeName();
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

        TextView textViewn = (TextView) view1.findViewById(R.id.name_engineer);
        TextView textView1 = (TextView) view1.findViewById(R.id.fellow_engineer);
        TextView textView2 = (TextView) view1.findViewById(R.id.company_engineer);
        TextView textView3 = (TextView) view1.findViewById(R.id.mob_engineer);
        TextView textView4 = (TextView) view1.findViewById(R.id.email_engineer);

        textViewn.setText(mStringArray1.get(position).getEmployeeName());
        textView1.setText(mStringArray1.get(position).getEmployeeStatus());
        textView2.setText(mStringArray1.get(position).getCompany());
        textView3.setText(mStringArray1.get(position).getEmployeeMobile());
        textView4.setText(mStringArray1.get(position).getEmployeeEmail());
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
        if (section == 35) {
            return 0;
        }
        for (int i = 0; i < mStringArray1.size(); i++) {
            EnggFragModel emp = mStringArray1.get(i);
            String l = emp.getEmployeeName();
            char firstChar = l.toUpperCase().charAt(0);
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
