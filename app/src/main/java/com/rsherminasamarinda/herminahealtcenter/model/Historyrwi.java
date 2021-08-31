package com.rsherminasamarinda.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Historyrwi {
    @SerializedName("noregistrasi")
    @Expose
    private String noregistrasi;
    @SerializedName("tglregistrasi")
    @Expose
    private String tglregistrasi;
    @SerializedName("tglpulang")
    @Expose
    private String tglpulang;
    @SerializedName("namapasien")
    @Expose
    private String namapasien;
    @SerializedName("umur")
    @Expose
    private String umur;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("keterangan2")
    @Expose
    private String keterangan2;
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

    public String getTglpulang() {
        return tglpulang;
    }

    public void setTglpulang(String tglpulang) {
        this.tglpulang = tglpulang;
    }

    public String getNamapasien() {
        return namapasien;
    }

    public void setNamapasien(String namapasien) {
        this.namapasien = namapasien;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKeterangan2() {
        return keterangan2;
    }

    public void setKeterangan2(String keterangan2) {
        this.keterangan2 = keterangan2;
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
