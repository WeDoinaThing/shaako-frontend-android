package com.github.meafs.recover.models;

public class EmergencyModel {
    private String name;
    private String type;
    private String phoneNumber;

    public EmergencyModel(String name, String type, String phoneNumber) {
        this.name = name;
        this.type = type;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
