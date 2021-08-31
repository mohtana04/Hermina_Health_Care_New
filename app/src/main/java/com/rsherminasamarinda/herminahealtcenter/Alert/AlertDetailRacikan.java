package com.rsherminasamarinda.herminahealtcenter.Alert;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rsherminasamarinda.herminahealtcenter.R;
import com.rsherminasamarinda.herminahealtcenter.adapter.FardetailracikanAdapter;
import com.rsherminasamarinda.herminahealtcenter.model.DetailRacikanResponse;
import com.rsherminasamarinda.herminahealtcenter.model.Detailracikan;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiClient;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlertDetailRacikan {
    public void showDialog(Activity activity, String noracikan) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.alert_detail_racikan_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        final RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.racikan_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        ApiInterface apiService =
                ApiClient.createService(ApiInterface.class, "admin", "h3rm1n4c4r3");


        Call<DetailRacikanResponse> call = apiService.dataracikan(noracikan);
        call.enqueue(new Callback<DetailRacikanResponse>() {
            @Override
            public void onResponse(Call<DetailRacikanResponse> call, Response<DetailRacikanResponse> response) {
                int metaData = response.code();
                 List<Detailracikan> detailRacikan = response.body().getDetailracikan();
                recyclerView.setAdapter(new FardetailracikanAdapter(detailRacikan,R.layout.fardetail_list_racikan_layout,activity));


            }

            @Override
            public void onFailure(Call<DetailRacikanResponse> call, Throwable t) {

            }
        });



//        FrameLayout mDialogNo = dialog.findViewById(R.id.frmNo);
//        mDialogNo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"Cancel" ,Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//            }
//        });



        TextView info = dialog.findViewById(R.id.noracikan);
        info.setText(noracikan);

        FrameLayout mDialogOk = dialog.findViewById(R.id.frmOk);
        mDialogOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity.getApplication(), "Okay" ,Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        FloatingActionButton floatingActionButton = dialog.findViewById(R.id.floatingOKalert);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(activity.getApplication(), "Okay" ,Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        dialog.show();
    }
}
