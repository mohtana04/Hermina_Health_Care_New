package com.rsherminasamarinda.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Historyrwj {
    @SerializedName("noregistrasi")
    @Expose
    private String noregistrasi;
    @SerializedName("tglregistrasi")
    @Expose
    private String tglregistrasi;
    @SerializedName("namapasien")
    @Expose
    private String namapasien;
    @SerializedName("poliklinikname")
    @Expose
    private String poliklinikname;
    @SerializedName("doktername")
    @Expose
    private String doktername;
    @SerializedName("s_nama")
    @Expose
    private String sNama;

    public String getNoregistrasi() {
        return noregistrasi;
    }

    public void setNoregistrasi(String noregistrasi) {
        this.noregistrasi = noregistrasi;
    }

    public String getTglregistrasi() {
        return tglregistrasi;
    }

    public void setTglregistrasi(String tglregistrasi) {
        this.tglregistrasi = tglregistrasi;
    }

    public String getNamapasien() {
        return namapasien;
    }

    public void setNamapasien(String namapasien) {
        this.namapasien = namapasien;
    }

    public String getPoliklinikname() {
        return poliklinikname;
    }

    public void setPoliklinikname(String poliklinikname) {
        this.poliklinikname = poliklinikname;
    }

    public String getDoktername() {
        return doktername;
    }

    public void setDoktername(String doktername) {
        this.doktername = doktername;
    }

    public String getsNama() {
        return sNama;
    }

    public void setsNama(String sNama) {
        this.sNama = sNama;
    }
}
