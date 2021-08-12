package com.example.herminahealtcenter.detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.herminahealtcenter.Alert.AlertKoneksi;
import com.example.herminahealtcenter.R;
import com.example.herminahealtcenter.adapter.LabdeatailAdapter;
import com.example.herminahealtcenter.model.Historylabdetail;
import com.example.herminahealtcenter.model.HistorylabdetailResponse;
import com.example.herminahealtcenter.model.MetaData;
import com.example.herminahealtcenter.model.Testindonesium;
import com.example.herminahealtcenter.rest.ApiClient;
import com.example.herminahealtcenter.rest.ApiInterface;
import com.example.herminahealtcenter.utils.SessionsManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaboratoriumDetailActivity extends AppCompatActivity  implements SwipeRefreshLayout.OnRefreshListener{

    SessionsManager sessionsManager;
    String norm, notransaksi, tgllahir, nobukti, nocm, nmpasien, umur, dokternama, tglsampling, jamsampling, shift ;
    Boolean on = true;
    private SwipeRefreshLayout swipeRefreshLayoutlabdet;
    private ImageView backLaboratoriumdet;
    TextView textViewnotranskasi,textViewnorekammedis, textViewnmpasien, textViewtgllahir, textViewumur, textViewnamadokter, textViewtglsampling, textViewjamsampling, textViewshift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorium_detail);
        if (on) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


        textViewnotranskasi = (TextView) findViewById(R.id.TVnotransaksiLabdetail);
        textViewnorekammedis = (TextView) findViewById(R.id.TVnormlabdetail) ;
        textViewnmpasien = (TextView) findViewById(R.id.TVnamapasienlabdetail);
        textViewtgllahir = (TextView) findViewById(R.id.TVtgllhirlabdetail) ;
        textViewumur = (TextView) findViewById(R.id.TVumurlabdetail);
        textViewnamadokter = (TextView) findViewById(R.id.TVnmdokterlabdetail) ;
        textViewtglsampling = (TextView) findViewById(R.id.TVtglsamplinglabdetail);
        textViewjamsampling = (TextView) findViewById(R.id.TVjamsamplinglabdetail) ;
        textViewshift = (TextView) findViewById(R.id.TVshiftlabdetail) ;

        swipeRefreshLayoutlabdet = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayoutlabdet);
        backLaboratoriumdet = findViewById(R.id.IVbacklaboratoriumdet);
        swipeRefreshLayoutlabdet.setOnRefreshListener(this);
        swipeRefreshLayoutlabdet.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayoutlabdet.setRefreshing(true);
                refreshData();
            }
        });
        sessionsManager = new SessionsManager(getApplicationContext());
        norm = sessionsManager.getUserNomr();

        backLaboratoriumdet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void refreshData() {

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.labdetail_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.createService(ApiInterface.class, "admin", "h3rm1n4c4r3");


        Call<HistorylabdetailResponse> call = apiService.hlabdet(getNotransaksi());

        call.enqueue(new Callback<HistorylabdetailResponse>() {
            @Override
            public void onResponse(Call<HistorylabdetailResponse> call, Response<HistorylabdetailResponse> response) {
                MetaData code = response.body().getMetaData();
                String message = code.getMessage();
                String MetaCode = code.getCode();
                Historylabdetail historylabdetailResponse = response.body().getHistorylabdetail();
                nobukti = historylabdetailResponse.getNotransaksi();
                nocm = historylabdetailResponse.getPatientid();
                nmpasien = historylabdetailResponse.getPatientnama();
                tgllahir = historylabdetailResponse.getTgllahir();
                umur = historylabdetailResponse.getUmur();
                dokternama = historylabdetailResponse.getDokternama();
                tglsampling = historylabdetailResponse.getTglsampling();
                jamsampling = historylabdetailResponse.getJamsampling();
                shift = historylabdetailResponse.getShift();

                if (MetaCode.equals("200")){
                    swipeRefreshLayoutlabdet.setRefreshing(false);
                    textViewnotranskasi.setText(historylabdetailResponse.getNotransaksi());
                    textViewnorekammedis.setText(historylabdetailResponse.getPatientid());
                    textViewnmpasien.setText(historylabdetailResponse.getPatientnama());
                    textViewtgllahir.setText(historylabdetailResponse.getTgllahir());
                    textViewumur.setText(historylabdetailResponse.getUmur());
                    textViewnamadokter.setText(historylabdetailResponse.getDokternama());
                    textViewtglsampling.setText(historylabdetailResponse.getTglsampling());
                    textViewjamsampling.setText(historylabdetailResponse.getJamsampling());
                    textViewshift.setText(historylabdetailResponse.getShift());


                    Log.d("Retrofit Post", "Tanggal lahir data Kontak: " + tgllahir);
                    final List<Testindonesium> testindonesiumList = response.body().getHistorylabdetail().getTestindonesia();
                    recyclerView.setAdapter(new LabdeatailAdapter(testindonesiumList,R.layout.labdetail_list_item_layout, getApplicationContext()));
                } else {
                    AlertKoneksi alert = new AlertKoneksi();
                    alert.showDialog(LaboratoriumDetailActivity.this,message);
                    swipeRefreshLayoutlabdet.setRefreshing(false);
                }

            }

            @Override
            public void onFailure(Call<HistorylabdetailResponse> call, Throwable t) {
                AlertKoneksi alert = new AlertKoneksi();
                alert.showDialog(LaboratoriumDetailActivity.this,"Mohon maaf , sedang dalam perbaikan");
                swipeRefreshLayoutlabdet.setRefreshing(false);
            }
        });

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private String getNotransaksi() {
        Intent mIntent = getIntent();
        Bundle mBundle = mIntent.getExtras();
        if (mBundle != null) {
            notransaksi = mBundle.getString("notransaksi");
        }
        return notransaksi;
    }
}