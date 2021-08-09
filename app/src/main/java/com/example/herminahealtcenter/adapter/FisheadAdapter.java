package com.example.herminahealtcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.herminahealtcenter.R;
import com.example.herminahealtcenter.model.Historyfisioheader;

import java.util.List;

public class FisheadAdapter extends RecyclerView.Adapter<FisheadAdapter.FisheaderAdapterViewholder> {

    public List<Historyfisioheader> historyfisioheaders;
    public Context context;
    private int rowLayout;

    public FisheadAdapter(List<Historyfisioheader> historyfisioheaders, int rowLayout, Context context){
        this.historyfisioheaders = historyfisioheaders;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public FisheadAdapter.FisheaderAdapterViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new FisheadAdapter.FisheaderAdapterViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FisheadAdapter.FisheaderAdapterViewholder holder, int position) {
        holder.nobuktitransaksi.setText(historyfisioheaders.get(position).getNobuktitransaksi());
        holder.tgltransaksi.setText(historyfisioheaders.get(position).getTgltransaksi());
        holder.typeketerangan.setText(historyfisioheaders.get(position).getTypeketerangan());
        holder.dotkternama.setText(historyfisioheaders.get(position).getDoktername());
    }

    @Override
    public int getItemCount() {
        return historyfisioheaders == null ? 0 : historyfisioheaders.size();
    }

    public class FisheaderAdapterViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nobuktitransaksi, tgltransaksi, typeketerangan, dotkternama;
        public FisheaderAdapterViewholder(View view){
            super(view);
            nobuktitransaksi = (TextView) itemView.findViewById(R.id.tvnotransaksifis);
            tgltransaksi = (TextView) itemView.findViewById(R.id.tvtgltransaksifis);
            typeketerangan = (TextView) itemView.findViewById(R.id.tvtipetransaksifis);
            dotkternama = (TextView) itemView.findViewById(R.id.tvdokternyafis);
        }
        @Override
        public void onClick(View view) {

        }
    }
}
