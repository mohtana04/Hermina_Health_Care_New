package com.rsherminasamarinda.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryraddetailResponse {
    @SerializedName("historyraddetail")
    @Expose
    private Historyraddetail historyraddetail;
    @SerializedName("metaData")
    @Expose
    private MetaData metaData;

    public Historyraddetail getHistoryraddetail() {
        return historyraddetail;
    }

    public void setHistoryraddetail(Historyraddetail historyraddetail) {
        this.historyraddetail = historyraddetail;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}
