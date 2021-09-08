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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
    private String nomr, tgllahir, nmpasien, gender;
    private Button buttonLogin;
    private SimpleDateFormat dateformatter;
    private DatePickerDialog datePickerDialog;
    View focusView = null;
    SessionsManager sessionsManager;
    String MetaCode;

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
                if (nomr == null || nomr.equals("")) {
                    AlertKoneksi alert = new AlertKoneksi();
                    alert.showDialog(LoginActivity.this, "Mohon maaf nomor rekam medis tidak boleh kosong");
                } else if (tgllahir == null || tgllahir.equals("")) {
                    AlertKoneksi alert = new AlertKoneksi();
                    alert.showDialog(LoginActivity.this, "Mohon maaf tanggal lahir tidak boleh kosong");
                } else {
                    SafetyNet.getClient(LoginActivity.this).verifyWithRecaptcha(getResources().getString(R.string.recaptcha_site_key)).addOnSuccessListener(new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                        @Override
                        public void onSuccess(SafetyNetApi.RecaptchaTokenResponse recaptchaTokenResponse) {
                            loginProcessWithRetrofit(nomr, tgllahir, recaptchaTokenResponse.getTokenResult());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                }
            }
        });

        editTextTgllahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepickerlogin();
            }
        });

    }

    private void datepickerlogin() {
        Calendar loginCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar loginDate = Calendar.getInstance();
                loginDate.set(year, month, dayOfMonth);
                editTextTgllahir.setText(dateformatter.format(loginDate.getTime()));
            }
        }, loginCalendar.get(Calendar.YEAR), loginCalendar.get(Calendar.MONTH), loginCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void attemptLogin() {
        nomr = editTextNomr.getText().toString();
        tgllahir = editTextTgllahir.getText().toString();

//        loginProcessWithRetrofit(nomr, tgllahir, );
    }


    private void loginProcessWithRetrofit(final String nomr, final String tgllahir, String token) {
        ApiInterface apiService =
                ApiClient.createService(ApiInterface.class, "admin", "h3rm1n4c4r3");
        Call<LoginResponse> mService = apiService.a(nomr, tgllahir);
        mService.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                MetaData code = response.body().getMetaData();
                Login login = response.body().getLogin();
                MetaCode = code.getCode();
                String MetaMessage = code.getMessage();

                if (MetaCode.equals("200")) {
                    nmpasien = login.getNama();
                    gender = login.getGender();

                    Log.d("Retrofit Post", "Jumlah data Kontak: " + nmpasien + " " + gender);
                    Log.d("nama", "session nama :" + sessionsManager.getUserName());
                    sessionsManager.createLoginSession(nomr, nmpasien, gender);
                    Intent log = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(log);

                } else {
                    Log.d("Retrofit Post", "error: " + MetaMessage);
                    AlertKoneksi alert = new AlertKoneksi();
                    alert.showDialog(LoginActivity.this, MetaMessage);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("Retrofit Post", t.toString());
                AlertKoneksi alert = new AlertKoneksi();
                alert.showDialog(LoginActivity.this, "Mohon maaf , sedang dalam perbaikan");
            }
        });
    }
}