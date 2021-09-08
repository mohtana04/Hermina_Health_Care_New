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
import com.rsherminasamarinda.herminahealtcenter.adapter.KtkheadAdapter;
import com.rsherminasamarinda.herminahealtcenter.model.Historyktkheader;
import com.rsherminasamarinda.herminahealtcenter.model.HistoryktkheaderResponse;
import com.rsherminasamarinda.herminahealtcenter.model.MetaData;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiClient;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiInterface;
import com.rsherminasamarinda.herminahealtcenter.utils.SessionsManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KtkHeaderActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    SessionsManager sessionsManager;
    String norm;
    Boolean on = true;
    private SwipeRefreshLayout swipeRefreshLayout;
    EditText editTextSearch;
    RecyclerView recyclerView;
    List<Historyktkheader> historyktkheaders;
    KtkheadAdapter ktkheadAdapter;
    ImageView backKtk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ktk_header);

        sessionsManager = new SessionsManager(getApplicationContext());
        norm = sessionsManager.getUserNomr();
        if (on) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        recyclerView = (RecyclerView) findViewById(R.id.ktkheader_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backKtk = findViewById(R.id.IVbackktk);

        editTextSearch = (EditText) findViewById(R.id.ETpencarianKtk);
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

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayoutktk);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                refreshData();
            }
        });

        backKtk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void filter(String text) {
        List<Historyktkheader> filteredList = new ArrayList<>();
        for (Historyktkheader item : historyktkheaders) {
            if (item.getTgltransaksi().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
            if (item.getDoktername().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
            if (item.getNobuktitransaksi().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        ktkheadAdapter.filterListktk(filteredList);
    }

    public void refreshData() {


        ApiInterface apiService =
                ApiClient.createService(ApiInterface.class, "admin", "h3rm1n4c4r3");


        Call<HistoryktkheaderResponse> call = apiService.hktkhead(norm);

        call.enqueue(new Callback<HistoryktkheaderResponse>() {
            @Override
            public void onResponse(Call<HistoryktkheaderResponse> call, Response<HistoryktkheaderResponse> response) {
                MetaData code = response.body().getMetaData();
                String MetaCode = code.getCode();
                String MetaMessage = code.getMessage();
                Log.d("Retrofit Post", "Jumlah data Kontak: " + MetaCode);
                historyktkheaders = response.body().getHistoryktkheader();
//                recyclerView.setAdapter(new KtkheadAdapter(historyktkheaders, R.layout.ktkheader_list_item_layout, getApplicationContext()));
                ktkheadAdapter = new KtkheadAdapter(historyktkheaders, R.layout.ktkheader_list_item_layout, getApplicationContext());
                recyclerView.setAdapter(ktkheadAdapter);
                swipeRefreshLayout.setRefreshing(false);
                if (MetaCode.equals("201")) {
                    AlertKoneksi alert = new AlertKoneksi();
                    alert.showDialog(KtkHeaderActivity.this, "Belum Ada Riwayat Kunjungan Anda");
                    swipeRefreshLayout.setRefreshing(false);
                    editTextSearch.setEnabled(false);
                    editTextSearch.setVisibility(View.GONE);
                } else {
                    editTextSearch.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<HistoryktkheaderResponse> call, Throwable t) {
                AlertKoneksi alert = new AlertKoneksi();
                alert.showDialog(KtkHeaderActivity.this, "Mohon maaf , sedang dalam perbaikan");
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