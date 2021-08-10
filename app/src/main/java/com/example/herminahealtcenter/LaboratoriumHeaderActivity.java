package com.example.herminahealtcenter;

import android.app.Activity;
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

import com.example.herminahealtcenter.Alert.AlertKoneksi;
import com.example.herminahealtcenter.adapter.LabheadAdapter;
import com.example.herminahealtcenter.model.Historylabheader;
import com.example.herminahealtcenter.model.HistorylabheaderResponse;
import com.example.herminahealtcenter.model.MetaData;
import com.example.herminahealtcenter.rest.ApiClient;
import com.example.herminahealtcenter.rest.ApiInterface;
import com.example.herminahealtcenter.utils.SessionsManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaboratoriumHeaderActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    SessionsManager sessionsManager;
    String norm;
    Boolean on = true;
    private Activity activity;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView backLaboratorium;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorium_header);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (on) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayoutlab);
        backLaboratorium = findViewById(R.id.IVbacklaboratorium);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                refreshData();
            }
        });
        sessionsManager = new SessionsManager(getApplicationContext());
        norm = sessionsManager.getUserNomr();

        backLaboratorium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    public void refreshData() {

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.labheader_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.createService(ApiInterface.class, "admin", "h3rm1n4c4r3");


        Call<HistorylabheaderResponse> call = apiService.hlabhead(norm);

        call.enqueue(new Callback<HistorylabheaderResponse>() {
            @Override
            public void onResponse(Call<HistorylabheaderResponse> call, Response<HistorylabheaderResponse> response) {
                MetaData code = response.body().getMetaData();
                String MetaCode = code.getCode();
                String MetaMessage = code.getMessage();
                Log.d("Retrofit Post", "Jumlah data Kontak: " + MetaCode);
                final List<Historylabheader> historylabheaders = response.body().getHistorylabheader();
                recyclerView.setAdapter(new LabheadAdapter(historylabheaders, R.layout.labheader_list_item_layout, getApplicationContext()));
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<HistorylabheaderResponse> call, Throwable t) {
//                Toast.makeText(LaboratoriumHeaderActivity.this, "gagal", Toast.LENGTH_LONG).show();
                AlertKoneksi alert = new AlertKoneksi();
                alert.showDialog(LaboratoriumHeaderActivity.this,"Mohon maaf , sedang dalam perbaikan");
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