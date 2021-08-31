package com.rsherminasamarinda.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistorylabheaderResponse {
    @SerializedName("historylabheader")
    @Expose
    private List<Historylabheader> historylabheader = null;
    @SerializedName("metaData")
    @Expose
    private MetaData metaData;

    public List<Historylabheader> getHistorylabheader() {
        return historylabheader;
    }

    public void setHistorylabheader(List<Historylabheader> historylabheader) {
        this.historylabheader = historylabheader;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}
