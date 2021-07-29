package com.example.herminahealtcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.herminahealtcenter.R;
import com.example.herminahealtcenter.model.Historylabheader;

import java.util.List;

public class LabheadAdapter extends RecyclerView.Adapter<LabheadAdapter.LabheaderAdapterViewHolder>{

    public List<Historylabheader> historylabheaders;
    public Context context;
    private int rowLayout;

    public LabheadAdapter (List<Historylabheader> historylabheaders, int rowLayout, Context context){
        this.historylabheaders = historylabheaders;
        this.rowLayout = rowLayout;
        this.context = context;
    }


    @Override
    public LabheadAdapter.LabheaderAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new LabheadAdapter.LabheaderAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LabheadAdapter.LabheaderAdapterViewHolder holder, int position) {
        holder.nobuktitransaksi.setText(historylabheaders.get(position).getNobuktitransaksi());
        holder.tgltransaksi.setText(historylabheaders.get(position).getTgltransaksi());
        holder.patientnama.setText(historylabheaders.get(position).getPatientnama());
        holder.typeketerangan.setText(historylabheaders.get(position).getTypeketerangan());
        holder.dotkternama.setText(historylabheaders.get(position).getDokternama());
    }

    @Override
    public int getItemCount() {
        return historylabheaders.size();
    }

    public class LabheaderAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nobuktitransaksi, tgltransaksi, patientnama, typeketerangan, dotkternama;
        public LabheaderAdapterViewHolder(View view) {
            super(view);
            nobuktitransaksi = (TextView) view.findViewById(R.id.tvnotransaksilab);
            tgltransaksi = (TextView) view.findViewById(R.id.tvtgltransaksilab);
            patientnama = (TextView) view.findViewById(R.id.tvnamapasienlab);
            typeketerangan = (TextView) view.findViewById(R.id.tvtipetransaksilab);
            dotkternama = (TextView) view.findViewById(R.id.tvdokternyalab);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
