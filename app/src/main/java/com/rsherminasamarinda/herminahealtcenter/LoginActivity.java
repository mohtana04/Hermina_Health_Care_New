package com.rsherminasamarinda.herminahealtcenter;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
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
    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText editTextNomr;
    private EditText editTextTgllahir;
    private String nomr, tgllahir, nmpasien, gender;
    private Button buttonLogin;
    private SimpleDateFormat dateformatter;
    private DatePickerDialog datePickerDialog;
    View focusView = null;
    SessionsManager sessionsManager;
    String MetaCode, token;

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
        editTextNomr.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        editTextNomr.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

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
//                            token = recaptchaTokenResponse.getTokenResult();
//                            loginProcessWithRetrofit(nomr, tgllahir, token);

                            if (!recaptchaTokenResponse.getTokenResult().isEmpty()){
                                String aa ="asdfsdfdfafafsaf";
                                loginProcessWithRetrofit(recaptchaTokenResponse.getTokenResult());
//                                Toast.makeText(LoginActivity.this,recaptchaTokenResponse.getTokenResult(),Toast.LENGTH_LONG).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (e instanceof ApiException) {
                                // An error occurred when communicating with the
                                // reCAPTCHA service. Refer to the status code to
                                // handle the error appropriately.
                                ApiException apiException = (ApiException) e;
                                int statusCode = apiException.getStatusCode();
                                Log.d("error captcha", "Error: " + CommonStatusCodes
                                        .getStatusCodeString(statusCode));
                            } else {
                                // A different, unknown type of error occurred.
                                Log.d("error captcha", "Error: " + e.getMessage());
                            }
                        }
                    });
//                    gender = "L";
//                    sessionsManager.createLoginSession(nomr, nmpasien, gender);
//                    Intent log = new Intent(LoginActivity.this, DashboardActivity.class);
//                    startActivity(log);
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


    private void loginProcessWithRetrofit(String token) {

        nomr = editTextNomr.getText().toString();
        tgllahir = editTextTgllahir.getText().toString();
        ApiInterface apiService =
                ApiClient.createService(ApiInterface.class, "admin", "h3rm1n4c4r3");
        Call<LoginResponse> mService = apiService.a(nomr, tgllahir, token);
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
                alert.showDialog(LoginActivity.this, "Cek koneksi anda");
            }
        });
    }
}