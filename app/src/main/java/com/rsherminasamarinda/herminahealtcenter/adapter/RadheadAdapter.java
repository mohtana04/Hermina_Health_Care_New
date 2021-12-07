package com.rsherminasamarinda.herminahealtcenter.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rsherminasamarinda.herminahealtcenter.R;
import com.rsherminasamarinda.herminahealtcenter.detail.RadiologiDetailActivity;
import com.rsherminasamarinda.herminahealtcenter.model.Historyradheader;

import java.util.List;

public class RadheadAdapter extends RecyclerView.Adapter<RadheadAdapter.RadheaderAdapterViewHolder> {

    public List<Historyradheader> historyradheaders;
    public Context context;
    private int rowLayout;
    String notransaksirad;

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
    public void onBindViewHolder(RadheadAdapter.RadheaderAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        notransaksirad = historyradheaders.get(position).getNobuktitransaksi();
        holder.nobuktitransaksi.setText(historyradheaders.get(position).getNobuktitransaksi());
        holder.tgltransaksi.setText(historyradheaders.get(position).getTgltransaksi());
        holder.typeketerangan.setText(historyradheaders.get(position).getTypeketerangan());
        holder.dotkternama.setText(historyradheaders.get(position).getDokternama());
        holder.detailrad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(holder.nobuktitransaksi.getContext(), historyradheaders.get(position).getNobuktitransaksi() ,Toast.LENGTH_LONG).show();
                Intent intent = new Intent (holder.detailrad.getContext(), RadiologiDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("notransaksirad", historyradheaders.get(position).getNobuktitransaksi());
                context.startActivity(intent);
            }
        });
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
        Button detailrad;

        public RadheaderAdapterViewHolder(View itemView) {
            super(itemView);
            nobuktitransaksi = (TextView) itemView.findViewById(R.id.tvnotransaksirad);
            tgltransaksi = (TextView) itemView.findViewById(R.id.tvtgltransaksirad);
            typeketerangan = (TextView) itemView.findViewById(R.id.tvtipetransaksirad);
            dotkternama = (TextView) itemView.findViewById(R.id.tvdokternyarad);
            detailrad = (Button) itemView. findViewById(R.id.ACBlihatdetailrad);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
