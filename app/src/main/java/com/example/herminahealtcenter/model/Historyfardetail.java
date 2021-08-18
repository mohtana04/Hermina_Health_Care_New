package com.example.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Historyfardetail {
    @SerializedName("noregistrasi")
    @Expose
    private String noregistrasi;
    @SerializedName("notransaksi")
    @Expose
    private String notransaksi;
    @SerializedName("patientid")
    @Expose
    private String patientid;
    @SerializedName("patientnama")
    @Expose
    private String patientnama;
    @SerializedName("dokterpengirim")
    @Expose
    private String dokterpengirim;
    @SerializedName("typeketerangan")
    @Expose
    private String typeketerangan;
    @SerializedName("shift")
    @Expose
    private String shift;
    @SerializedName("petugas")
    @Expose
    private String petugas;
    @SerializedName("tgl_trans")
    @Expose
    private String tglTrans;
    @SerializedName("jam")
    @Expose
    private String jam;
    @SerializedName("resep")
    @Expose
    private List<Resep> resep = null;

    public String getNoregistrasi() {
        return noregistrasi;
    }

    public void setNoregistrasi(String noregistrasi) {
        this.noregistrasi = noregistrasi;
    }

    public String getNotransaksi() {
        return notransaksi;
    }

    public void setNotransaksi(String notransaksi) {
        this.notransaksi = notransaksi;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getPatientnama() {
        return patientnama;
    }

    public void setPatientnama(String patientnama) {
        this.patientnama = patientnama;
    }

    public String getDokterpengirim() {
        return dokterpengirim;
    }

    public void setDokterpengirim(String dokterpengirim) {
        this.dokterpengirim = dokterpengirim;
    }

    public String getTypeketerangan() {
        return typeketerangan;
    }

    public void setTypeketerangan(String typeketerangan) {
        this.typeketerangan = typeketerangan;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getPetugas() {
        return petugas;
    }

    public void setPetugas(String petugas) {
        this.petugas = petugas;
    }

    public String getTglTrans() {
        return tglTrans;
    }

    public void setTglTrans(String tglTrans) {
        this.tglTrans = tglTrans;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public List<Resep> getResep() {
        return resep;
    }

    public void setResep(List<Resep> resep) {
        this.resep = resep;
    }
}
