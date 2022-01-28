package com.github.meafs.recover.fragments;


import static com.azure.android.maps.control.options.PopupOptions.anchor;
import static com.azure.android.maps.control.options.PopupOptions.content;
import static com.azure.android.maps.control.options.PopupOptions.position;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.azure.android.maps.control.source.DataSource;
import com.github.meafs.recover.R;
import com.github.meafs.recover.models.LocationData;
import com.github.meafs.recover.utils.PoiToFeature;
import com.github.meafs.recover.viewmodels.MapsViewModel;
import com.mapbox.geojson.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {

    static {
        AzureMaps.setSubscriptionKey("qdC7dOIpHs7Hngg1ucQTfeQwVRV3km7lkoXpaK9xhEU");
    }

    private MapControl mapControl;
    private MapsViewModel mapsViewModel;
    private ArrayList<LocationData> locdata = new ArrayList<>();


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact, container, false);


        mapControl = view.findViewById(R.id.mapcontrol);

        mapsViewModel = ViewModelProviders.of(this).get(MapsViewModel.class);
        mapsViewModel.init();

        new Handler().postDelayed(() -> {

            mapsViewModel.getLocationResponseLiveData().observe(getViewLifecycleOwner(), new Observer<List<LocationData>>() {
                @Override
                public void onChanged(List<LocationData> locationData) {
                    locdata.addAll(locationData);
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

            } else {
                Toast.makeText(view.getContext(), "Error!!", Toast.LENGTH_SHORT).show();
            }
        }, 10000);

        return view;
    }
}