package com.github.meafs.recover.utils;

import androidx.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetchThumbnail {
    static String outputUrl = "https://img.youtube.com/vi/";
    String inputUrl;

    public FetchThumbnail(String inputUrl) {
        this.inputUrl = inputUrl;
    }

    public String getThumbnailUrl() {
        return outputUrl + getVideoId(inputUrl) + "/mqdefault.jpg";
    }

    public String getHDThumbnailUrl() {
        return outputUrl + getVideoId(inputUrl) + "/maxresdefault.jpg";
    }

    public String getVideoId(@NonNull String videoUrl) {
        String videoId = "";
        String regex = "http(?:s)?:\\/\\/(?:m.)?(?:www\\.)?youtu(?:\\.be\\/|be\\.com\\/(?:watch\\?(?:feature=youtu.be\\&)?v=|v\\/|embed\\/|user\\/(?:[\\w#]+\\/)+))([^&#?\\n]+)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(videoUrl);
        if (matcher.find()) {
            videoId = matcher.group(1);
        }
        return videoId;
    }
}
