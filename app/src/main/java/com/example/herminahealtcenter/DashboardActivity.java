package com.example.herminahealtcenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.herminahealtcenter.Alert.AlertKoneksi;
import com.example.herminahealtcenter.adapter.VpAdapterClass;

public class DashboardActivity extends AppCompatActivity {

    private TextView Btnbrd, Btninfo, Btnprof;
    private ViewPager viewPager;
    Boolean on = true;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);


        /*cek koneksi pada handphone*/

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;

        } else {
            connected = false;
            Dialogform();
        }

        /*merubah warna icon status bar*/
        View view = getWindow().getDecorView();
        if (on) {
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        Btninfo = findViewById(R.id.btninformasi);
        Btnbrd = findViewById(R.id.btnberanda);
        Btnprof = findViewById(R.id.btnprofile);
        viewPager = findViewById(R.id.VPberanda);

        FragmentManager fm = this.getSupportFragmentManager();
        viewPager.setAdapter(new VpAdapterClass(fm));
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                DashboardActivity.this.chageTabs(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
        Btnbrd.setBackgroundResource(R.drawable.active_back);

        Btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
                Btninfo.setBackgroundResource(R.drawable.active_back);
            }
        });

        Btnbrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
                Btnbrd.setBackgroundResource(R.drawable.active_back);
            }
        });

        Btnprof.setOnClickListener(v -> {
            viewPager.setCurrentItem(2);
            Btnprof.setBackgroundResource(R.drawable.active_back);
        });
    }

    private void chageTabs(int position) {
        if (position == 0) {
            Btnbrd.setBackgroundResource(R.drawable.active_back);
            Btninfo.setBackgroundResource(R.drawable.inactive_back);
            Btnprof.setBackgroundResource(R.drawable.inactive_back);
        }
        if (position == 1) {
            Btninfo.setBackgroundResource(R.drawable.active_back);
            Btnprof.setBackgroundResource(R.drawable.inactive_back);
            Btnbrd.setBackgroundResource(R.drawable.inactive_back);
        }
        if (position == 2) {
            Btnprof.setBackgroundResource(R.drawable.active_back);
            Btninfo.setBackgroundResource(R.drawable.inactive_back);
            Btnbrd.setBackgroundResource(R.drawable.inactive_back);
        }
    }


    public void Dialogform(){
//        dialog = new AlertDialog.Builder(DashboardActivity.this);
//        inflater = getLayoutInflater();
//        dialogView = inflater.inflate(R.layout.alert_connection_layout, null);
//        dialog.setView(dialogView);
////        dialog.setMessage("Silahkan cek koneksi anda");
////        dialog.setCancelable(true);
////        dialog.setIcon(R.mipmap.ic_launcher);
////        dialog.setTitle("Koneksi Alert !");
////        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
////
////            @Override
////            public void onClick(DialogInterface dialog, int which) {
//////                nama    = txt_nama.getText().toString();
//////                usia    = txt_usia.getText().toString();
//////                alamat  = txt_alamat.getText().toString();
//////                status = txt_status.getText().toString();
//////
//////                txt_hasil.setText("Nama : " + nama + "\n" + "Usia : " + usia + "\n" + "Alamat : " + alamat + "\n" + "Status : " + status);
////                dialog.dismiss();
////            }
////        });
//
////        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
////
////            @Override
////            public void onClick(DialogInterface dialog, int which) {
////                dialog.dismiss();
////            }
////        });
//        dialog.show();

        AlertKoneksi alert = new AlertKoneksi();
        alert.showDialog(DashboardActivity.this,"Cek koneksi anda");
    }
}