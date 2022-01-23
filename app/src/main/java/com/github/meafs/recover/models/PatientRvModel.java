package com.github.meafs.recover.models;

public class PatientRvModel {
    private String name;
    private String area;
    private String priority;

    public PatientRvModel(String name, String area, String priority) {
        this.name = name;
        this.area = area;
        this.priority = priority;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public String getPriority() {
        return priority;
    }

}
