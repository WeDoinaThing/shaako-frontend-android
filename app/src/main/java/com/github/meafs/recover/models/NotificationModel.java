package com.github.meafs.recover.models;

public class NotificationModel {
    private final String date;
    private final String text;
    private final String colorResource;

    public NotificationModel( String date, String text, String colorResource) {
        this.date = date;
        this.text = text;
        this.colorResource = colorResource;
    }


    public String getText() {
        return text;
    }

    public String getNumber() {
        return date;
    }

    public String getColorResource() {
        return colorResource;
    }

}
