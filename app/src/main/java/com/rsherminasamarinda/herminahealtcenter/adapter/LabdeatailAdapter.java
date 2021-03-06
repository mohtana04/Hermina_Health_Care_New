package com.rsherminasamarinda.herminahealtcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rsherminasamarinda.herminahealtcenter.R;
import com.rsherminasamarinda.herminahealtcenter.model.Testindonesium;

import java.util.List;

public class LabdeatailAdapter extends RecyclerView.Adapter<LabdeatailAdapter.LabdeatailAdapterViewHolder> {

    public List<Testindonesium> testindonesiums;
    public Context context;
    private int rowLayout;
    String hasilnumeriks;
    public LabdeatailAdapter (List<Testindonesium> testindonesiums, int rowLayout, Context context){

        this.testindonesiums = testindonesiums;
        this.rowLayout = rowLayout;
        this.context = context;
    }
    @NonNull
    @Override
    public LabdeatailAdapter.LabdeatailAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new LabdeatailAdapter.LabdeatailAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LabdeatailAdapter.LabdeatailAdapterViewHolder holder, int position) {

        holder.kelompoknama.setText(testindonesiums.get(position).getKelompoknama());
        holder.testindonesia.setText(testindonesiums.get(position).getTestindonesia());
        hasilnumeriks = testindonesiums.get(position).getHasilnumerik();
        if (hasilnumeriks.equals("0")){
            holder.hasil.setText(testindonesiums.get(position).getHasilkarakter());
        } else {
            holder.hasil.setText(testindonesiums.get(position).getHasilnumerik());
        }
        holder.normalkarakter.setText(testindonesiums.get(position).getNormalkarakter());
        holder.satuanindonesia.setText(testindonesiums.get(position).getSatuanindonesia());

    }

    @Override
    public int getItemCount() {
        return testindonesiums == null ? 0 : testindonesiums.size();
    }

    public class LabdeatailAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView kelompoknama, testindonesia, hasil, normalkarakter, satuanindonesia;

        public LabdeatailAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            testindonesia = (TextView) itemView.findViewById(R.id.TVtestindonesialabdet);
            kelompoknama = (TextView) itemView.findViewById(R.id.TVkelompoknamalabdet);
            hasil = (TextView) itemView.findViewById(R.id.TVhasillabdet);
            normalkarakter = (TextView) itemView.findViewById(R.id.TVnormalkarakterlabdet);
            satuanindonesia = (TextView) itemView.findViewById(R.id.TVSatuanlabdet);
        }
    }
}
