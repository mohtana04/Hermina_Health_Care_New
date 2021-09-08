package com.rsherminasamarinda.herminahealtcenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.rsherminasamarinda.herminahealtcenter.utils.SessionsManager;

public class MainActivity extends AppCompatActivity {

    SessionsManager sessionsManager;
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sessionsManager = new SessionsManager(getApplicationContext());
                if (sessionsManager.isLoggedIn() == true) {
                    Intent log = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(log);
                } else {
                    Intent log = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(log);
                }
            }
        }, SPLASH_TIME_OUT);
    }
}