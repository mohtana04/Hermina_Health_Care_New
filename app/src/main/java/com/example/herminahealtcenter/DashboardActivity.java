package com.example.herminahealtcenter;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.herminahealtcenter.fragment.HomeFragment;
import com.example.herminahealtcenter.fragment.InformationFragment;
import com.example.herminahealtcenter.fragment.ProfileFragment;

public class DashboardActivity extends AppCompatActivity {

    private Button Btninfo, Btnbrd, Btnprof;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(
                R.id.VPberanda, new HomeFragment()
        );
        ft.commit();

        Btninfo = findViewById(R.id.btninformasi);
        Btnbrd = findViewById(R.id.btnberanda);
        Btnprof = findViewById(R.id.btnprofile);
        viewPager = findViewById(R.id.VPberanda);
    }


}