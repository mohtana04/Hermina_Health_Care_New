package com.example.herminahealtcenter.model;

import com.google.gson.annotations.Expose;

public class Identitas {
    @Expose
    private String nama;
    @Expose
    private String noMr;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoMr() {
        return noMr;
    }

    public void setNoMr(String noMr) {
        this.noMr = noMr;
    }

}
