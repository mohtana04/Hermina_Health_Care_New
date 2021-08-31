package com.rsherminasamarinda.herminahealtcenter.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rsherminasamarinda.herminahealtcenter.Alert.AlertDetailRacikan;
import com.rsherminasamarinda.herminahealtcenter.R;
import com.rsherminasamarinda.herminahealtcenter.model.Racikan;
import com.rsherminasamarinda.herminahealtcenter.model.Resep;

import java.util.List;

public class FardetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final int VIEW_TYPE_RESEP = 0;
    final int VIEW_TYPE_RACIKAN = 1;

    public List<Resep> reseps;
    public Context context;
    private int rowLayout;
    String cekracikan, hasil, nmobatracikan, carapakauracikan;
    public List<Racikan> racikans;


    public FardetailAdapter(List<Resep> reseps, List<Racikan> racikans ,  int rowLayout, Context context) {
        this.racikans = racikans;
        this.reseps = reseps;
        this.rowLayout = rowLayout;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
//        return new FardetailAdapter.FardetailAdapterViewHolder(view);
        if(viewType == VIEW_TYPE_RESEP){
            return new ResepViewHolder(view);
        }

//        if(viewType == VIEW_TYPE_RACIKAN){
//            return new RacikanViewHolder(view);
//        }

        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

//        cekracikan = reseps.get(position).getObatnama();
//        racikans = reseps.get(position).getRacikan();
//
//        if (!cekracikan.equals("") || !cekracikan.isEmpty()) {
//            hasil = cekracikan.substring(0, 2);
//            if (hasil.equals("RC")) {
//                nmobatracikan = reseps.get(position).getObatnama();
//                int ukuran = reseps.get(position).getRacikan().size();
////               List<Racikan> racikans = reseps.get(position).getRacikan();
////               nmobatracikan = racikans.get(position).getObatnamarecikan();
//                for (int i = 0; i < ukuran; i++) {
//                   System.out.println("obatracikan "+ i + " : " + racikans.get(i).getObatnamarecikan());
//                   holder.namaobatracikan.setVisibility(View.VISIBLE);
//                   holder.namaobatracikan.setText(racikans.get(i).getObatnamarecikan());
//                }
//                holder.detailracikan.setVisibility(View.VISIBLE);
//                holder.detailracikan.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(view.getContext(), nmobatracikan, Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        }
//        holder.namaobat.setText(reseps.get(position).getObatnama());
//        holder.jumlahobat.setText(reseps.get(position).getJumlah());
//        holder.carapakai.setText(reseps.get(position).getCarapakai());
        if(viewHolder instanceof ResepViewHolder){
            ((ResepViewHolder) viewHolder).populate(reseps.get(position));
        }

//        if(viewHolder instanceof RacikanViewHolder){
//            ((RacikanViewHolder) viewHolder).populate(racikans.get(position - reseps.size()));
//        }

    }

    @Override
    public int getItemCount() {
        return reseps == null ? 0 : reseps.size() ;
//        if (reseps == null) {
//            return 0;
//        } else {
//            reseps.size();
//            return racikans.size();
//        }
    }

    @Override
    public int getItemViewType(int position){
        if(position < reseps.size()){
            return VIEW_TYPE_RESEP;
        }

//        if(position - reseps.size() < racikans.size()){
//            return VIEW_TYPE_RACIKAN;
//        }

        return -1;
    }

//    public class FardetailAdapterViewHolder extends RecyclerView.ViewHolder {
//        TextView namaobat, jumlahobat, carapakai, namaobatracikan, carapakairacikan;
//        Button detailracikan;
//
//        public FardetailAdapterViewHolder(@NonNull View itemView) {
//            super(itemView);
//            namaobat = (TextView) itemView.findViewById(R.id.TVnmobatfar);
//            jumlahobat = (TextView) itemView.findViewById(R.id.TVjumlahfar);
//            carapakai = (TextView) itemView.findViewById(R.id.TVcarapakaifar);
//            detailracikan = (Button) itemView.findViewById(R.id.ACBlihatdetailrackanfar);
//            namaobatracikan = (TextView) itemView.findViewById(R.id.TVnamaobatracikfardet);
//        }
//    }

    public class ResepViewHolder extends RecyclerView.ViewHolder {
        String deteksiracikan;
        TextView namaobat, jumlahobat, carapakai;
        Button detailracikan;
        public ResepViewHolder(View itemView){
            super(itemView);
            namaobat = (TextView) itemView.findViewById(R.id.TVnmobatfar);
            jumlahobat = (TextView) itemView.findViewById(R.id.TVjumlahfar);
            carapakai = (TextView) itemView.findViewById(R.id.TVcarapakaifar);
            detailracikan = (Button) itemView.findViewById(R.id.ACBlihatdetailrackanfar);
        }

        public void populate(Resep resep){
            deteksiracikan = resep.getObatnama();
            hasil = deteksiracikan.substring(0, 2);
            System.out.println("cucok " + hasil);
            if (hasil.equals("RC")){
//                namaobat.setVisibility(itemView.VISIBLE);
//                jumlahobat.setVisibility(itemView.VISIBLE);
//                carapakai.setVisibility(itemView.VISIBLE);
//                farmasiresep.setVisibility(itemView.GONE);
//                farmasiresep.setPadding(0,0,0,0);
                namaobat.setText(resep.getObatnama());
                jumlahobat.setText(resep.getJumlah());
                carapakai.setText(resep.getCarapakai());
                detailracikan.setVisibility(View.VISIBLE);
                detailracikan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDetailRacikan alert = new AlertDetailRacikan();
                        alert.showDialog((Activity) view.getContext(), deteksiracikan);
//                        Toast.makeText(view.getContext(), deteksiracikan, Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                namaobat.setText(resep.getObatnama());
                jumlahobat.setText(resep.getJumlah());
                carapakai.setText(resep.getCarapakai());
            }

        }
    }

//    public class RacikanViewHolder extends RecyclerView.ViewHolder {
//        TextView noracikan, namaobatracikan, jumlahracikan, carapakairac;
//
//        public RacikanViewHolder(View itemView){
//            super(itemView);
//            noracikan = (TextView) itemView.findViewById(R.id.TVnmobatfar);
//            namaobatracikan = (TextView) itemView.findViewById(R.id.TVnamaobatracikfar);
////            jumlahracikan = (TextView) itemView.findViewById(R.id.TVjumlahracikanfar);
////            carapakairac = (TextView) itemView.findViewById(R.id.TVcarapakaifar);
//        }
//
//        public void populate(Racikan racikan){
////            noracikan.setText("Rincian :" +racikan.getNoracikan());
////            namaobatracikan.setText(racikan.getObatnamarecikan());
////            jumlahracikan.setText(racikan.getJumlahracikan());
////            carapakairac.setText(carapakauracikan);
//        }
//    }
}
