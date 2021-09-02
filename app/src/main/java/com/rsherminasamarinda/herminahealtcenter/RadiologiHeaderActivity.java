package com.rsherminasamarinda.herminahealtcenter;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.rsherminasamarinda.herminahealtcenter.Alert.AlertKoneksi;
import com.rsherminasamarinda.herminahealtcenter.adapter.RadheadAdapter;
import com.rsherminasamarinda.herminahealtcenter.model.Historyradheader;
import com.rsherminasamarinda.herminahealtcenter.model.HistoryradheaderResponse;
import com.rsherminasamarinda.herminahealtcenter.model.MetaData;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiClient;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiInterface;
import com.rsherminasamarinda.herminahealtcenter.utils.SessionsManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RadiologiHeaderActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    SessionsManager sessionsManager;
    String norm;
    Boolean on = true;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView backRadiologi;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiologi_header);
        if (on) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        backRadiologi = findViewById(R.id.IVbackradiologi);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayoutrad);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                refreshData();
            }
        });

        backRadiologi.setOnClickListener(v -> {
            finish();
        });


    }

    public void refreshData() {
        sessionsManager = new SessionsManager(getApplicationContext());
        norm = sessionsManager.getUserNomr();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvradiologiheader);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.createService(ApiInterface.class, "admin", "h3rm1n4c4r3");


        Call<HistoryradheaderResponse> call = apiService.hradhead(norm);
        call.enqueue(new Callback<HistoryradheaderResponse>() {
            @Override
            public void onResponse(Call<HistoryradheaderResponse> call, Response<HistoryradheaderResponse> response) {
                MetaData code = response.body().getMetaData();
                String MetaCode = code.getCode();
                String MetaMessage = code.getMessage();
                Log.d("Retrofit Post", "Jumlah data Rad: " + MetaCode);
                final List<Historyradheader> historyradheaderList = response.body().getHistoryradheader();
                recyclerView.setAdapter(new RadheadAdapter(historyradheaderList, R.layout.radheader_list_item_layout, getApplicationContext()));
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<HistoryradheaderResponse> call, Throwable t) {
//                Toast.makeText(RadiologiHeaderActivity.this, "gagal", Toast.LENGTH_LONG).show();
                AlertKoneksi alert = new AlertKoneksi();
                alert.showDialog(RadiologiHeaderActivity.this,"Mohon maaf , sedang dalam perbaikan");
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public void onRefresh() {
        refreshData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}