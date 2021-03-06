package com.rsherminasamarinda.herminahealtcenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rsherminasamarinda.herminahealtcenter.utils.SessionsManager;

public class MainActivity extends AppCompatActivity {
    TextView textViewVersiname ;
    SessionsManager sessionsManager;
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String versionName = "Versi "+BuildConfig.VERSION_NAME;
        textViewVersiname = (TextView) findViewById(R.id.versionname);
        textViewVersiname.setText(versionName);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sessionsManager = new SessionsManager(getApplicationContext());
                if (sessionsManager.isLoggedIn() == true) {
                    Intent log = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(log);
                    finish();
                } else {
                    Intent log = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(log);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
}