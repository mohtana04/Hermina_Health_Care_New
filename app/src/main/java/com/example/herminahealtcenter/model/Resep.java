package com.example.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Resep {
    @SerializedName("obatid")
    @Expose
    private String obatid;
    @SerializedName("obatnama")
    @Expose
    private String obatnama;
    @SerializedName("jumlah")
    @Expose
    private String jumlah;
    @SerializedName("carapakai")
    @Expose
    private String carapakai;
    @SerializedName("racikan")
    @Expose
    private List<Racikan> racikan = null;

    public String getObatid() {
        return obatid;
    }

    public void setObatid(String obatid) {
        this.obatid = obatid;
    }

    public String getObatnama() {
        return obatnama;
    }

    public void setObatnama(String obatnama) {
        this.obatnama = obatnama;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getCarapakai() {
        return carapakai;
    }

    public void setCarapakai(String carapakai) {
        this.carapakai = carapakai;
    }

    public List<Racikan> getRacikan() {
        return racikan;
    }

    public void setRacikan(List<Racikan> racikan) {
        this.racikan = racikan;
    }
}
