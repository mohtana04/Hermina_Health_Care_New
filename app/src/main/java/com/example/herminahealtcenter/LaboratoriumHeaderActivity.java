package com.example.herminahealtcenter;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class LaboratoriumHeaderActivity extends AppCompatActivity {

    SessionsManager sessionsManager;
    String norm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorium_header);
        sessionsManager = new SessionsManager(getApplicationContext());
        norm = sessionsManager.getUserName();


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
                recyclerView.setAdapter(new LabheadAdapter(historylabheaders,R.layout.labheader_list_item_layout,getApplicationContext()));
            }

            @Override
            public void onFailure(Call<HistorylabheaderResponse> call, Throwable t) {
                Toast.makeText(LaboratoriumHeaderActivity.this, "gagal", Toast.LENGTH_LONG).show();
            }
        });
//        call.enqueue(new Callback<HistorylabheaderResponse>() {
//            @Override
//            public void onResponse(Call<HistorylabheaderResponse> call, Response<HistorylabheaderResponse> response) {
//                MetaData code =response.body().getMetaData();
//                String MetaCode = code.getCode();
//                String MetaMessage = code.getMessage();
//                Log.d("Retrofit Post", "Jumlah data Kontak: " + MetaCode);
//
//                if (MetaCode.equals("200")) {
//                    final List<Historylabheader> historylabheaders = response.body().getHistorylabheader();
//                    recyclerView.setAdapter(new LabheadAdapter(historylabheaders, R.layout.labheader_list_item_layout, getApplicationContext()));
//                } else {
//                    Toast.makeText(LaboratoriumHeaderActivity.this,MetaMessage,Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<HistorylabheaderResponse> call, Throwable t) {
//
//            }
//        });

    }


}