package com.example.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryfisioheaderResponse {
    @SerializedName("historyfisioheader")
    @Expose
    private List<Historyfisioheader> historyfisioheader = null;
    @SerializedName("metaData")
    @Expose
    private MetaData metaData;

    public List<Historyfisioheader> getHistoryfisioheader() {
        return historyfisioheader;
    }

    public void setHistoryfisioheader(List<Historyfisioheader> historyfisioheader) {
        this.historyfisioheader = historyfisioheader;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }


}
