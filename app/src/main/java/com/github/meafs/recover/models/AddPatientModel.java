package com.github.meafs.recover.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddPatientModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("added_by")
    @Expose
    private String addedBy;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("bgroup")
    @Expose
    private String bgroup;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("comorbidity")
    @Expose
    private String comorbidity;
    @SerializedName("patient_history")
    @Expose
    private String patientHistory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBgroup() {
        return bgroup;
    }

    public void setBgroup(String bgroup) {
        this.bgroup = bgroup;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getComorbidity() {
        return comorbidity;
    }

    public void setComorbidity(String comorbidity) {
        this.comorbidity = comorbidity;
    }

    public String getPatientHistory() {
        return patientHistory;
    }

    public void setPatientHistory(String patientHistory) {
        this.patientHistory = patientHistory;
    }

}
