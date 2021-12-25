package com.example.alhosn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView title1, title2;
    private TabAdapter adapter;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title1 = findViewById(R.id.title1);
        title2 = findViewById(R.id.title2);


        ViewPager pager = findViewById(R.id.photos_viewpager);
        adapter = new TabAdapter(this);
        pager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager, true);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if(position == 1 && positionOffset == 0)
                    return;

                title1.animate().translationX(- 2 * positionOffset * title1.getWidth()).setDuration(0).start();
                title2.animate().translationX( 1.5f * (1.0f - positionOffset) * title1.getWidth()).setDuration(0).start();
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        countDownTimer = new CountDownTimer(300000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                long sec = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished));

                String secStr = sec < 10 ? "0" + sec : String.valueOf(sec);

                String str = ""+String.format("0%d:%s",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        secStr);

                adapter.tick(str);
            }

            public void onFinish() {
                countDownTimer.cancel();
                countDownTimer.start();
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        countDownTimer.cancel();
        countDownTimer.start();
    }
}
