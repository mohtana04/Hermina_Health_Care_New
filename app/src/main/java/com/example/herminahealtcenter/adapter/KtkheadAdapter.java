package com.example.herminahealtcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.herminahealtcenter.R;
import com.example.herminahealtcenter.model.Historyktkheader;

import java.util.List;

public class KtkheadAdapter extends RecyclerView.Adapter<KtkheadAdapter.KtkheaderAdapterViewHolder> {

    public List<Historyktkheader> historyktkheaders;
    public Context context;
    private int rowLayout;

    public KtkheadAdapter(List<Historyktkheader> historyktkheaders, int rowLayout, Context context){
        this.historyktkheaders = historyktkheaders;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public KtkheaderAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
       return new KtkheadAdapter.KtkheaderAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KtkheaderAdapterViewHolder holder, int position) {
        holder.nobuktitransaksi.setText(historyktkheaders.get(position).getNobuktitransaksi());
        holder.tgltransaksi.setText(historyktkheaders.get(position).getTgltransaksi());
        holder.typeketerangan.setText(historyktkheaders.get(position).getTypeketerangan());
        holder.dotkternama.setText(historyktkheaders.get(position).getDoktername());
    }

    @Override
    public int getItemCount() {
        return historyktkheaders == null ? 0 : historyktkheaders.size();
    }


    public class KtkheaderAdapterViewHolder  extends  RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nobuktitransaksi, tgltransaksi, typeketerangan, dotkternama;

        public KtkheaderAdapterViewHolder(View view){
            super(view);
            nobuktitransaksi = (TextView) itemView.findViewById(R.id.tvnotransaksiktk);
            tgltransaksi = (TextView) itemView.findViewById(R.id.tvtgltransaksiktk);
            typeketerangan = (TextView) itemView.findViewById(R.id.tvtipetransaksiktk);
            dotkternama = (TextView) itemView.findViewById(R.id.tvdokternyaktk);

        }
        @Override
        public void onClick(View view) {

        }
    }
}
