package com.example.herminahealtcenter.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {
    @SerializedName("response")
    @Expose
    private List<Login> response = null;
    @SerializedName("metaData")
    @Expose
    private MetaData metaData;

    public List<Login> getResponse() {
        return response;
    }

    public void setResponse(List<Login> response) {
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }


}
