package com.example.herminahealtcenter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.herminahealtcenter.adapter.FisheadAdapter;
import com.example.herminahealtcenter.model.Historyfisioheader;
import com.example.herminahealtcenter.model.HistoryfisioheaderResponse;
import com.example.herminahealtcenter.model.MetaData;
import com.example.herminahealtcenter.rest.ApiClient;
import com.example.herminahealtcenter.rest.ApiInterface;
import com.example.herminahealtcenter.utils.SessionsManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FisioHeaderActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    SessionsManager sessionsManager;
    String norm;
    Boolean on = true;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisio_header);

        if (on) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayoutfis);
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
    }

    public void refreshData() {

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.fisheader_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
                final List<Historyfisioheader> historyfisioheaders = response.body().getHistoryfisioheader();
                recyclerView.setAdapter(new FisheadAdapter(historyfisioheaders, R.layout.fisheader_list_item_layout, getApplicationContext()));
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<HistoryfisioheaderResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public void onRefresh() {
        refreshData();
    }
}