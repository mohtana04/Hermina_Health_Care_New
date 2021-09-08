package com.rsherminasamarinda.herminahealtcenter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.rsherminasamarinda.herminahealtcenter.Alert.AlertKoneksi;
import com.rsherminasamarinda.herminahealtcenter.adapter.FisheadAdapter;
import com.rsherminasamarinda.herminahealtcenter.model.Historyfisioheader;
import com.rsherminasamarinda.herminahealtcenter.model.HistoryfisioheaderResponse;
import com.rsherminasamarinda.herminahealtcenter.model.MetaData;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiClient;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiInterface;
import com.rsherminasamarinda.herminahealtcenter.utils.SessionsManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FisioHeaderActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    SessionsManager sessionsManager;
    String norm;
    Boolean on = true;
    private SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    FisheadAdapter fisheadAdapter;
    EditText editTextSearch;
    List<Historyfisioheader> historyfisioheaders;
    private ImageView backFisio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisio_header);
        sessionsManager = new SessionsManager(getApplicationContext());
        norm = sessionsManager.getUserNomr();
        if (on) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        recyclerView = (RecyclerView) findViewById(R.id.fisheader_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backFisio = findViewById(R.id.IVbackfisio);
        editTextSearch = (EditText) findViewById(R.id.ETpencarianFis);
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
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayoutfis);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                refreshData();
            }
        });


        backFisio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void filter(String text) {
        List<Historyfisioheader> filteredList = new ArrayList<>();
        for (Historyfisioheader item : historyfisioheaders) {
            if (item.getTgltransaksi().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }if (item.getDoktername().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            } if (item.getNobuktitransaksi().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        fisheadAdapter.filterListfis(filteredList);
    }

    public void refreshData() {

        ApiInterface apiService =
                ApiClient.createService(ApiInterface.class, "admin", "h3rm1n4c4r3");

        Call<HistoryfisioheaderResponse> call= apiService.hfishead(norm);

        call.enqueue(new Callback<HistoryfisioheaderResponse>() {
            @Override
            public void onResponse(Call<HistoryfisioheaderResponse> call, Response<HistoryfisioheaderResponse> response) {
                MetaData code = response.body().getMetaData();
                String MetaCode = code.getCode();
                String MetaMessage = code.getMessage();
                Log.d("Retrofit Post", "Jumlah data Kontak: " + MetaCode);
                historyfisioheaders = response.body().getHistoryfisioheader();
//                recyclerView.setAdapter(new FisheadAdapter(historyfisioheaders, R.layout.fisheader_list_item_layout, getApplicationContext()));
                fisheadAdapter = new FisheadAdapter(historyfisioheaders, R.layout.fisheader_list_item_layout,getApplicationContext());
                recyclerView.setAdapter(fisheadAdapter);
                swipeRefreshLayout.setRefreshing(false);
                if (MetaCode.equals("201")){
                    AlertKoneksi alert = new AlertKoneksi();
                    alert.showDialog(FisioHeaderActivity.this,"Belum Ada Riwayat Kunjungan Anda");
                    swipeRefreshLayout.setRefreshing(false);
                    editTextSearch.setEnabled(false);
                    editTextSearch.setVisibility(View.GONE);
                } else {
                    editTextSearch.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<HistoryfisioheaderResponse> call, Throwable t) {
                AlertKoneksi alert = new AlertKoneksi();
                alert.showDialog(FisioHeaderActivity.this,"Mohon maaf , sedang dalam perbaikan");
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