package com.rsherminasamarinda.herminahealtcenter;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.rsherminasamarinda.herminahealtcenter.Alert.AlertKoneksi;
import com.rsherminasamarinda.herminahealtcenter.model.Login;
import com.rsherminasamarinda.herminahealtcenter.model.LoginResponse;
import com.rsherminasamarinda.herminahealtcenter.model.MetaData;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiClient;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiInterface;
import com.rsherminasamarinda.herminahealtcenter.utils.SessionsManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextNomr;
    private EditText editTextTgllahir;
    private String nomr,tgllahir, nmpasien, gender;
    private Button buttonLogin;
    private SimpleDateFormat dateformatter;
    private DatePickerDialog datePickerDialog;
    View focusView = null;
    SessionsManager sessionsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_activity);
        sessionsManager = new SessionsManager(getApplicationContext());
        if (sessionsManager.isLoggedIn() == true) {
            Intent log = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(log);
        }
        editTextNomr = (EditText) findViewById(R.id.ETnomorcm);
        editTextTgllahir = (EditText) findViewById(R.id.ETtgllahir);
        buttonLogin = (Button) findViewById(R.id.BTlogin);

        dateformatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                attemptLogin();
                nomr = editTextNomr.getText().toString();
                tgllahir = editTextTgllahir.getText().toString();
                loginProcessWithRetrofit(nomr, tgllahir);
//                Toast.makeText(LoginActivity.this,nomr + " " + tgllahir,Toast.LENGTH_LONG).show();
            }
        });

        editTextTgllahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepickerlogin();
            }
        });

    }

    private void datepickerlogin () {
        Calendar loginCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar loginDate = Calendar.getInstance();
                loginDate.set(year, month, dayOfMonth);
                editTextTgllahir.setText(dateformatter.format(loginDate.getTime()));
            }
        },loginCalendar.get(Calendar.YEAR), loginCalendar.get(Calendar.MONTH), loginCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void attemptLogin() {
        nomr = editTextNomr.getText().toString();
        tgllahir = editTextTgllahir.getText().toString();
        loginProcessWithRetrofit(nomr, tgllahir);
    }



    private void loginProcessWithRetrofit(final String nomr,final String tgllahir) {
        ApiInterface apiService =
                ApiClient.createService(ApiInterface.class, "admin", "h3rm1n4c4r3");
        Call<LoginResponse> mService = apiService.a(nomr, tgllahir);
        mService.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                MetaData code =response.body().getMetaData();
                Login login = response.body().getLogin();
                String MetaCode = code.getCode();
                String MetaMessage = code.getMessage();

                if (MetaCode.equals("200")) {
                    nmpasien = login.getNama();
                    gender = login.getGender();
                    sessionsManager.createLoginSession(nomr,nmpasien,gender);
                    Log.d("Retrofit Post", "Jumlah data Kontak: " + nmpasien + " " + gender);
                    Log.d("nama" , "session nama :"  +sessionsManager.getUserName() );
                    Intent log = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(log);
                } else {
                    Log.d("Retrofit Post", "error: " + MetaMessage);
                    AlertKoneksi alert = new AlertKoneksi();
                    alert.showDialog(LoginActivity.this,MetaMessage);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("Retrofit Post", t.toString());
                AlertKoneksi alert = new AlertKoneksi();
                alert.showDialog(LoginActivity.this,"Mohon maaf , sedang dalam perbaikan");
            }
        });
    }
}