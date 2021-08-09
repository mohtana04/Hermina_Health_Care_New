package com.example.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("no_mr")
    @Expose
    private String noMr;
    @SerializedName("gender")
    @Expose
    private String gender;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
