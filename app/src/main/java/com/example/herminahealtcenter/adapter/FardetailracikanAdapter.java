package com.example.herminahealtcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.herminahealtcenter.R;
import com.example.herminahealtcenter.model.Detailracikan;

import java.util.List;

public class FardetailracikanAdapter extends RecyclerView.Adapter<FardetailracikanAdapter.FardetailracikanAdapterViewHolder> {

    public List<Detailracikan> racikans;
    public Context context;
    private int rowLayout;


    public FardetailracikanAdapter(List<Detailracikan> racikans, int rowLayout, Context context){
            this.racikans = racikans;
            this.rowLayout = rowLayout;
            this.context = context;
    }

    @NonNull
    @Override
    public FardetailracikanAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new FardetailracikanAdapter.FardetailracikanAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FardetailracikanAdapterViewHolder holder, int position) {
//        holder.noracikan.setText(racikans.get(position).getNoracikan());
        holder.jumlahracikan.setText(racikans.get(position).getJumlahracikan());
        holder.namaobatracikan.setText(racikans.get(position).getObatnamarecikan());
    }

    @Override
    public int getItemCount() {
        return racikans == null ? 0 : racikans.size();
    }


    public class FardetailracikanAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView noracikan, namaobatracikan, jumlahracikan;


        public FardetailracikanAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

//            noracikan = (TextView) itemView.findViewById(R.id.TVnomorracikanfardet);
            namaobatracikan = (TextView) itemView.findViewById(R.id.TVnamaobatracikfardet);
            jumlahracikan = (TextView) itemView.findViewById(R.id.TVjumlahracikanfardet);
        }
    }
}
