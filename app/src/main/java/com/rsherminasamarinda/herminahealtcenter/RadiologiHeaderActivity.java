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
import com.rsherminasamarinda.herminahealtcenter.adapter.RadheadAdapter;
import com.rsherminasamarinda.herminahealtcenter.model.Historyradheader;
import com.rsherminasamarinda.herminahealtcenter.model.HistoryradheaderResponse;
import com.rsherminasamarinda.herminahealtcenter.model.MetaData;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiClient;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiInterface;
import com.rsherminasamarinda.herminahealtcenter.utils.SessionsManager;

import java.util.ArrayList;
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
    EditText editTextSearch;
    RecyclerView recyclerView;
    RadheadAdapter radheadAdapter;
    List<Historyradheader> historyradheaderList;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiologi_header);

        sessionsManager = new SessionsManager(getApplicationContext());
        norm = sessionsManager.getUserNomr();

        if (on) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        recyclerView = (RecyclerView) findViewById(R.id.rvradiologiheader);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

        editTextSearch = (EditText) findViewById(R.id.ETpencarianRad) ;
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

    }


    private void filter(String text) {
        List<Historyradheader> filteredList = new ArrayList<>();
        for (Historyradheader item : historyradheaderList) {
            if (item.getTgltransaksi().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }if (item.getDokternama().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            } if (item.getNobuktitransaksi().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        radheadAdapter.filterListrad(filteredList);
    }

    public void refreshData() {

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
                historyradheaderList = response.body().getHistoryradheader();
//                recyclerView.setAdapter(new RadheadAdapter(historyradheaderList, R.layout.radheader_list_item_layout, getApplicationContext()));
                radheadAdapter = new RadheadAdapter(historyradheaderList,R.layout.radheader_list_item_layout,getApplicationContext());
                recyclerView.setAdapter(radheadAdapter);
                swipeRefreshLayout.setRefreshing(false);

                if (MetaCode.equals("201")){
                    AlertKoneksi alert = new AlertKoneksi();
                    alert.showDialog(RadiologiHeaderActivity.this,"Belum Ada Riwayat Kunjungan Anda");
                    swipeRefreshLayout.setRefreshing(false);
                    editTextSearch.setEnabled(false);
                    editTextSearch.setVisibility(View.GONE);
                } else {
                    editTextSearch.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<HistoryradheaderResponse> call, Throwable t) {
//                Toast.makeText(RadiologiHeaderActivity.this, "gagal", Toast.LENGTH_LONG).show();
                AlertKoneksi alert = new AlertKoneksi();
                alert.showDialog(RadiologiHeaderActivity.this,"Cek koneksi anda");
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