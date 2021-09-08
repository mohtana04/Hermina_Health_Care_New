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
import com.rsherminasamarinda.herminahealtcenter.adapter.FarheadAdapter;
import com.rsherminasamarinda.herminahealtcenter.model.HistoryFarheaderResponse;
import com.rsherminasamarinda.herminahealtcenter.model.Historyfarheader;
import com.rsherminasamarinda.herminahealtcenter.model.MetaData;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiClient;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiInterface;
import com.rsherminasamarinda.herminahealtcenter.utils.RecyclerTouchListener;
import com.rsherminasamarinda.herminahealtcenter.utils.SessionsManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmasiHeaderActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    SessionsManager sessionsManager;
    String norm;
    Boolean on = true;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView backFarmasi;
    RecyclerView recyclerView;
    EditText editTextSearch;
    List<Historyfarheader> historyfarheaders;
    FarheadAdapter farheadAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmasi_header);
        if (on) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        recyclerView = (RecyclerView) findViewById(R.id.farheader_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backFarmasi = findViewById(R.id.IVbackfarmasi);
        editTextSearch = (EditText) findViewById(R.id.ETpencarianFar) ;
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


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayoutfar);
        backFarmasi = findViewById(R.id.IVbackfarmasi);
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

        backFarmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void filter(String text) {
        List<Historyfarheader> filteredList = new ArrayList<>();
        for (Historyfarheader item : historyfarheaders) {
            if (item.getTanggal().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }if (item.getDoktername().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            } if (item.getNobuktitransaksi().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }  if (item.getTypeketerangan().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        farheadAdapter.filterListfar(filteredList);
    }

    public void refreshData() {


        ApiInterface apiService =
                ApiClient.createService(ApiInterface.class, "admin", "h3rm1n4c4r3");


        Call<HistoryFarheaderResponse> call = apiService.hfarhead(norm);

        call.enqueue(new Callback<HistoryFarheaderResponse>() {
            @Override
            public void onResponse(Call<HistoryFarheaderResponse> call, Response<HistoryFarheaderResponse> response) {
                MetaData code = response.body().getMetaData();
                String MetaCode = code.getCode();
                String MetaMessage = code.getMessage();
                Log.d("Retrofit Post", "Jumlah data Farmasi: " + MetaCode);
                historyfarheaders = response.body().getHistoryfarheader();
                farheadAdapter = new FarheadAdapter(historyfarheaders,R.layout.farheader_list_item_layout,getApplicationContext());
                recyclerView.setAdapter(farheadAdapter);
                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Historyfarheader historyfarheader = historyfarheaders.get(position);
                        String nobukti = historyfarheader.getNobuktitransaksi();
//                        Toast.makeText(getApplicationContext(),nobukti,Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));

//                recyclerView.setAdapter(new FarheadAdapter(historyfarheaders, R.layout.farheader_list_item_layout, getApplicationContext()));
                swipeRefreshLayout.setRefreshing(false);
                if (MetaCode.equals("201")){
                    AlertKoneksi alert = new AlertKoneksi();
                    alert.showDialog(FarmasiHeaderActivity.this,"Belum Ada Riwayat Kunjungan Anda");
                    swipeRefreshLayout.setRefreshing(false);
                    editTextSearch.setEnabled(false);
                    editTextSearch.setVisibility(View.GONE);
                } else {
                    editTextSearch.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<HistoryFarheaderResponse> call, Throwable t) {
                AlertKoneksi alert = new AlertKoneksi();
                alert.showDialog(FarmasiHeaderActivity.this,"Mohon maaf , sedang dalam perbaikan");
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