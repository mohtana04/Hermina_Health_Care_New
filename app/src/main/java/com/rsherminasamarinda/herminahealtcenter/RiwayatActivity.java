package com.rsherminasamarinda.herminahealtcenter;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.rsherminasamarinda.herminahealtcenter.adapter.VpAdapterRiwayat;

public class RiwayatActivity extends AppCompatActivity {

    private TextView BtnRawatjalan, BtnRawatinap;
    private ViewPager viewPager;
    private ImageView riwayatBack;
    Boolean on = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_riwayat);
        if (on) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        BtnRawatinap = findViewById(R.id.btnrawatinap);
        BtnRawatjalan = findViewById(R.id.btnrawatjalan);
        viewPager = findViewById(R.id.VPriwayat);
        riwayatBack = findViewById(R.id.IVbackriwayat);

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

        BtnRawatinap.setOnClickListener(v -> {
            viewPager.setCurrentItem(0);
            BtnRawatinap.setBackgroundResource(R.drawable.active_back);
        });

        BtnRawatjalan.setOnClickListener(v -> {
            viewPager.setCurrentItem(1);
            BtnRawatjalan.setBackgroundResource(R.drawable.active_back);
        });

        riwayatBack.setOnClickListener(v -> {
//            startActivity(new Intent(RiwayatActivity.this, DashboardActivity.class));
            finish();
        });
    }

    private void chageTabs(int position) {
        if (position == 0) {
            BtnRawatinap.setBackgroundResource(R.drawable.active_back);
            BtnRawatjalan.setBackgroundResource(R.drawable.inactive_back);
        }
        if (position == 1) {
            BtnRawatinap.setBackgroundResource(R.drawable.inactive_back);
            BtnRawatjalan.setBackgroundResource(R.drawable.active_back);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}