package com.rsherminasamarinda.herminahealtcenter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rsherminasamarinda.herminahealtcenter.R;
import com.rsherminasamarinda.herminahealtcenter.detail.LaboratoriumDetailActivity;
import com.rsherminasamarinda.herminahealtcenter.model.Historylabheader;

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

        String notransaksi = historylabheaders.get(position).getNobuktitransaksi();
        holder.nobuktitransaksi.setText(historylabheaders.get(position).getNobuktitransaksi());
        holder.jamsampling.setText(historylabheaders.get(position).getJamsampling());
        holder.typeketerangan.setText(historylabheaders.get(position).getTypeketerangan());
        holder.dotkternama.setText(historylabheaders.get(position).getDokternama());
        holder.detaillab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(holder.detaillab.getContext(), "s" ,Toast.LENGTH_LONG).show();
                Intent intent = new Intent (holder.detaillab.getContext(), LaboratoriumDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("notransaksi", notransaksi);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return historylabheaders == null ? 0 : historylabheaders.size();
    }

    public void filterListlab(List<Historylabheader> filteredList) {
        historylabheaders = filteredList;
        notifyDataSetChanged();
    }

    public class LabheaderAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nobuktitransaksi, jamsampling, typeketerangan, dotkternama;
        Button detaillab;
        public LabheaderAdapterViewHolder(View view) {
            super(view);
            nobuktitransaksi = (TextView) view.findViewById(R.id.tvnotransaksilab);
            jamsampling = (TextView) view.findViewById(R.id.tvtgltransaksilab);
            typeketerangan = (TextView) view.findViewById(R.id.tvtipetransaksilab);
            dotkternama = (TextView) view.findViewById(R.id.tvdokternyalab);
            detaillab = (Button) view. findViewById(R.id.ACBlihatdetaillab);
        }

        @Override
        public void onClick(View view) {


        }
    }
}
