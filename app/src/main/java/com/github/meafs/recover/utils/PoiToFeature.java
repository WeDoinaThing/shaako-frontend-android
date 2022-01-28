package com.github.meafs.recover.utils;

import com.github.meafs.recover.models.LocationData;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;

import java.util.ArrayList;

public class PoiToFeature {

    private ArrayList<LocationData> locationData = new ArrayList<>();

    public PoiToFeature(ArrayList<LocationData> locationData) {
        this.locationData = locationData;
    }

    public Feature getFeature(Integer i) {
        Feature feature = Feature.fromGeometry(Point.fromLngLat(Double.parseDouble(locationData.get(i).getLongitude()), Double.parseDouble(locationData.get(i).getLattitude())));

        feature.addStringProperty("title", locationData.get(i).getPlaceName());

        return feature;
    }
}
