package com.rsherminasamarinda.herminahealtcenter;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.rsherminasamarinda.herminahealtcenter.Alert.AlertKoneksi;
import com.rsherminasamarinda.herminahealtcenter.adapter.LabheadAdapter;
import com.rsherminasamarinda.herminahealtcenter.model.Historylabheader;
import com.rsherminasamarinda.herminahealtcenter.model.HistorylabheaderResponse;
import com.rsherminasamarinda.herminahealtcenter.model.MetaData;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiClient;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiInterface;
import com.rsherminasamarinda.herminahealtcenter.utils.SessionsManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaboratoriumHeaderActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    SessionsManager sessionsManager;
    String norm;
    Boolean on = true;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView backLaboratorium;
    EditText editTextSearch;
    RecyclerView recyclerView;
    List<Historylabheader> historylabheaders;
    LabheadAdapter labheadAdapter;


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
        sessionsManager = new SessionsManager(getApplicationContext());
        norm = sessionsManager.getUserNomr();

        recyclerView = (RecyclerView) findViewById(R.id.labheader_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backLaboratorium = findViewById(R.id.IVbacklaboratorium);

        editTextSearch = (EditText) findViewById(R.id.ETpencarianLab);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

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


        backLaboratorium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void filter(String text) {
        List<Historylabheader> filteredList = new ArrayList<>();
        for (Historylabheader item : historylabheaders) {
            if (item.getDokternama().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            } if (item.getNobuktitransaksi().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }  if (item.getJamsampling().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            } if (item.getTypeketerangan().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        labheadAdapter.filterListlab(filteredList);
    }

    public void refreshData() {

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
                historylabheaders = response.body().getHistorylabheader();
//                recyclerView.setAdapter(new LabheadAdapter(historylabheaders, R.layout.labheader_list_item_layout, getApplicationContext()));
                labheadAdapter = new LabheadAdapter(historylabheaders,R.layout.labheader_list_item_layout, getApplicationContext());
                recyclerView.setAdapter(labheadAdapter);
                swipeRefreshLayout.setRefreshing(false);
                if (MetaCode.equals("201")){
                    AlertKoneksi alert = new AlertKoneksi();
                    alert.showDialog(LaboratoriumHeaderActivity.this,"Belum Ada Riwayat Kunjungan Anda");
                    swipeRefreshLayout.setRefreshing(false);
                    editTextSearch.setEnabled(false);
                    editTextSearch.setVisibility(View.GONE);
                } else {
                    editTextSearch.setEnabled(true);
                }
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