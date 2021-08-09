package com.example.herminahealtcenter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.herminahealtcenter.adapter.KtkheadAdapter;
import com.example.herminahealtcenter.model.Historyktkheader;
import com.example.herminahealtcenter.model.HistoryktkheaderResponse;
import com.example.herminahealtcenter.model.MetaData;
import com.example.herminahealtcenter.rest.ApiClient;
import com.example.herminahealtcenter.rest.ApiInterface;
import com.example.herminahealtcenter.utils.SessionsManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KtkHeaderActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    SessionsManager sessionsManager;
    String norm;
    Boolean on = true;
    private SwipeRefreshLayout swipeRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ktk_header);
        if (on) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayoutktk);
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

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ktkheader_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
                final List<Historyktkheader> historyktkheaders = response.body().getHistoryktkheader();
                recyclerView.setAdapter(new KtkheadAdapter(historyktkheaders, R.layout.ktkheader_list_item_layout, getApplicationContext()));
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<HistoryktkheaderResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void onRefresh() {

    }
}