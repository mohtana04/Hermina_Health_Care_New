package com.example.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryktkheaderResponse {
    @SerializedName("historyktkheader")
    @Expose
    private List<Historyktkheader> historyktkheader = null;
    @SerializedName("metaData")
    @Expose
    private MetaData metaData;

    public List<Historyktkheader> getHistoryktkheader() {
        return historyktkheader;
    }

    public void setHistoryktkheader(List<Historyktkheader> historyktkheader) {
        this.historyktkheader = historyktkheader;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}
