package com.rsherminasamarinda.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detailracikan {
    @SerializedName("obatid")
    @Expose
    private String obatid;
    @SerializedName("noracikan")
    @Expose
    private String noracikan;
    @SerializedName("obatnamarecikan")
    @Expose
    private String obatnamarecikan;
    @SerializedName("jumlahracikan")
    @Expose
    private String jumlahracikan;

    public String getObatid() {
        return obatid;
    }

    public void setObatid(String obatid) {
        this.obatid = obatid;
    }

    public String getNoracikan() {
        return noracikan;
    }

    public void setNoracikan(String noracikan) {
        this.noracikan = noracikan;
    }

    public String getObatnamarecikan() {
        return obatnamarecikan;
    }

    public void setObatnamarecikan(String obatnamarecikan) {
        this.obatnamarecikan = obatnamarecikan;
    }

    public String getJumlahracikan() {
        return jumlahracikan;
    }

    public void setJumlahracikan(String jumlahracikan) {
        this.jumlahracikan = jumlahracikan;
    }

}
