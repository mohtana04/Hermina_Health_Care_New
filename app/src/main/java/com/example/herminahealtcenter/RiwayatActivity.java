package com.example.herminahealtcenter;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentTransaction;

import com.example.herminahealtcenter.fragment.RiwayatRwi;
import com.example.herminahealtcenter.fragment.RiwayatRwj;

public class RiwayatActivity extends AppCompatActivity {

    private AppCompatButton BtnRawatjalan, BtnRawatinap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_riwayat);

        BtnRawatinap = findViewById(R.id.btnrawatinap);
        BtnRawatjalan = findViewById(R.id.btnrawatjalan);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(
                R.id.framefragmentriwayat, new RiwayatRwi()
        );
        ft.commit();

        BtnRawatinap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(
                        R.id.framefragmentriwayat, new RiwayatRwi()
                );
                ft.commit();
            }
        });

        BtnRawatjalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(
                        R.id.framefragmentriwayat, new RiwayatRwj()
                );
                ft.commit();
            }
        });
    }
}