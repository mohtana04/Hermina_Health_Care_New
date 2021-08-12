package com.example.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Testindonesium {

    @SerializedName("kelompoknama")
    @Expose
    private String kelompoknama;
    @SerializedName("testindonesia")
    @Expose
    private String testindonesia;
    @SerializedName("hasilnumerik")
    @Expose
    private String hasilnumerik;
    @SerializedName("hasilkarakter")
    @Expose
    private String hasilkarakter;
    @SerializedName("normalkarakter")
    @Expose
    private String normalkarakter;
    @SerializedName("satuanindonesia")
    @Expose
    private String satuanindonesia;

    public String getKelompoknama() {
        return kelompoknama;
    }

    public void setKelompoknama(String kelompoknama) {
        this.kelompoknama = kelompoknama;
    }

    public String getTestindonesia() {
        return testindonesia;
    }

    public void setTestindonesia(String testindonesia) {
        this.testindonesia = testindonesia;
    }

    public String getHasilnumerik() {
        return hasilnumerik;
    }

    public void setHasilnumerik(String hasilnumerik) {
        this.hasilnumerik = hasilnumerik;
    }

    public String getHasilkarakter() {
        return hasilkarakter;
    }

    public void setHasilkarakter(String hasilkarakter) {
        this.hasilkarakter = hasilkarakter;
    }

    public String getNormalkarakter() {
        return normalkarakter;
    }

    public void setNormalkarakter(String normalkarakter) {
        this.normalkarakter = normalkarakter;
    }

    public String getSatuanindonesia() {
        return satuanindonesia;
    }

    public void setSatuanindonesia(String satuanindonesia) {
        this.satuanindonesia = satuanindonesia;
    }
}
