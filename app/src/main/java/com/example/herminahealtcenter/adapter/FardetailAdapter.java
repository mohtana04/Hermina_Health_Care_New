package com.example.herminahealtcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.herminahealtcenter.R;
import com.example.herminahealtcenter.model.Racikan;
import com.example.herminahealtcenter.model.Resep;

import java.util.List;

public class FardetailAdapter extends RecyclerView.Adapter<FardetailAdapter.FardetailAdapterViewHolder> {

    public List<Resep> reseps;
    public Context context;
    private int rowLayout;
    String cekracikan, hasil, nmobatracikan;

    public List<Racikan> racikans;



    public FardetailAdapter (List<Resep> reseps, int rowLayout, Context context){
        this.reseps = reseps;
        this.rowLayout = rowLayout;
        this.context = context;
    }



    @NonNull
    @Override
    public FardetailAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent,false);
        return new FardetailAdapter.FardetailAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FardetailAdapterViewHolder holder, int position) {

        cekracikan = reseps.get(position).getObatnama();
        racikans = reseps.get(position).getRacikan();

        if (!cekracikan.equals("") || !cekracikan.isEmpty())
        {
            hasil = cekracikan.substring(0,2);
            if (hasil.equals("RC")){
                int ukuran = reseps.get(position).getRacikan().size();
//               List<Racikan> racikans = reseps.get(position).getRacikan();
//               nmobatracikan = racikans.get(position).getObatnamarecikan();
               for (int i = 0 ; i<ukuran;i++){
                   System.out.println("nama obat racikan "+ i + " : " + racikans.get(i).getObatnamarecikan());
               }
                holder.detailracikan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(view.getContext(), hasil, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
        holder.namaobat.setText(reseps.get(position).getObatnama());
        holder.jumlahobat.setText(reseps.get(position).getJumlah());
        holder.carapakai.setText(reseps.get(position).getCarapakai());
    }

    @Override
    public int getItemCount() {
        return reseps == null ? 0 : reseps.size();
    }

    public class FardetailAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView namaobat, jumlahobat, carapakai;
        Button detailracikan;

        public FardetailAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            namaobat = (TextView) itemView.findViewById(R.id.TVnmobatfar);
            jumlahobat = (TextView) itemView.findViewById(R.id.TVjumlahfar);
            carapakai = (TextView) itemView.findViewById(R.id.TVcarapakaifar);
            detailracikan = (Button) itemView.findViewById(R.id.ACBlihatdetailrackanfar);
        }
    }
}
