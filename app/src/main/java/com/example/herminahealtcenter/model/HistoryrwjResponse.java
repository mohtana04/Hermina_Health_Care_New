package com.example.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryrwjResponse {
    @SerializedName("historyrwj")
    @Expose
    private List<Historyrwj> historyrwj = null;
    @SerializedName("metaData")
    @Expose
    private MetaData metaData;

    public List<Historyrwj> getHistoryrwj() {
        return historyrwj;
    }

    public void setHistoryrwj(List<Historyrwj> historyrwj) {
        this.historyrwj = historyrwj;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}
