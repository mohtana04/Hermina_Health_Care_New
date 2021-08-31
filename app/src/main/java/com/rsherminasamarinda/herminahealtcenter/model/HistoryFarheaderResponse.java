package com.rsherminasamarinda.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryFarheaderResponse {
    @SerializedName("historyfarheader")
    @Expose
    private List<Historyfarheader> historyfarheader = null;
    @SerializedName("metaData")
    @Expose
    private MetaData metaData;

    public List<Historyfarheader> getHistoryfarheader() {
        return historyfarheader;
    }

    public void setHistoryfarheader(List<Historyfarheader> historyfarheader) {
        this.historyfarheader = historyfarheader;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}
