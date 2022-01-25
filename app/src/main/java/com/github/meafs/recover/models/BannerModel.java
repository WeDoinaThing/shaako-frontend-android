package com.github.meafs.recover.models;


public class BannerModel {

    String bannerText;
    String bannerLink;

    public BannerModel(String bannerText, String bannerLink) {
        this.bannerText = bannerText;
        this.bannerLink = bannerLink;
    }

    public String getBannerText() {
        return bannerText;
    }

    public void setBannerText(String bannerText) {
        this.bannerText = bannerText;
    }

    public String getBannerLink() {
        return bannerLink;
    }

    public void setBannerLink(String bannerLink) {
        this.bannerLink = bannerLink;
    }
}