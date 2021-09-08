package com.rsherminasamarinda.herminahealtcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rsherminasamarinda.herminahealtcenter.R;
import com.rsherminasamarinda.herminahealtcenter.model.Historyradheader;

import java.util.List;

public class RadheadAdapter extends RecyclerView.Adapter<RadheadAdapter.RadheaderAdapterViewHolder> {

    public List<Historyradheader> historyradheaders;
    public Context context;
    private int rowLayout;

    public RadheadAdapter(List<Historyradheader> historyradheaders, int rowLayout, Context context){
        this.historyradheaders = historyradheaders;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public RadheadAdapter.RadheaderAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new RadheadAdapter.RadheaderAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RadheadAdapter.RadheaderAdapterViewHolder holder, int position) {
        holder.nobuktitransaksi.setText(historyradheaders.get(position).getNobuktitransaksi());
        holder.tgltransaksi.setText(historyradheaders.get(position).getTgltransaksi());
        holder.typeketerangan.setText(historyradheaders.get(position).getTypeketerangan());
        holder.dotkternama.setText(historyradheaders.get(position).getDokternama());
    }

    @Override
    public int getItemCount() {
        return historyradheaders == null ? 0 : historyradheaders.size();
    }

    public void filterListrad(List<Historyradheader> filteredList) {
        historyradheaders = filteredList;
        notifyDataSetChanged();
    }

    public class RadheaderAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nobuktitransaksi, tgltransaksi, typeketerangan, dotkternama;

        public RadheaderAdapterViewHolder(View itemView) {
            super(itemView);
            nobuktitransaksi = (TextView) itemView.findViewById(R.id.tvnotransaksirad);
            tgltransaksi = (TextView) itemView.findViewById(R.id.tvtgltransaksirad);
            typeketerangan = (TextView) itemView.findViewById(R.id.tvtipetransaksirad);
            dotkternama = (TextView) itemView.findViewById(R.id.tvdokternyarad);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
