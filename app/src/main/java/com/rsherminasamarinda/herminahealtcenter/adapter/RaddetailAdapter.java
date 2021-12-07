package com.rsherminasamarinda.herminahealtcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rsherminasamarinda.herminahealtcenter.R;
import com.rsherminasamarinda.herminahealtcenter.model.Pemeriksaan;

import java.util.List;

public class RaddetailAdapter extends RecyclerView.Adapter<RaddetailAdapter.RaddetailViewHolder> {

    public List<Pemeriksaan> pemeriksaans;
    public Context context;
    private int rowLayout;

    public RaddetailAdapter (List<Pemeriksaan> pemeriksaans, int rowLayout, Context context){
        this.pemeriksaans = pemeriksaans;
        this.rowLayout = rowLayout;
        this.context = context;

    }

    @NonNull
    @Override
    public RaddetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return  new RaddetailAdapter.RaddetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RaddetailViewHolder holder, int position) {
        holder.pemerikssannama.setText(pemeriksaans.get(position).getPemeriksaannama());
        holder.dokternama.setText(pemeriksaans.get(position).getDokterpemeriksa());
        holder.nofoto.setText(pemeriksaans.get(position).getNofoto());
        holder.catatanhasil.setText(pemeriksaans.get(position).getCatatanhasil());
    }

    @Override
    public int getItemCount() {
        return pemeriksaans == null ? 0 : pemeriksaans.size();
    }

    public class RaddetailViewHolder extends RecyclerView.ViewHolder {

        TextView pemerikssannama, dokternama, nofoto, catatanhasil;

        public RaddetailViewHolder(@NonNull View itemView) {
            super(itemView);

            pemerikssannama = (TextView) itemView.findViewById(R.id.TVpemeriksaannamaraddet);
            dokternama = (TextView) itemView.findViewById(R.id.TVdokterpemeriksaraddet);
            nofoto = (TextView) itemView.findViewById(R.id.TVnofotoraddet);
            catatanhasil = (TextView) itemView.findViewById(R.id.TVcatatanhasilraddet);

        }
    }
}
