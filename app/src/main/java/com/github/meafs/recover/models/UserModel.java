package com.github.meafs.recover.models;

public class UserModel {

    private String weight;
    private String sex;

    public UserModel(String weight, String sex) {
        this.weight = weight;
        this.sex = sex;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
