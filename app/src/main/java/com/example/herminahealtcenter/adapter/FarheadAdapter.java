package com.example.herminahealtcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.herminahealtcenter.R;
import com.example.herminahealtcenter.model.Historyfarheader;

import java.util.List;

public class FarheadAdapter extends RecyclerView.Adapter<FarheadAdapter.FarheadAdapterViewHolder> {


    public List<Historyfarheader> historyfarheaders;
    public Context context;
    private int rowLayout;

    public FarheadAdapter(List<Historyfarheader> historyfarheaders, int rowLayout, Context context){
        this.historyfarheaders = historyfarheaders;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public FarheadAdapter.FarheadAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
       return new FarheadAdapter.FarheadAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FarheadAdapter.FarheadAdapterViewHolder holder, int position) {
        holder.nobuktitransaksi.setText(historyfarheaders.get(position).getNobuktitransaksi());
        holder.tgltransaksi.setText(historyfarheaders.get(position).getTanggal());
        holder.typeketerangan.setText(historyfarheaders.get(position).getTypeketerangan());
        holder.dotkternama.setText(historyfarheaders.get(position).getDoktername());
    }

    @Override
    public int getItemCount() {
        return historyfarheaders == null ? 0 : historyfarheaders.size();
    }

    public class FarheadAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nobuktitransaksi, tgltransaksi, patientnama, typeketerangan, dotkternama;

        public FarheadAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            nobuktitransaksi = (TextView) itemView.findViewById(R.id.tvnotransaksifar);
            tgltransaksi = (TextView) itemView.findViewById(R.id.tvtgltransaksifar);
            typeketerangan = (TextView) itemView.findViewById(R.id.tvtipetransaksifar);
            dotkternama = (TextView) itemView.findViewById(R.id.tvdokternyafar);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
