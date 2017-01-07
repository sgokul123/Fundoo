/*
package com.example.bridgeit.fundoo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bridgeit.fundoo.R;
import com.example.bridgeit.fundoo.model.SearchModel;

import java.util.ArrayList;

*//*

 * /Created by bridgeit on 2/1/17.


public class MySearchAdapter extends BaseAdapter implements Filterable {
    Context mContext;
    private ArrayList<SearchModel> mOriginalValues; // Original Values
    private ArrayList<SearchModel> mDisplayedValues;    // Values to be displayed
    LayoutInflater inflater;

    public MySearchAdapter(Context context, ArrayList<SearchModel> mProductArrayList) {
        this.mOriginalValues = mProductArrayList;
        this.mDisplayedValues = mProductArrayList;
        inflater = LayoutInflater.from(context);
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
            convertView = inflater.inflate(R.layout.card_engineer, null);
            holder.llContainer = (LinearLayout)convertView.findViewById(R.id.section);
            holder.name = (TextView) convertView.findViewById(R.id.name_engineer);
            holder.status = (TextView) convertView.findViewById(R.id.fellow_engineer);
            holder.company = (TextView) convertView.findViewById(R.id.bridge_engineer);
            holder.mobile = (TextView) convertView.findViewById(R.id.mob_engineer);
            holder.emailengg = (TextView) convertView.findViewById(R.id.email_engineer);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
       SearchModel searchModel=(SearchModel) getItem(position);
       holder.name.setText(searchModel.getmName());
        holder.status.setText(searchModel.getmName());
        holder.company.setText(searchModel.getmName());
        holder.mobile.setText(searchModel.getmName());
        holder.emailengg.setText(searchModel.getmName());




        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                mDisplayedValues = (ArrayList<SearchModel>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new Filter.FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<SearchModel> FilteredArrList = new ArrayList<SearchModel>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<SearchModel>(mDisplayedValues); // saves the original data in mOriginalValues
                }

*******
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 *******

                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i).mName;
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(new SearchModel(mOriginalValues.get(i).mName));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }
}

*/
