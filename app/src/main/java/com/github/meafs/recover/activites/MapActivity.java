package com.github.meafs.recover.activites;

import static com.azure.android.maps.control.options.AnimationOptions.animationDuration;
import static com.azure.android.maps.control.options.AnimationOptions.animationType;
import static com.azure.android.maps.control.options.CameraOptions.center;
import static com.azure.android.maps.control.options.CameraOptions.zoom;
import static com.azure.android.maps.control.options.PopupOptions.anchor;
import static com.azure.android.maps.control.options.PopupOptions.content;
import static com.azure.android.maps.control.options.PopupOptions.position;
import static com.github.meafs.recover.utils.Constants.AzureMapsToken;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.azure.android.maps.control.AzureMaps;
import com.azure.android.maps.control.MapControl;
import com.azure.android.maps.control.MapMath;
import com.azure.android.maps.control.Popup;
import com.azure.android.maps.control.data.Position;
import com.azure.android.maps.control.events.OnFeatureClick;
import com.azure.android.maps.control.layer.BubbleLayer;
import com.azure.android.maps.control.options.AnchorType;
import com.azure.android.maps.control.options.AnimationType;
import com.azure.android.maps.control.source.DataSource;
import com.github.meafs.recover.R;
import com.google.gson.JsonObject;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;

public class MapActivity extends AppCompatActivity {


    static {
        AzureMaps.setSubscriptionKey(AzureMapsToken);

    }

    MapControl mapControl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapControl = findViewById(R.id.mapcontrol);
        mapControl.onCreate(savedInstanceState);

        DataSource source = new DataSource();

        source.importDataFromUrl("asset://SamplePoiDataSet.json");

        BubbleLayer layer = new BubbleLayer(source);

        Popup popup = new Popup();

//        -77.04702,38.91087

        mapControl.onReady(map -> {
            map.sources.add(source);
            map.layers.add(layer);
            map.popups.add(popup);

            map.setCamera(
                    center(Point.fromLngLat(-85.38401, 40.15725)),
                    zoom(2),
                    animationType(AnimationType.FLY),
                    animationDuration(3000)
            );

            map.events.add((OnFeatureClick) (feature) -> {

                Feature f = feature.get(0);
                JsonObject props = f.properties();

                View customView = LayoutInflater.from(this).inflate(R.layout.popup_text, null);

                TextView tv = customView.findViewById(R.id.message);
                tv.setText(String.format("%s",
                        f.getStringProperty("Name")
                ));

                Position pos = MapMath.getPosition((Point) f.geometry());

                popup.setOptions(
                        position(pos),
                        anchor(AnchorType.BOTTOM),
                        content(customView)
                );

                popup.open();

                return false;
            }, layer);


        });
    }


    @Override
    public void onResume() {
        super.onResume();
        mapControl.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapControl.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapControl.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapControl.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapControl.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapControl.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapControl.onSaveInstanceState(outState);
    }

}