package com.example.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryradheaderResponse {
    @SerializedName("historyradheader")
    @Expose
    private List<Historyradheader> historyradheader = null;
    @SerializedName("metaData")
    @Expose
    private MetaData metaData;

    public List<Historyradheader> getHistoryradheader() {
        return historyradheader;
    }

    public void setHistoryradheader(List<Historyradheader> historyradheader) {
        this.historyradheader = historyradheader;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}
