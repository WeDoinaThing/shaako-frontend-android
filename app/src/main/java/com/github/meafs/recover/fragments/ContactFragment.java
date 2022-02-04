package com.github.meafs.recover.fragments;


import static com.azure.android.maps.control.options.AnimationOptions.animationDuration;
import static com.azure.android.maps.control.options.AnimationOptions.animationType;
import static com.azure.android.maps.control.options.CameraOptions.center;
import static com.azure.android.maps.control.options.CameraOptions.zoom;
import static com.azure.android.maps.control.options.PopupOptions.anchor;
import static com.azure.android.maps.control.options.PopupOptions.content;
import static com.azure.android.maps.control.options.PopupOptions.position;
import static com.github.meafs.recover.utils.Constants.AzureMapsToken;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

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
import com.github.meafs.recover.models.LocationData;
import com.github.meafs.recover.utils.PoiToFeature;
import com.github.meafs.recover.utils.Speak;
import com.github.meafs.recover.viewmodels.MapsViewModel;
import com.mapbox.geojson.Point;

import java.util.ArrayList;
import java.util.Locale;

public class ContactFragment extends Fragment implements TextToSpeech.OnInitListener {

    static {
        AzureMaps.setSubscriptionKey(AzureMapsToken);
    }

    private MapControl mapControl;
    private MapsViewModel mapsViewModel;
    private ArrayList<LocationData> locdata = new ArrayList<>();
    private ProgressBar progressBar;
    private Button button;
    private static final int REQUEST_LOCATION = 1;

    private LocationManager locationManager;
    private TextToSpeech engine;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Double latitude;
    private Double longitude;

    public ContactFragment() {
    }

    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        progressBar = view.findViewById(R.id.progressBar2);
        mapControl = view.findViewById(R.id.mapcontrol);
        button = view.findViewById(R.id.dial);

        engine = new TextToSpeech(view.getContext(), this);
        Speak speak = new Speak(view.getContext());

        button.setOnClickListener(view1 -> Toast.makeText(view1.getContext(), "This will dial an emergency number", Toast.LENGTH_SHORT).show());

        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation(view.getContext());
        }

        mapsViewModel = ViewModelProviders.of(this).get(MapsViewModel.class);
        mapsViewModel.init(String.valueOf(latitude), String.valueOf(longitude));

        mapsViewModel.getLocationResponseLiveData().observe(this, locationData -> {
            if (locationData != null) {
                locdata.addAll(locationData);
                setupMap(view, savedInstanceState);
            } else {
                Toast.makeText(view.getContext(), getResources().getString(R.string.noInternet), Toast.LENGTH_LONG).show();
                speak.speak(engine, getResources().getString(R.string.noInternet));
            }

        });
        return view;
    }

    private void setupMap(View view, Bundle savedInstanceState) {
        if (locdata.size() != 0) {
            DataSource source = new DataSource();
            PoiToFeature poiToFeature = new PoiToFeature(locdata);
            for (int i = 0; i < locdata.size(); i++) {
                source.add(poiToFeature.getFeature(i));
            }

            mapControl.onCreate(savedInstanceState);
            BubbleLayer layer = new BubbleLayer(source);
            Popup popup = new Popup();
            mapControl.onReady(azureMap -> {
                azureMap.sources.add(source);
                azureMap.layers.add(layer);
                azureMap.popups.add(popup);
                System.out.println("Lat: " + latitude + "Long:" + longitude);
                azureMap.setCamera(
                        center(Point.fromLngLat(longitude, latitude)),
                        zoom(10),
                        animationType(AnimationType.FLY),
                        animationDuration(3000)
                );
                azureMap.events.add((OnFeatureClick) (features) -> {
                    View customView = LayoutInflater.from(view.getContext()).inflate(R.layout.popup_text, null);
                    for (int i = 0; i < locdata.size(); i++) {
                        TextView tv = customView.findViewById(R.id.message);
                        tv.setText(String.format("%s",
                                poiToFeature.getFeature(i).getStringProperty("title")
                        ));
                        Position pos = MapMath.getPosition((Point) poiToFeature.getFeature(i).geometry());
                        popup.setOptions(
                                position(pos),
                                anchor(AnchorType.BOTTOM),
                                content(customView)
                        );
                        popup.open();
                    }
                    return false;
                }, layer.getId());
            });
            progressBar.setVisibility(View.GONE);
        } else {
            System.out.println("Error!!");
        }
    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", (dialog, which) -> startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))).setNegativeButton("No", (dialog, which) -> dialog.cancel());
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getLocation(Context context) {
        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                latitude = locationGPS.getLatitude();
                longitude = locationGPS.getLongitude();
            } else {
                longitude = 91.8687;
                latitude = 24.8949;
                Toast.makeText(context, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroy() {
        if (mapControl != null) {
            mapControl.onDestroy();
        }
        super.onDestroy();

    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            //Setting speech Language
            engine.setLanguage(Locale.ENGLISH);
            engine.setPitch(1);
        }
    }
}