package com.example.herminahealtcenter;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.herminahealtcenter.utils.SessionsManager;

public class MainActivity extends AppCompatActivity {

    SessionsManager sessionsManager;
    TextView textViewNorm;
    String pasienname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionsManager = new SessionsManager(getApplicationContext());
        pasienname = sessionsManager.getUserName();
        textViewNorm = (TextView) findViewById(R.id.TVsapapasien);


        if(sessionsManager.isLoggedIn() == false) {
                textViewNorm.setText("tidak ada session");
        } else {
            textViewNorm.setText("Hallo : " +  pasienname);
        }
    }
}