package com.github.meafs.recover.utils;

public class FetchThumbnail {
    String id;
    static String url = "https://img.youtube.com/vi/";

    public FetchThumbnail(String id) {
        this.id = id;
    }
    public String getThumbnailUrl(){
        return url + id + "/0.jpg";
    }
}
