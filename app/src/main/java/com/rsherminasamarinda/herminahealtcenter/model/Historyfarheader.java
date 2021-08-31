package com.rsherminasamarinda.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Historyfarheader {
    @SerializedName("nobuktitransaksi")
    @Expose
    private String nobuktitransaksi;
    @SerializedName("typeketerangan")
    @Expose
    private String typeketerangan;
    @SerializedName("doktername")
    @Expose
    private String doktername;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    public String getNobuktitransaksi() {
        return nobuktitransaksi;
    }

    public void setNobuktitransaksi(String nobuktitransaksi) {
        this.nobuktitransaksi = nobuktitransaksi;
    }

    public String getTypeketerangan() {
        return typeketerangan;
    }

    public void setTypeketerangan(String typeketerangan) {
        this.typeketerangan = typeketerangan;
    }

    public String getDoktername() {
        return doktername;
    }

    public void setDoktername(String doktername) {
        this.doktername = doktername;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
