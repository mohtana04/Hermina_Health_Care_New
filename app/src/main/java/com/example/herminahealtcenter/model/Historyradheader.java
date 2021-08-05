package com.example.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Historyradheader {
    @SerializedName("nobuktitransaksi")
    @Expose
    private String nobuktitransaksi;
    @SerializedName("typeketerangan")
    @Expose
    private String typeketerangan;
    @SerializedName("dokternama")
    @Expose
    private String dokternama;
    @SerializedName("tgltransaksi")
    @Expose
    private String tgltransaksi;

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

    public String getDokternama() {
        return dokternama;
    }

    public void setDokternama(String dokternama) {
        this.dokternama = dokternama;
    }

    public String getTgltransaksi() {
        return tgltransaksi;
    }

    public void setTgltransaksi(String tgltransaksi) {
        this.tgltransaksi = tgltransaksi;
    }
}
