package com.example.alhosn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ch.halcyon.squareprogressbar.SquareProgressBar;


/**
 * Created by A on 16-03-2018.
 */

public class TabAdapter extends PagerAdapter {

    private Context mContext;
    private TextView timer1, timer2, date, date1, date2, date3;
    SquareProgressBar squareProgressBar;

    public TabAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout;
        if(position == 0){
            layout = (ViewGroup) inflater.inflate(R.layout.fragment_expo, collection, false);
            timer1 = layout.findViewById(R.id.timer);
        }else{
            layout = (ViewGroup) inflater.inflate(R.layout.fragment_green_pass, collection, false);
            timer2 = layout.findViewById(R.id.timer);
        }

        collection.addView(layout);
        createElements(layout);
        return layout;
    }


    private void createElements(ViewGroup view){
        date = view.findViewById(R.id.date);
        date1 = view.findViewById(R.id.date1);
        date2 = view.findViewById(R.id.date2);
        date3 = view.findViewById(R.id.date3);




        squareProgressBar = view.findViewById(R.id.sprogressbar);
        squareProgressBar.setProgress(50.0f);
        squareProgressBar.setIndeterminate(true);
        squareProgressBar.setRoundedCorners(true, 25);
        squareProgressBar.setColor("#44b49c");

        date.setText("Since " + getCalculatedDate(-3));
        date1.setText(getCalculatedDate(-3));
        date2.setText(getCalculatedDate(-30));
        date3.setText(getCalculatedDate(-45));

    }



    public void tick(String str){
        if(timer1 != null)
            timer1.setText("QR code will be updated in " + str);

        if(timer2 != null)
            timer2.setText("QR code will be updated in " + str);
    }


    public static String getCalculatedDate(int days) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("dd MMM yyyy");
        cal.add(Calendar.DAY_OF_YEAR, days);
        return s.format(new Date(cal.getTimeInMillis()));
    }
}
