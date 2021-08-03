package com.example.herminahealtcenter.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.herminahealtcenter.R;
import com.example.herminahealtcenter.adapter.HistoryrwjAdapter;
import com.example.herminahealtcenter.model.Historyrwj;
import com.example.herminahealtcenter.model.HistoryrwjResponse;
import com.example.herminahealtcenter.model.MetaData;
import com.example.herminahealtcenter.rest.ApiClient;
import com.example.herminahealtcenter.rest.ApiInterface;
import com.example.herminahealtcenter.utils.SessionsManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RiwayatRwjFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RiwayatRwjFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private SwipeRefreshLayout swipeRefreshLayout;
    SessionsManager sessionsManager;
    String nomr ;

    public RiwayatRwjFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RiwayatRwj.
     */
    // TODO: Rename and change types and number of parameters
    public static RiwayatRwjFragment newInstance(String param1, String param2) {
        RiwayatRwjFragment fragment = new RiwayatRwjFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sessionsManager = new SessionsManager(getContext());
        nomr = sessionsManager.getUserName();
        final View view = inflater.inflate(R.layout.fragment_riwayat_rwj, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayoutrwj);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                refreshData(view);
            }
        });
        return view;
    }

    private void refreshData(View view) {

        final RecyclerView rView = (RecyclerView) view.findViewById(R.id.rvriwayatrwj);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));


        ApiInterface apiService =
                ApiClient.createService(ApiInterface.class, "admin", "h3rm1n4c4r3");


        Call<HistoryrwjResponse> call = apiService.hrwj(nomr);
        call.enqueue(new Callback<HistoryrwjResponse>() {
            @Override
            public void onResponse(Call<HistoryrwjResponse> call, Response<HistoryrwjResponse> response) {
                MetaData code = response.body().getMetaData();
                String MetaCode = code.getCode();
                String MetaMessage = code.getMessage();
                Log.d("Retrofit Post", "Jumlah data Kontak: " + MetaCode);
                final List<Historyrwj> historyrwjs = response.body().getHistoryrwj();
                rView.setAdapter(new HistoryrwjAdapter(historyrwjs,R.layout.historyrwj_list_item_layout,getContext()));
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<HistoryrwjResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    @Override
    public void onRefresh() {
        refreshData(getView());
    }
}