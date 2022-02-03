package com.github.meafs.recover.fragments;


import static com.azure.android.maps.control.options.AnimationOptions.animationDuration;
import static com.azure.android.maps.control.options.AnimationOptions.animationType;
import static com.azure.android.maps.control.options.CameraOptions.center;
import static com.azure.android.maps.control.options.CameraOptions.zoom;
import static com.azure.android.maps.control.options.PopupOptions.anchor;
import static com.azure.android.maps.control.options.PopupOptions.content;
import static com.azure.android.maps.control.options.PopupOptions.position;
import static com.github.meafs.recover.utils.Constants.AzureMapsToken;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
import com.github.meafs.recover.activites.ContentRunnerActivity;
import com.github.meafs.recover.models.LocationData;
import com.github.meafs.recover.utils.PoiToFeature;
import com.github.meafs.recover.utils.Speak;
import com.github.meafs.recover.viewmodels.MapsViewModel;
import com.mapbox.geojson.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment implements TextToSpeech.OnInitListener {

    static {
        AzureMaps.setSubscriptionKey(AzureMapsToken);
    }

    private MapControl mapControl;
    private MapsViewModel mapsViewModel;
    private ArrayList<LocationData> locdata = new ArrayList<>();
    private ProgressBar progressBar;
    private Button button;
    private TextToSpeech engine;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        mapsViewModel = ViewModelProviders.of(this).get(MapsViewModel.class);
        mapsViewModel.init();

        new Handler().postDelayed(() -> {

            mapsViewModel.getLocationResponseLiveData().observe(this, new Observer<List<LocationData>>() {
                @Override
                public void onChanged(List<LocationData> locationData) {
                    if (locationData != null) {
                        locdata.addAll(locationData);
                    } else {
                        Toast.makeText(view.getContext(), view.getResources().getString(R.string.noInternet), Toast.LENGTH_LONG).show();
                        speak.speak(engine, view.getResources().getString(R.string.noInternet));
                    }
                }
            });

            if (locdata.size() != 0) {
                System.out.println(locdata.get(0).getPlaceName() + locdata.get(0).getLattitude() + locdata.get(0).getLongitude());

                DataSource source = new DataSource();

                PoiToFeature poiToFeature = new PoiToFeature(locdata);

                for (int i = 0; i < locdata.size(); i++) {
                    source.add(poiToFeature.getFeature(i));
                    System.out.println(poiToFeature.getFeature(i).toString());
                }

                mapControl.onCreate(savedInstanceState);
                BubbleLayer layer = new BubbleLayer(source);
                Popup popup = new Popup();


                mapControl.onReady(azureMap -> {
                    azureMap.sources.add(source);
                    azureMap.layers.add(layer);
                    azureMap.popups.add(popup);


                    azureMap.setCamera(
                            center(Point.fromLngLat(91.8687, 24.8949)),
                            zoom(8),
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
//                Toast.makeText(view.getContext(), "Error!!", Toast.LENGTH_SHORT).show();
                System.out.println("Error!!");
            }
        }, 5000);

        return view;
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