package com.example.herminahealtcenter;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.herminahealtcenter.fragment.HomeFragment;
import com.example.herminahealtcenter.fragment.InformationFragment;
import com.example.herminahealtcenter.fragment.ProfileFragment;

public class DashboardActivity extends AppCompatActivity {

    private Button Btninfo, Btnbrd, Btnprof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(
                R.id.framefragmentberanda, new HomeFragment()
        );
        ft.commit();

        Btninfo = findViewById(R.id.btninformasi);
        Btnbrd = findViewById(R.id.btnberanda);
        Btnprof = findViewById(R.id.btnprofile);

        Btnbrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(
                        R.id.framefragmentberanda, new HomeFragment()
                );
                ft.commit();
            }
        });

        Btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(
                        R.id.framefragmentberanda, new InformationFragment()
                );
                ft.commit();
            }
        });

        Btnprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(
                        R.id.framefragmentberanda, new ProfileFragment()
                );
                ft.commit();
            }
        });



    }
}