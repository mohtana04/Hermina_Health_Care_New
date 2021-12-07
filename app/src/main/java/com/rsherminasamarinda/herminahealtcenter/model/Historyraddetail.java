package com.rsherminasamarinda.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Historyraddetail {
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
    @SerializedName("dokterpengirim")
    @Expose
    private String dokterpengirim;
    @SerializedName("dokterpemeriksa")
    @Expose
    private String dokterpemeriksa;
    @SerializedName("tglselesai")
    @Expose
    private String tglselesai;
    @SerializedName("shift")
    @Expose
    private String shift;
    @SerializedName("typeketerangan")
    @Expose
    private String typeketerangan;
    @SerializedName("noregistrasi")
    @Expose
    private String noregistrasi;
    @SerializedName("pemeriksaantype")
    @Expose
    private String pemeriksaantype;
    @SerializedName("pemeriksaan")
    @Expose
    private List<Pemeriksaan> pemeriksaan = null;

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

    public String getDokterpengirim() {
        return dokterpengirim;
    }

    public void setDokterpengirim(String dokterpengirim) {
        this.dokterpengirim = dokterpengirim;
    }

    public String getDokterpemeriksa() {
        return dokterpemeriksa;
    }

    public void setDokterpemeriska(String dokterpemeriksa) {
        this.dokterpemeriksa = dokterpemeriksa;
    }

    public String getTglselesai() {
        return tglselesai;
    }

    public void setTglselesai(String tglselesai) {
        this.tglselesai = tglselesai;
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

    public String getPemeriksaantype() {
        return pemeriksaantype;
    }

    public void setPemeriksaantype(String pemeriksaantype) {
        this.pemeriksaantype = pemeriksaantype;
    }

    public List<Pemeriksaan> getPemeriksaan() {
        return pemeriksaan;
    }

    public void setPemeriksaan(List<Pemeriksaan> pemeriksaan) {
        this.pemeriksaan = pemeriksaan;
    }
}
