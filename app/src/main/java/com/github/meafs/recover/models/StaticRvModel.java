package com.github.meafs.recover.models;

public class StaticRvModel {
    private final int image;
    private final String number;
    private final String text;
    private final String colorResource;

    public StaticRvModel(int image, String number, String text, String colorResource) {
        this.image = image;
        this.number = number;
        this.text = text;
        this.colorResource = colorResource;
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

    public String getColorResource() {
        return colorResource;
    }

}
