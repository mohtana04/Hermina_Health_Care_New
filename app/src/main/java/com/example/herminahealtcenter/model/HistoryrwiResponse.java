package com.example.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryrwiResponse {
    @SerializedName("historyrwi")
    @Expose
    private List<Historyrwi> historyrwi = null;
    @SerializedName("metaData")
    @Expose
    private MetaData metaData;

    public List<Historyrwi> getHistoryrwi() {
        return historyrwi;
    }

    public void setHistoryrwi(List<Historyrwi> historyrwi) {
        this.historyrwi = historyrwi;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}
