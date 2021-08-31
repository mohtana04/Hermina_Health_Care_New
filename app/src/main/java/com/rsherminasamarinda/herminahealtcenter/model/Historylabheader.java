package com.rsherminasamarinda.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Historylabheader {
    @SerializedName("nobuktitransaksi")
    @Expose
    private String nobuktitransaksi;
    @SerializedName("tgltransaksi")
    @Expose
    private String tgltransaksi;
    @SerializedName("jamsampling")
    @Expose
    private String jamsampling;
    @SerializedName("patientnama")
    @Expose
    private String patientnama;
    @SerializedName("typeketerangan")
    @Expose
    private String typeketerangan;
    @SerializedName("dokternama")
    @Expose
    private String dokternama;

    public String getNobuktitransaksi() {
        return nobuktitransaksi;
    }

    public void setNobuktitransaksi(String nobuktitransaksi) {
        this.nobuktitransaksi = nobuktitransaksi;
    }

    public String getTgltransaksi() {
        return tgltransaksi;
    }

    public void setTgltransaksi(String tgltransaksi) {
        this.tgltransaksi = tgltransaksi;
    }

    public String getJamsampling() {
        return jamsampling;
    }

    public void setJamsampling(String jamsampling) {
        this.jamsampling = jamsampling;
    }

    public String getPatientnama() {
        return patientnama;
    }

    public void setPatientnama(String patientnama) {
        this.patientnama = patientnama;
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
}
