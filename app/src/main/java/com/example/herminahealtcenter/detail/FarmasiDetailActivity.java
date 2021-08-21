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
import com.example.herminahealtcenter.adapter.FardetailAdapter;
import com.example.herminahealtcenter.model.Historyfardetail;
import com.example.herminahealtcenter.model.Historyfardetailresponse;
import com.example.herminahealtcenter.model.MetaData;
import com.example.herminahealtcenter.model.Racikan;
import com.example.herminahealtcenter.model.Resep;
import com.example.herminahealtcenter.rest.ApiClient;
import com.example.herminahealtcenter.rest.ApiInterface;
import com.example.herminahealtcenter.utils.SessionsManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmasiDetailActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    SessionsManager sessionsManager;
    Boolean on = true;
    private SwipeRefreshLayout swipeRefreshLayoutlabdet;
    private ImageView backLaboratoriumdet;
    TextView textViewNoregi, textViewNotrans, textViewNorm, textViewNamapasien, textViewNmdokter, textViewTypeketerangan, textViewShift, textViewPetugas, textViewJam, textViewTanggal;
    String notransaksi, notrans, norm, noregis, nmpasien, nmdokter, typeketerangan, shift, petugas, jam, tanggal;

    RecyclerView recyclerView, recyclerView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmasi_detail);

        if (on) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        textViewNoregi = (TextView) findViewById(R.id.TVnoregistrasifardetail);
        textViewNotrans = (TextView) findViewById(R.id.TVnotransaksifardetail);
        textViewNorm = (TextView) findViewById(R.id.TVnormfardetail);
        textViewNamapasien = (TextView) findViewById(R.id.TVnamapasienfardetail);
        textViewNmdokter = (TextView) findViewById(R.id.TVnmdokterfardetail);
        textViewTypeketerangan = (TextView) findViewById(R.id.TVtypeketeranganfardetail);
        textViewShift = (TextView) findViewById(R.id.TVshiftfardetail);
        textViewJam = (TextView) findViewById(R.id.TVjamfardetail);
        textViewTanggal = (TextView) findViewById(R.id.TVtglfardetail);
        textViewPetugas = (TextView) findViewById(R.id.TVpetugasfardetail);

        swipeRefreshLayoutlabdet = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayoutfardet);
        backLaboratoriumdet = findViewById(R.id.IVbackfarmasidet);
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

        recyclerView = (RecyclerView) findViewById(R.id.fardetail_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        recyclerView1 = (RecyclerView) findViewById(R.id.racikanfardetail_recycleview);
//        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.createService(ApiInterface.class, "admin", "h3rm1n4c4r3");


        Call<Historyfardetailresponse> call = apiService.hfardet(getNotransaksi());

        call.enqueue(new Callback<Historyfardetailresponse>() {
            @Override
            public void onResponse(Call<Historyfardetailresponse> call, Response<Historyfardetailresponse> response) {
                MetaData code = response.body().getMetaData();

                String message = code.getMessage();
                String MetaCode = code.getCode();
                Historyfardetail historyfardetail = response.body().getHistoryfardetail();


                if (MetaCode.equals("200")) {
                    swipeRefreshLayoutlabdet.setRefreshing(false);
                    textViewNoregi.setText(historyfardetail.getNoregistrasi());
                    textViewNotrans.setText(historyfardetail.getNotransaksi());
                    textViewNorm.setText(historyfardetail.getPatientid());
                    textViewNamapasien.setText(historyfardetail.getPatientnama());
                    textViewNmdokter.setText(historyfardetail.getDokterpengirim());
                    textViewTypeketerangan.setText(historyfardetail.getTypeketerangan());
                    textViewShift.setText(historyfardetail.getShift());
                    textViewPetugas.setText(historyfardetail.getPetugas());
                    textViewJam.setText(historyfardetail.getJam());
                    textViewTanggal.setText(historyfardetail.getTglTrans());


                    Log.d("Retrofit Post", "Tanggal lahir data Kontak: " + message);
                    final List<Resep> reseps = response.body().getHistoryfardetail().getResep();
                    int ukuranresep = reseps.size();
                    int fixukuran = ukuranresep - 1;
                    System.out.println("ccc " + ukuranresep);
                    final List<Racikan> racikans = reseps.get(fixukuran).getRacikan();
//                    int ukuran = reseps.get(fixukuran).getRacikan().size();

//                    System.out.println("hello : "+ ukuran + " " + fixukuran);
//                    for (int i = 0; i < ukuran; i++) {
//                        System.out.println("nama obat racikan2 " + i + " : " + racikans.get(i).getObatnamarecikan());
//                    }



//                    if (racikans != null) {
                        recyclerView.setAdapter(new FardetailAdapter(reseps,racikans, R.layout.fardetail_list_resep_layout, getApplicationContext()));
//                        recyclerView1.setAdapter(new FardetailracikanAdapter(racikans, R.layout.fardetail_list_racikan_layout, getApplicationContext()));
//                    }


                } else {
                    AlertKoneksi alert = new AlertKoneksi();
                    alert.showDialog(FarmasiDetailActivity.this, message);
                    swipeRefreshLayoutlabdet.setRefreshing(false);
                }

            }

            @Override
            public void onFailure(Call<Historyfardetailresponse> call, Throwable t) {

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