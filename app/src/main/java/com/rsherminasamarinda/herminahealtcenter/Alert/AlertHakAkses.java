package com.rsherminasamarinda.herminahealtcenter.Alert;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rsherminasamarinda.herminahealtcenter.R;

public class AlertHakAkses {
    public void showDialog(Activity activity, String text) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.alert_hakakses_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));



//        FrameLayout mDialogNo = dialog.findViewById(R.id.frmNo);
//        mDialogNo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"Cancel" ,Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//            }
//        });

        TextView info = dialog.findViewById(R.id.infoalertprevilage);
        info.setText(text);

        FrameLayout mDialogOk = dialog.findViewById(R.id.frmOkPrevilage);
        mDialogOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(activity.getApplication(), "Okay" ,Toast.LENGTH_SHORT).show();
//                dialog.cancel();
//                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
//                intent.setData(uri);
//                activity.startActivity(intent);
            }
        });

        FloatingActionButton floatingActionButton = dialog.findViewById(R.id.floatingOKalertPrevilage);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(activity.getApplication(), "Okay" ,Toast.LENGTH_SHORT).show();
//                dialog.cancel();
//                activity.finish();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                intent.setData(uri);
                activity.startActivity(intent);

            }
        });

        FrameLayout mDialogNo = dialog.findViewById(R.id.frmNoPrevilage);
        mDialogNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        FloatingActionButton floatingActionButtonNo = dialog.findViewById(R.id.floatingNoalertPrevilage);
        floatingActionButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                activity.finish();
            }
        });
        dialog.show();
    }
}
