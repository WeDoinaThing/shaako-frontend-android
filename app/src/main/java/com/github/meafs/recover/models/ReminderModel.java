package com.github.meafs.recover.models;

//model class is used to set and get the data from the database
public class ReminderModel {
    String title, date, time,pname;
    public ReminderModel() {
    }
    public ReminderModel(String title, String date, String time, String pname) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.pname = pname;
    }
    public String getTitle() {
        return title;
    }

    public String getPname() {
        return "ABUL" ;             //TO-DO
//        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}