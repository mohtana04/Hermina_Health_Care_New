package com.rsherminasamarinda.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistorylabdetailResponse {
    @SerializedName("historylabdetail")
    @Expose
    private Historylabdetail historylabdetail;
    @SerializedName("metaData")
    @Expose
    private MetaData metaData;

    public Historylabdetail getHistorylabdetail() {
        return historylabdetail;
    }

    public void setHistorylabdetail(Historylabdetail historylabdetail) {
        this.historylabdetail = historylabdetail;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}
