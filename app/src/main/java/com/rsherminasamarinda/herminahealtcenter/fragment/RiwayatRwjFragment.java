package com.rsherminasamarinda.herminahealtcenter.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.rsherminasamarinda.herminahealtcenter.Alert.AlertKoneksi;
import com.rsherminasamarinda.herminahealtcenter.R;
import com.rsherminasamarinda.herminahealtcenter.adapter.HistoryrwjAdapter;
import com.rsherminasamarinda.herminahealtcenter.model.Historyrwj;
import com.rsherminasamarinda.herminahealtcenter.model.HistoryrwjResponse;
import com.rsherminasamarinda.herminahealtcenter.model.MetaData;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiClient;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiInterface;
import com.rsherminasamarinda.herminahealtcenter.utils.SessionsManager;

import java.util.ArrayList;
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
    List<Historyrwj> historyrwjs;
    SessionsManager sessionsManager;
    String nomr ;
    EditText editTextSearch;
    RecyclerView rView;
    HistoryrwjAdapter mAdapter;

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
        nomr = sessionsManager.getUserNomr();



        final View view = inflater.inflate(R.layout.fragment_riwayat_rwj, container, false);

        rView = (RecyclerView) view.findViewById(R.id.rvriwayatrwj);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));



        editTextSearch = (EditText)  view.findViewById(R.id.svpencarianrwj);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayoutrwj);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                refreshData(view);
            }
        });

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
        return view;
    }

    private void filter(String text) {
        List<Historyrwj> filteredList = new ArrayList<>();
        for (Historyrwj item : historyrwjs) {
            if (item.getTglregistrasi().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }if (item.getDoktername().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            } if (item.getNoregistrasi().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        mAdapter.filterList(filteredList);
    }


    private void refreshData(View view) {

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
                historyrwjs = response.body().getHistoryrwj();
//                rView.setAdapter(new HistoryrwjAdapter(historyrwjs,R.layout.historyrwj_list_item_layout,getContext()));
                 mAdapter = new HistoryrwjAdapter(historyrwjs,R.layout.historyrwj_list_item_layout,getContext());
                 rView.setAdapter(mAdapter);
                swipeRefreshLayout.setRefreshing(false);
                if (MetaCode.equals("201")){
                    AlertKoneksi alert = new AlertKoneksi();
                    alert.showDialog(getActivity(),"Belum Ada Riwayat Kunjungan Anda");
                    swipeRefreshLayout.setRefreshing(false);
                    editTextSearch.setEnabled(false);
                    editTextSearch.setVisibility(View.GONE);
                } else {
                    editTextSearch.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<HistoryrwjResponse> call, Throwable t) {
                AlertKoneksi alert = new AlertKoneksi();
                alert.showDialog(getActivity(),"Cek koneksi anda");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    @Override
    public void onRefresh() {
        refreshData(getView());
    }
}