package com.example.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Historyfardetailresponse {
    @SerializedName("historyfardetail")
    @Expose
    private Historyfardetail historyfardetail;
    @SerializedName("metaData")
    @Expose
    private MetaData metaData;

    public Historyfardetail getHistoryfardetail() {
        return historyfardetail;
    }

    public void setHistoryfardetail(Historyfardetail historyfardetail) {
        this.historyfardetail = historyfardetail;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}
