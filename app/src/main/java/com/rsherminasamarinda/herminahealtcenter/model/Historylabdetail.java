package com.rsherminasamarinda.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Historylabdetail {
    @SerializedName("notransaksi")
    @Expose
    private String notransaksi;
    @SerializedName("patientid")
    @Expose
    private String patientid;
    @SerializedName("patientnama")
    @Expose
    private String patientnama;
    @SerializedName("tgllahir")
    @Expose
    private String tgllahir;
    @SerializedName("umur")
    @Expose
    private String umur;
    @SerializedName("dokternama")
    @Expose
    private String dokternama;
    @SerializedName("tglsampling")
    @Expose
    private String tglsampling;
    @SerializedName("jamsampling")
    @Expose
    private String jamsampling;
    @SerializedName("shift")
    @Expose
    private String shift;
    @SerializedName("typeketerangan")
    @Expose
    private String typeketerangan;
    @SerializedName("noregistrasi")
    @Expose
    private String noregistrasi;
    @SerializedName("testindonesia")
    @Expose
    private List<Testindonesium> testindonesia = null;

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

    public String getTgllahir() {
        return tgllahir;
    }

    public void setTgllahir(String tgllahir) {
        this.tgllahir = tgllahir;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getDokternama() {
        return dokternama;
    }

    public void setDokternama(String dokternama) {
        this.dokternama = dokternama;
    }

    public String getTglsampling() {
        return tglsampling;
    }

    public void setTglsampling(String tglsampling) {
        this.tglsampling = tglsampling;
    }

    public String getJamsampling() {
        return jamsampling;
    }

    public void setJamsampling(String jamsampling) {
        this.jamsampling = jamsampling;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getTypeketerangan() {
        return typeketerangan;
    }

    public void setTypeketerangan(String typeketerangan) {
        this.typeketerangan = typeketerangan;
    }

    public String getNoregistrasi() {
        return noregistrasi;
    }

    public void setNoregistrasi(String noregistrasi) {
        this.noregistrasi = noregistrasi;
    }

    public List<Testindonesium> getTestindonesia() {
        return testindonesia;
    }

    public void setTestindonesia(List<Testindonesium> testindonesia) {
        this.testindonesia = testindonesia;
    }

}
