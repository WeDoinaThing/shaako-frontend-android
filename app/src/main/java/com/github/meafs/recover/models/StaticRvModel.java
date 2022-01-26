package com.github.meafs.recover.models;

public class StaticRvModel {
    private int image;
    private String number;
    private String text;

    public StaticRvModel(int image, String number, String text) {
        this.image = image;
        this.number = number;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public String getNumber() {
        return number;
    }

}
