package com.github.meafs.recover.models;

public class LocationData {
    private String placeName;
    private String lattitude;
    private String longitude;

    public LocationData(String placeName, String lattitude, String longitude) {
        this.placeName = placeName;
        this.lattitude = lattitude;
        this.longitude = longitude;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
