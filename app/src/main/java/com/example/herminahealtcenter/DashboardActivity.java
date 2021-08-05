package com.example.herminahealtcenter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.herminahealtcenter.adapter.VpAdapterClass;

public class DashboardActivity extends AppCompatActivity {

    private TextView Btnbrd, Btninfo, Btnprof;
    private ViewPager viewPager;
    Boolean on = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
        if (on) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        Btninfo = findViewById(R.id.btninformasi);
        Btnbrd = findViewById(R.id.btnberanda);
        Btnprof = findViewById(R.id.btnprofile);
        viewPager = findViewById(R.id.VPberanda);

        FragmentManager fm = this.getSupportFragmentManager();
        viewPager.setAdapter((PagerAdapter) (new VpAdapterClass(fm)));
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                DashboardActivity.this.chageTabs(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
        Btnbrd.setBackgroundResource(R.drawable.active_back);

        Btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
                Btninfo.setBackgroundResource(R.drawable.active_back);
                Btnprof.setBackgroundResource(R.drawable.inactive_back);
                Btnbrd.setBackgroundResource(R.drawable.inactive_back);
            }
        });

        Btnbrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
                Btnbrd.setBackgroundResource(R.drawable.active_back);
                Btninfo.setBackgroundResource(R.drawable.inactive_back);
                Btnprof.setBackgroundResource(R.drawable.inactive_back);
            }
        });

        Btnprof.setOnClickListener(v -> {
            viewPager.setCurrentItem(2);
            Btnprof.setBackgroundResource(R.drawable.active_back);
            Btninfo.setBackgroundResource(R.drawable.inactive_back);
            Btnbrd.setBackgroundResource(R.drawable.inactive_back);
        });
    }

    private void chageTabs(int position) {
        if (position == 0) {
            Btnbrd.setBackgroundResource(R.drawable.active_back);
            Btninfo.setBackgroundResource(R.drawable.inactive_back);
            Btnprof.setBackgroundResource(R.drawable.inactive_back);
        }
        if (position == 1) {
            Btninfo.setBackgroundResource(R.drawable.active_back);
            Btnprof.setBackgroundResource(R.drawable.inactive_back);
            Btnbrd.setBackgroundResource(R.drawable.inactive_back);
        }
        if (position == 2) {
            Btnprof.setBackgroundResource(R.drawable.active_back);
            Btninfo.setBackgroundResource(R.drawable.inactive_back);
            Btnbrd.setBackgroundResource(R.drawable.inactive_back);
        }
    }

}