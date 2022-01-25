package com.github.meafs.recover.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PatientModel {

    @SerializedName("_rid")
    @Expose
    private String rid;
    @SerializedName("Documents")
    @Expose
    private List<Document> documents = null;
    @SerializedName("_count")
    @Expose
    private Integer count;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


}
