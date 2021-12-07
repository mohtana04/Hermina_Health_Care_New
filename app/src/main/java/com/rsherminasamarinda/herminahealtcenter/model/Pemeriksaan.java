package com.rsherminasamarinda.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pemeriksaan {
    @SerializedName("pemeriksaannama")
    @Expose
    private String pemeriksaannama;
    @SerializedName("dokterpemeriksa")
    @Expose
    private String dokterpemeriksa;
    @SerializedName("nofoto")
    @Expose
    private String nofoto;
    @SerializedName("catatanhasil")
    @Expose
    private String catatanhasil;
    @SerializedName("tglambil")
    @Expose
    private String tglambil;

    public String getPemeriksaannama() {
        return pemeriksaannama;
    }

    public void setPemeriksaannama(String pemeriksaannama) {
        this.pemeriksaannama = pemeriksaannama;
    }

    public String getDokterpemeriksa() {
        return dokterpemeriksa;
    }

    public void setDokterpemeriksa(String dokterpemeriksa) {
        this.dokterpemeriksa = dokterpemeriksa;
    }

    public String getNofoto() {
        return nofoto;
    }

    public void setNofoto(String nofoto) {
        this.nofoto = nofoto;
    }

    public String getCatatanhasil() {
        return catatanhasil;
    }

    public void setCatatanhasil(String catatanhasil) {
        this.catatanhasil = catatanhasil;
    }

    public String getTglambil() {
        return tglambil;
    }

    public void setTglambil(String tglambil) {
        this.tglambil = tglambil;
    }
}
