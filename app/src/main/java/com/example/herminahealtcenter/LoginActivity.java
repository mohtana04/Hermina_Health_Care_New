package com.example.herminahealtcenter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.herminahealtcenter.model.Login;
import com.example.herminahealtcenter.model.LoginResponse;
import com.example.herminahealtcenter.model.MetaData;
import com.example.herminahealtcenter.rest.ApiClient;
import com.example.herminahealtcenter.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextNomr;
    private EditText editTextTgllahir;
    private String nomr,tgllahir;
    private Button buttonLogin;
    View focusView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        editTextNomr = (EditText) findViewById(R.id.ETnomorcm);
        editTextTgllahir = (EditText) findViewById(R.id.ETtgllahir);
        buttonLogin = (Button) findViewById(R.id.BTlogin);


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

    }

    private void attemptLogin() {
        nomr = editTextNomr.getText().toString();
        tgllahir = editTextTgllahir.getText().toString();
        loginProcessWithRetrofit(nomr, tgllahir);
    }



    private void loginProcessWithRetrofit(String nomr,String tgllahir) {
        ApiInterface apiService =
                ApiClient.createService(ApiInterface.class, "admin", "h3rm1n4c4r3");
        Call<LoginResponse> mService = apiService.a(nomr, tgllahir);
      mService.enqueue(new Callback<LoginResponse>() {
          @Override
          public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
            MetaData code =response.body().getMetaData();
            List<Login> logins = response.body().getResponse();
            String MetaCode = code.getCode();
              Log.d("Retrofit Post", "Jumlah data Kontak: " + MetaCode);

              Toast.makeText(LoginActivity.this,MetaCode,Toast.LENGTH_LONG).show();
          }

          @Override
          public void onFailure(Call<LoginResponse> call, Throwable t) {
              Log.e("Retrofit Post", t.toString());
              Toast.makeText(LoginActivity.this,"GAGAL",Toast.LENGTH_LONG).show();
          }
      });
    }
}