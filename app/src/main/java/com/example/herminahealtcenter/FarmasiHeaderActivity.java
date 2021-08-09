package com.example.herminahealtcenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.herminahealtcenter.utils.SessionsManager;

public class FarmasiHeaderActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    SessionsManager sessionsManager;
    String norm;
    Boolean on = true;
    private Activity activity;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView backFarmasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmasi_header);
        if (on) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayoutfar);
        backFarmasi = findViewById(R.id.IVbackfarmasi);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
//                refreshData();
            }
        });
        sessionsManager = new SessionsManager(getApplicationContext());
        norm = sessionsManager.getUserNomr();

        backFarmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    @Override
    public void onRefresh() {

    }
}