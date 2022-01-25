package com.github.meafs.recover.models;

import java.util.ArrayList;

public class PatientRvModel {
    private String name;
    private String area;
    private String priority;
    private double weight;
    private String sex;
    private String comorbidity;
    private String chw_id;
    private ArrayList<String> history;

    public PatientRvModel(String name, String area, String priority) {
        this.name = name;
        this.area = area;
        this.priority = priority;

    }

    public PatientRvModel(String name, String area, String priority, double weight, String sex, String comorbidity, String chw_id, ArrayList<String> history) {
        this.name = name;
        this.area = area;
        this.priority = priority;
        this.weight = weight;
        this.sex = sex;
        this.comorbidity = comorbidity;
        this.chw_id = chw_id;
        this.history = history;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getComorbidity() {
        return comorbidity;
    }

    public void setComorbidity(String comorbidity) {
        this.comorbidity = comorbidity;
    }

    public String getChw_id() {
        return chw_id;
    }

    public void setChw_id(String chw_id) {
        this.chw_id = chw_id;
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<String> history) {
        this.history = history;
    }
}
