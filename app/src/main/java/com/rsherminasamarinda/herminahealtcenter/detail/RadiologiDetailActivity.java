package com.rsherminasamarinda.herminahealtcenter.detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.rsherminasamarinda.herminahealtcenter.Alert.AlertKoneksi;
import com.rsherminasamarinda.herminahealtcenter.PdfViewActivity;
import com.rsherminasamarinda.herminahealtcenter.R;
import com.rsherminasamarinda.herminahealtcenter.adapter.RaddetailAdapter;
import com.rsherminasamarinda.herminahealtcenter.model.Historyraddetail;
import com.rsherminasamarinda.herminahealtcenter.model.HistoryraddetailResponse;
import com.rsherminasamarinda.herminahealtcenter.model.MetaData;
import com.rsherminasamarinda.herminahealtcenter.model.Pemeriksaan;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiClient;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiInterface;
import com.rsherminasamarinda.herminahealtcenter.utils.SessionsManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RadiologiDetailActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    SessionsManager sessionsManager;
    String norm, notransaksi, tgllahir, nobukti, nocm, nmpasien, umur, dokterpengirim, tglsampling, jamsampling, shift, tglselesai , penunjang;
    Boolean on = true;
    private SwipeRefreshLayout swipeRefreshLayoutraddet;
    private ImageView backradoratoriumdet;
    TextView textViewnotranskasi, textViewnorekammedis, textViewnmpasien, textViewtgllahir, textViewumur, textViewnamadokter, textViewtglsampling, textViewjamsampling, textViewshift;
//    Bitmap bitmap, scaleBitmap;
    ImageButton buttonCetakrad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiologi_detail);
        if (on) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        //cover header
//        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.headerpdf);
//        scaleBitmap = Bitmap.createScaledBitmap(bitmap, 1200, 518, false);

        textViewnotranskasi = (TextView) findViewById(R.id.TVnotransaksiraddetail);
        textViewnorekammedis = (TextView) findViewById(R.id.TVnormraddetail);
        textViewnmpasien = (TextView) findViewById(R.id.TVnamapasienraddetail);
        textViewtgllahir = (TextView) findViewById(R.id.TVtgllhirraddetail);
        textViewumur = (TextView) findViewById(R.id.TVumurraddetail);
        textViewnamadokter = (TextView) findViewById(R.id.TVnmdokterraddetail);
        textViewtglsampling = (TextView) findViewById(R.id.TVtglsamplingraddetail);
//        textViewjamsampling = (TextView) findViewById(R.id.TVjamsamplingraddetail);
        textViewshift = (TextView) findViewById(R.id.TVshiftraddetail);
        buttonCetakrad = (ImageButton) findViewById(R.id.BTNcetakraddet);

        swipeRefreshLayoutraddet = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayoutraddet);
        backradoratoriumdet = findViewById(R.id.IVbackRadiologidet);
        swipeRefreshLayoutraddet.setOnRefreshListener(this);
        swipeRefreshLayoutraddet.post(new Runnable() {
            @Override
            public void run() {
                refreshData();
            }
        });

        sessionsManager = new SessionsManager(getApplicationContext());
        norm = sessionsManager.getUserNomr();

        backradoratoriumdet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cetakPdf();
    }


    public void refreshData(){
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.raddetail_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.createService(ApiInterface.class, "admin", "h3rm1n4c4r3");

        Call<HistoryraddetailResponse> call = apiService.hraddet(getNotransaksi());
        call.enqueue(new Callback<HistoryraddetailResponse>() {
            @Override
            public void onResponse(Call<HistoryraddetailResponse> call, Response<HistoryraddetailResponse> response) {
                MetaData code = response.body().getMetaData();
                String message = code.getMessage();
                String MetaCode = code.getCode();

                Historyraddetail historyraddetail = response.body().getHistoryraddetail();
                nobukti = historyraddetail.getNotransaksi();
                nocm = historyraddetail.getPatientid();
                nmpasien = historyraddetail.getPatientnama();
                tgllahir = historyraddetail.getTgllahir();
                umur = historyraddetail.getUmur();
                dokterpengirim = historyraddetail.getDokterpengirim();
                shift = historyraddetail.getShift();
                tglselesai = historyraddetail.getTglselesai();

                if(MetaCode.equals("200")){
                    swipeRefreshLayoutraddet.setRefreshing(false);
                    textViewnotranskasi.setText(historyraddetail.getNotransaksi());
                    textViewnorekammedis.setText(historyraddetail.getPatientid());
                    textViewnmpasien.setText(historyraddetail.getPatientnama());
                    textViewtgllahir.setText(historyraddetail.getTgllahir());
                    textViewumur.setText(historyraddetail.getUmur());
                    textViewnamadokter.setText(historyraddetail.getDokterpengirim());
                    textViewtglsampling.setText(historyraddetail.getTglselesai());
//                    textViewjamsampling.setText(historyraddetail.getJamsampling());
                    textViewshift.setText(historyraddetail.getShift());
                    Log.d("Retrofit Post", "Tanggal lahir data Kontak: " + tgllahir);

                    final List<Pemeriksaan> pemeriksaans = response.body().getHistoryraddetail().getPemeriksaan();
                    recyclerView.setAdapter(new RaddetailAdapter(pemeriksaans,R.layout.raddetail_list_item_layout, getApplicationContext()));

                }
            }

            @Override
            public void onFailure(Call<HistoryraddetailResponse> call, Throwable t) {
                AlertKoneksi alert = new AlertKoneksi();
                alert.showDialog(RadiologiDetailActivity.this, "Mohon maaf , sedang dalam perbaikan");
                swipeRefreshLayoutraddet.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        refreshData();
    }

    private void cetakPdf() {

        buttonCetakrad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PdfViewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("penunjang", "radiologi");
                intent.putExtra("notransaksi", notransaksi);
                startActivity(intent);
            }
        });

    }


    private String getNotransaksi() {
        Intent mIntent = getIntent();
        Bundle mBundle = mIntent.getExtras();
        if (mBundle != null) {
            notransaksi = mBundle.getString("notransaksirad");
        }
        return notransaksi;
    }
}