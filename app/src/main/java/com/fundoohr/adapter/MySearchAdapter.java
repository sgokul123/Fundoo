package com.fundoohr.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.model.EnggFragModel;
import com.fundoohr.view.activity.EngineerProfileActivity;
import com.fundoohr.view.fragment.PersonaldetailsFragment;

import java.util.ArrayList;
import java.util.List;


public class MySearchAdapter extends BaseAdapter implements Filterable {
    Context mContext;
    private List<EnggFragModel> mOriginalValues; // Original Values
    private List<EnggFragModel> mDisplayedValues;    // Values to be displayed
    LayoutInflater inflater;

    public MySearchAdapter(Context context, List<EnggFragModel> mProductArrayList) {
        this.mOriginalValues = mProductArrayList;
        this.mDisplayedValues = mProductArrayList;
        inflater = LayoutInflater.from(context);
        this.mContext = context;
    }


    @Override
    public int getCount() {
        return mDisplayedValues.size();
    }

    @Override
    public Object getItem(int position) {
        return mDisplayedValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        LinearLayout llContainer;
        TextView name,status,company,mobile,emailengg;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {

            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_card_engineer, null);
            holder.llContainer = (LinearLayout)convertView.findViewById(R.id.section);
            holder.name = (TextView) convertView.findViewById(R.id.name_engineer);
            holder.status = (TextView) convertView.findViewById(R.id.fellow_engineer);
            holder.company = (TextView) convertView.findViewById(R.id.company_engineer);
            holder.mobile = (TextView) convertView.findViewById(R.id.mob_engineer);
            holder.emailengg = (TextView) convertView.findViewById(R.id.email_engineer);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final EnggFragModel searchModel=(EnggFragModel) getItem(position);
        holder.name.setText(searchModel.getEmployeeName());
        holder.status.setText(searchModel.getEmployeeStatus());
        holder.company.setText(searchModel.getCompany());
        holder.mobile.setText(searchModel.getEmployeeMobile());
        holder.emailengg.setText(searchModel.getEmployeeEmail());

        CardView cardview = (CardView) convertView.findViewById(R.id.engineercarddata);
        //On Click of Cardview the data is displayed on Collasping Layout
        //Which Contains The Engineers Info,Hence to Send the data to that layout
        //Bundle is used and data is passed through bundle using intent
        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,EngineerProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("imageUrl",searchModel.getImageUrl());
                Log.i("engiSidebarAdapter", "onClick: "+bundle);
                bundle.putString("name",searchModel.getEmployeeName());
                bundle.putString("id",searchModel.getEngineerID());
                bundle.putString("status",searchModel.getEmployeeStatus());
                bundle.putString("company",searchModel.getCompany());
                bundle.putString("mobile",searchModel.getEmployeeMobile());
                bundle.putString("emailid",searchModel.getEmployeeEmail());
                bundle.putString("blStart",searchModel.getBlStartDate());
                bundle.putString("companyStart",searchModel.getCompanyStartDate());
                bundle.putString("CompanyLeave",searchModel.getCompanyLeaveDate());
                bundle.putString("leave",searchModel.getLeaveTaken());
                intent.putExtra("data",bundle);



                PersonaldetailsFragment personaldetails = new PersonaldetailsFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("Id",searchModel.getEngineerID());
                // getFragmentManager().beginTransaction().replace(R.id.attendance_frame,personaldetails).commit();
                personaldetails.setArguments(bundle1);
                Log.i("enggid", "enggViewMInterface: "+bundle);
                mContext.startActivity(intent);


            }
        });


        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            public void publishResults(CharSequence constraint,FilterResults results) {

                mDisplayedValues = (ArrayList<EnggFragModel>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new Filter.FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<EnggFragModel> FilteredArrList = new ArrayList<EnggFragModel>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<EnggFragModel>(mDisplayedValues); // saves the original data in mOriginalValues
                }



                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i).getEmployeeName();
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(mOriginalValues.get(i));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
               /* charText = charText.toLowerCase(Locale.getDefault());
                worldpopulationlist.clear();
                if (charText.length() == 0) {
                    worldpopulationlist.addAll(arraylist);
                } else {
                    for (WorldPopulation wp : arraylist) {
                        if (wp.getCountry().toLowerCase(Locale.getDefault())
                                .contains(charText)) {
                            worldpopulationlist.add(wp);
                        }
                    }
                }
                notifyDataSetChanged();
                return results;*/
        };
        return filter;
    }
}

