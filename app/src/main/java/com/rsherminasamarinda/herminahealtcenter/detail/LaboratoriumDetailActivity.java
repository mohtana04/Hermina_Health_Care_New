package com.rsherminasamarinda.herminahealtcenter.detail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.rsherminasamarinda.herminahealtcenter.Alert.AlertKoneksi;
import com.rsherminasamarinda.herminahealtcenter.PdfViewActivity;
import com.rsherminasamarinda.herminahealtcenter.R;
import com.rsherminasamarinda.herminahealtcenter.adapter.LabdeatailAdapter;
import com.rsherminasamarinda.herminahealtcenter.model.Historylabdetail;
import com.rsherminasamarinda.herminahealtcenter.model.HistorylabdetailResponse;
import com.rsherminasamarinda.herminahealtcenter.model.MetaData;
import com.rsherminasamarinda.herminahealtcenter.model.Testindonesium;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiClient;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiInterface;
import com.rsherminasamarinda.herminahealtcenter.utils.SessionsManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaboratoriumDetailActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    SessionsManager sessionsManager;
    String norm, notransaksi, tgllahir, nobukti, nocm, nmpasien, umur, dokternama, tglsampling, jamsampling, shift;
    Boolean on = true;
    private SwipeRefreshLayout swipeRefreshLayoutlabdet;
    private ImageView backLaboratoriumdet;
    TextView buttonCetakLab, textViewnotranskasi, textViewnorekammedis, textViewnmpasien, textViewtgllahir, textViewumur, textViewnamadokter, textViewtglsampling, textViewjamsampling, textViewshift;
    Bitmap bitmap, scaleBitmap;

    int pageWidth = 1200;


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

        //cover header
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.headerpdf);
        scaleBitmap = Bitmap.createScaledBitmap(bitmap, 1200, 518, false);

        //permission
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        textViewnotranskasi = (TextView) findViewById(R.id.TVnotransaksiLabdetail);
        textViewnorekammedis = (TextView) findViewById(R.id.TVnormlabdetail);
        textViewnmpasien = (TextView) findViewById(R.id.TVnamapasienlabdetail);
        textViewtgllahir = (TextView) findViewById(R.id.TVtgllhirlabdetail);
        textViewumur = (TextView) findViewById(R.id.TVumurlabdetail);
        textViewnamadokter = (TextView) findViewById(R.id.TVnmdokterlabdetail);
        textViewtglsampling = (TextView) findViewById(R.id.TVtglsamplinglabdetail);
        textViewjamsampling = (TextView) findViewById(R.id.TVjamsamplinglabdetail);
        textViewshift = (TextView) findViewById(R.id.TVshiftlabdetail);
        buttonCetakLab = (TextView) findViewById(R.id.BTNcetaklabdet);

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
 ///sdfafdf
        cetakPdf();
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

                if (MetaCode.equals("200")) {
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
                    recyclerView.setAdapter(new LabdeatailAdapter(testindonesiumList, R.layout.labdetail_list_item_layout, getApplicationContext()));
                } else {
                    AlertKoneksi alert = new AlertKoneksi();
                    alert.showDialog(LaboratoriumDetailActivity.this, message);
                    swipeRefreshLayoutlabdet.setRefreshing(false);
                }

            }

            @Override
            public void onFailure(Call<HistorylabdetailResponse> call, Throwable t) {
                AlertKoneksi alert = new AlertKoneksi();
                alert.showDialog(LaboratoriumDetailActivity.this, "Mohon maaf , sedang dalam perbaikan");
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

    private void cetakPdf() {

        buttonCetakLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ApiInterface apiService =
//                        ApiClient.createService(ApiInterface.class, "admin", "h3rm1n4c4r3");
//
//
//                Call<HistorylabdetailResponse> call = apiService.hlabdet(getNotransaksi());
//                call.enqueue(new Callback<HistorylabdetailResponse>() {
//                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                    @Override
//                    public void onResponse(Call<HistorylabdetailResponse> call, Response<HistorylabdetailResponse> response) {
//                        MetaData code = response.body().getMetaData();
//                        String message = code.getMessage();
//                        String MetaCode = code.getCode();
//                        Historylabdetail historylabdetailResponse = response.body().getHistorylabdetail();
//
//                        nobukti = historylabdetailResponse.getNotransaksi();
//                        nocm = historylabdetailResponse.getPatientid();
//                        nmpasien = historylabdetailResponse.getPatientnama();
//                        tgllahir = historylabdetailResponse.getTgllahir();
//                        umur = historylabdetailResponse.getUmur();
//                        dokternama = historylabdetailResponse.getDokternama();
//                        tglsampling = historylabdetailResponse.getTglsampling();
//                        jamsampling = historylabdetailResponse.getJamsampling();
//                        shift = historylabdetailResponse.getShift();
//
//
//                        String sfilename = nobukti + ".pdf";
//
//                        if (MetaCode.equals("200")) {
//                            load();
//                            PdfDocument pdfDocument = new PdfDocument();
//                            Paint paint = new Paint();
//                            Paint titlePaint = new Paint();
//
//                            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1200, 2010, 1).create();
//                            PdfDocument.Page page = pdfDocument.startPage(pageInfo);
//
//                            Canvas canvas = page.getCanvas();
//                            canvas.drawBitmap(scaleBitmap, 0, 0, paint);
//
//                            titlePaint.setTextAlign(Paint.Align.CENTER);
//                            titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
//                            titlePaint.setColor(Color.WHITE);
//                            titlePaint.setTextSize(70);
//
//                            canvas.drawText("HASIL PEMERIKSAAN LABORATORIUM", pageWidth / 2, 500, titlePaint);
//                            pdfDocument.finishPage(page);
//
//                            File file = new File(Environment.getExternalStorageDirectory(), "/Pesanan.pdf");
//                            try {
//                                pdfDocument.writeTo(new FileOutputStream(file));
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//
//                            pdfDocument.close();
////                            writeFileOnInternalStorage();
////                            Toast.makeText(LaboratoriumDetailActivity.this, "PDF sudah dibuat /" + getFilesDir(), Toast.LENGTH_LONG).show();
//                        } else {
//                            AlertKoneksi alert = new AlertKoneksi();
//                            alert.showDialog(LaboratoriumDetailActivity.this, message);
//                            swipeRefreshLayoutlabdet.setRefreshing(false);
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<HistorylabdetailResponse> call, Throwable t) {
//                        AlertKoneksi alert = new AlertKoneksi();
//                        alert.showDialog(LaboratoriumDetailActivity.this, "Mohon maaf , Cek kembali koneksi anda");
//                        swipeRefreshLayoutlabdet.setRefreshing(false);
//                    }
//                });

                startActivity(new Intent(getApplicationContext(), PdfViewActivity.class));
            }
        });

    }




}