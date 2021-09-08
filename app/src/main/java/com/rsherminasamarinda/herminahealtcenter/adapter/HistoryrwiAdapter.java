package com.rsherminasamarinda.herminahealtcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rsherminasamarinda.herminahealtcenter.R;
import com.rsherminasamarinda.herminahealtcenter.model.Historyrwi;

import java.util.List;

public class HistoryrwiAdapter extends RecyclerView.Adapter<HistoryrwiAdapter.HistoryrwiAdapterViewHolder> {

    public List<Historyrwi> historyrwis;
    public Context context;
    private int rowLayout;


    public HistoryrwiAdapter(List<Historyrwi> historyrwis, int rowLayout, Context context){
        this.historyrwis = historyrwis;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public HistoryrwiAdapter.HistoryrwiAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new HistoryrwiAdapter.HistoryrwiAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryrwiAdapterViewHolder holder, int position) {
        holder.noregisrwi.setText(historyrwis.get(position).getNoregistrasi());
        holder.doktername.setText(historyrwis.get(position).getDoktername());
        holder.tglpulang.setText(historyrwis.get(position).getTglpulang());
        holder.asuransi.setText(historyrwis.get(position).getsNama());
        holder.tglregisrwi.setText(historyrwis.get(position).getTglregistrasi());
        holder.umur.setText(historyrwis.get(position).getUmur());
        holder.keterangan.setText(historyrwis.get(position).getKeterangan());
        holder.keterangan2.setText(historyrwis.get(position).getKeterangan2());
    }

    @Override
    public int getItemCount() {
        return historyrwis == null ? 0 :historyrwis.size();
    }

    public void filterListrwi(List<Historyrwi> filteredList) {
        historyrwis = filteredList;
        notifyDataSetChanged();
    }

    public class HistoryrwiAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView noregisrwi , tglregisrwi, tglpulang, umur, keterangan, keterangan2, doktername, asuransi;

        public HistoryrwiAdapterViewHolder(View itemView) {
            super(itemView);

            noregisrwi = (TextView) itemView.findViewById(R.id.tvnoregistrasirwi);
            tglregisrwi = (TextView) itemView.findViewById(R.id.tvtglregistrasirwi);
            tglpulang = (TextView) itemView.findViewById(R.id.tvtglpulangrwi);
            umur = (TextView) itemView.findViewById(R.id.tvusiarwi);
            keterangan = (TextView) itemView.findViewById(R.id.tvketeranganrwi);
            keterangan2 = (TextView) itemView.findViewById(R.id.tvketeranganrwi2);
            doktername = (TextView) itemView.findViewById(R.id.tvnamadokterrwi);
            asuransi = (TextView) itemView.findViewById(R.id.tvasuransirwi);

        }
    }
}
