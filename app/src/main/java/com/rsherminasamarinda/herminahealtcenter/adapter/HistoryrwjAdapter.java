package com.rsherminasamarinda.herminahealtcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rsherminasamarinda.herminahealtcenter.R;
import com.rsherminasamarinda.herminahealtcenter.model.Historyrwj;

import java.util.List;

public class HistoryrwjAdapter extends RecyclerView.Adapter<HistoryrwjAdapter.HistoryrwjAdapterViewHolder> {


    public List<Historyrwj> historyrwjs;
    public Context context;
    private int rowLayout;

    public HistoryrwjAdapter(List<Historyrwj> historyrwjs, int rowLayout, Context context){
        this.historyrwjs = historyrwjs;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public HistoryrwjAdapter.HistoryrwjAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new HistoryrwjAdapter.HistoryrwjAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryrwjAdapterViewHolder holder, int position) {
        holder.noregistrasi.setText(historyrwjs.get(position).getNoregistrasi());
        holder.namadokter.setText(historyrwjs.get(position).getDoktername());
        holder.poliklinik.setText(historyrwjs.get(position).getPoliklinikname());
        holder.asuransi.setText(historyrwjs.get(position).getsNama());
        holder.tglregis.setText(historyrwjs.get(position).getTglregistrasi());
    }

    @Override
    public int getItemCount() {
        return historyrwjs == null ? 0 : historyrwjs.size();
    }

    public class HistoryrwjAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView noregistrasi, namadokter, poliklinik, tglregis, asuransi;

        public HistoryrwjAdapterViewHolder(View view) {
            super(view);
            noregistrasi = (TextView) view.findViewById(R.id.tvnoregistrasirwj);
            namadokter = (TextView) view.findViewById(R.id.tvnamadokterrwj);
            tglregis = (TextView) view.findViewById(R.id.tvtglregistrasirwj);
            poliklinik = (TextView) view.findViewById(R.id.tvpolikliniknamerwj);
            asuransi = (TextView) view.findViewById(R.id.tvasuransirwj);
         }
    }
}
