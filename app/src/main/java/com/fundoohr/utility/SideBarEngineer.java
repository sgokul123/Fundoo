package com.fundoohr.utility;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.SectionIndexer;

import com.fundoohr.callback.EnggViewModelInterface;

/**
 * Created by bridgeit on 10/12/16.
 */
public class SideBarEngineer extends View {
    private static String TAG ="SideBarEngineer";
    private char[] l;
    private SectionIndexer sectionIndexter = null;
    private ListView list;
    private final int m_nItemHeight = 48;
    private EnggViewModelInterface enggViewModelInterface;

    public SideBarEngineer(Context context) {
        super(context);
        init();
    }

    public SideBarEngineer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        l = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        //Side Bar Color Code
        setBackgroundColor(Color.WHITE);
    }

    public SideBarEngineer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setListView(ListView _list) {
        list = _list;
        sectionIndexter = (SectionIndexer) _list.getAdapter();
    }

    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int i = (int) event.getY();
        int idx = i / m_nItemHeight;
        if (idx >= l.length) {
            idx = l.length - 1;
        } else if (idx < 0) {
            idx = 0;
        }
        // sectionIndexter is used for the fast scroll or the scroll touch
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            try {
                if (sectionIndexter == null) {
                    sectionIndexter = (SectionIndexer) list.getAdapter();
                }
                int position = sectionIndexter.getPositionForSection(l[idx]);
                if (position == -1) {
                    return true;
                }
                list.setSelection(position);
            }catch (Exception e){

            }

        }
        return true;
    }

    public void setCustomTouchListener(EnggViewModelInterface enggViewModelInterface){
        this.enggViewModelInterface = enggViewModelInterface;
    }
    //the use of onDraw using canvas is to give color to the alphabets used in side bar and its size
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        //Alphabet Color
        paint.setColor(Color.rgb(74,94,119));
        paint.setTextSize(50);
       
        paint.setTextAlign(Paint.Align.CENTER);
        float widthCenter = getMeasuredWidth() / 2;
        for (int i = 0; i < l.length; i++) {
            canvas.drawText(String.valueOf(l[i]), widthCenter, m_nItemHeight + (i * m_nItemHeight), paint);
        }
        super.onDraw(canvas);
    }

}
