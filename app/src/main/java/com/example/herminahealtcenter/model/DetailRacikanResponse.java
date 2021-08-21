package com.example.herminahealtcenter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailRacikanResponse {
    @SerializedName("detailracikan")
    @Expose
    private List<Detailracikan> detailracikan = null;
    @SerializedName("metaData")
    @Expose
    private MetaData metaData;

    public List<Detailracikan> getDetailracikan() {
        return detailracikan;
    }

    public void setDetailracikan(List<Detailracikan> detailracikan) {
        this.detailracikan = detailracikan;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

}
