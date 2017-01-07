package com.example.bridgeit.fundoo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bridgeit.fundoo.R;
import com.example.bridgeit.fundoo.view.Engineer_Collapse;
import com.example.bridgeit.fundoo.view.Engineer_Fragment;

import java.util.ArrayList;

/**
 * Created by bridgeit on 10/12/16.
 */
public class Engineer_SideBar_adapter extends BaseAdapter implements SectionIndexer {
    private ArrayList<String> mStringArray;
    private Context mContext;

    public Engineer_SideBar_adapter(ArrayList mData) {
    }

    public Engineer_SideBar_adapter(Context context, ArrayList data) {
        this.mStringArray = data;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mStringArray.size();
    }

    @Override
    public Object getItem(int i) {
        return mStringArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View v, ViewGroup viewGroup) {

        LayoutInflater inflate = ((Activity) mContext).getLayoutInflater();
        View view1 = (View) inflate.inflate(R.layout.card_engineer, null);

        LinearLayout header = (LinearLayout) view1.findViewById(R.id.section);
        CardView cardview = (CardView) header.findViewById(R.id.engineer_card_data);
        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,Engineer_Collapse.class);
                mContext.startActivity(intent);
            }
        });
        String label = mStringArray.get(position);
        char firstChar = label.toUpperCase().charAt(0);
        if (position == 0) {
            setSection(header, label);
        } else {
            String preLabel = mStringArray.get(position - 1);
            char preFirstChar = preLabel.toUpperCase().charAt(0);
            if (firstChar != preFirstChar) {
                setSection(header, label);
            } else {
                header.setVisibility(View.GONE);
            }
        }
        TextView textView = (TextView) view1.findViewById(R.id.name_engineer);
        textView.setText(label);

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
        for (int i = 0; i < mStringArray.size(); i++) {
            String l = mStringArray.get(i);
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
