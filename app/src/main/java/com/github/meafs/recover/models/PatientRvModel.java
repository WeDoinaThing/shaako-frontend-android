package com.github.meafs.recover.models;

public class PatientRvModel {
    private String name;
    private String area;
    private String priority;
    private int fire;

    public PatientRvModel(String name, String area, String priority, int fire) {
        this.name = name;
        this.area = area;
        this.priority = priority;
        this.fire = fire;
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

    public void setFire(int fire) {
        this.fire = fire;
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

    public int getFire() {
        return fire;
    }
}
