package com.rsherminasamarinda.herminahealtcenter.Alert;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

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
                Toast.makeText(activity.getApplication(), "Okay" ,Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        FloatingActionButton floatingActionButton = dialog.findViewById(R.id.floatingOKalertPrevilage);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(activity.getApplication(), "Okay" ,Toast.LENGTH_SHORT).show();
                dialog.cancel();
                activity.finish();
            }
        });

        dialog.show();
    }
}
