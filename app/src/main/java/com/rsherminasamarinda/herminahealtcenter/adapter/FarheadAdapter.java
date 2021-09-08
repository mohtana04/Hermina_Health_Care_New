package com.rsherminasamarinda.herminahealtcenter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rsherminasamarinda.herminahealtcenter.R;
import com.rsherminasamarinda.herminahealtcenter.detail.FarmasiDetailActivity;
import com.rsherminasamarinda.herminahealtcenter.model.Historyfarheader;

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
        String notransaksi = historyfarheaders.get(position).getNobuktitransaksi();
        holder.nobuktitransaksi.setText(historyfarheaders.get(position).getNobuktitransaksi());
        holder.tgltransaksi.setText(historyfarheaders.get(position).getTanggal());
        holder.typeketerangan.setText(historyfarheaders.get(position).getTypeketerangan());
        holder.dotkternama.setText(historyfarheaders.get(position).getDoktername());
        holder.detailfar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(holder.detaillab.getContext(), "s" ,Toast.LENGTH_LONG).show();
                Intent intent = new Intent (holder.detailfar.getContext(), FarmasiDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("notransaksi", notransaksi);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyfarheaders == null ? 0 : historyfarheaders.size();
    }

    public void filterListfar(List<Historyfarheader> filteredList) {
        historyfarheaders = filteredList;
        notifyDataSetChanged();
    }

    public class FarheadAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nobuktitransaksi, tgltransaksi, patientnama, typeketerangan, dotkternama;
        Button detailfar;
        public FarheadAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            nobuktitransaksi = (TextView) itemView.findViewById(R.id.tvnotransaksifar);
            tgltransaksi = (TextView) itemView.findViewById(R.id.tvtgltransaksifar);
            typeketerangan = (TextView) itemView.findViewById(R.id.tvtipetransaksifar);
            dotkternama = (TextView) itemView.findViewById(R.id.tvdokternyafar);
            detailfar = (Button) itemView. findViewById(R.id.ACBlihatdetailfar);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
