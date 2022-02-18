package com.github.meafs.recover.models;

public class ReminderModel {
    String title, date, time, pname;

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
        return pname;
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