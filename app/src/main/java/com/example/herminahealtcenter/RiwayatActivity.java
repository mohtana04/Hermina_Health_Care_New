package com.example.herminahealtcenter;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.herminahealtcenter.adapter.VpAdapterRiwayat;

public class RiwayatActivity extends AppCompatActivity {

    private AppCompatButton BtnRawatjalan, BtnRawatinap;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_riwayat);

        BtnRawatinap = findViewById(R.id.btnrawatinap);
        BtnRawatjalan = findViewById(R.id.btnrawatjalan);
        viewPager = findViewById(R.id.VPriwayat);

        FragmentManager fm = this.getSupportFragmentManager();
        viewPager.setAdapter((PagerAdapter) (new VpAdapterRiwayat(fm)));
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                RiwayatActivity.this.chageTabs(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
        BtnRawatinap.setBackgroundResource(R.drawable.active_back);

        BtnRawatinap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
                BtnRawatinap.setBackgroundResource(R.drawable.active_back);
            }
        });

        BtnRawatjalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
                BtnRawatjalan.setBackgroundResource(R.drawable.active_back);
            }
        });


    }

    private final void chageTabs(int position) {
        if (position == 0) {
            BtnRawatinap.setBackgroundResource(R.drawable.active_back);
            BtnRawatjalan.setBackgroundResource(R.drawable.inactive_back);
        }
        if (position == 1) {
            BtnRawatinap.setBackgroundResource(R.drawable.inactive_back);
            BtnRawatjalan.setBackgroundResource(R.drawable.active_back);
        }
    }
}