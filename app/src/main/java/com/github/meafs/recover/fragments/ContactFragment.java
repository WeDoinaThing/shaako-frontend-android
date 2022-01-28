package com.github.meafs.recover.fragments;


import static com.azure.android.maps.control.options.PopupOptions.anchor;
import static com.azure.android.maps.control.options.PopupOptions.content;
import static com.azure.android.maps.control.options.PopupOptions.position;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.azure.android.maps.control.AzureMap;
import com.azure.android.maps.control.AzureMaps;
import com.azure.android.maps.control.MapControl;
import com.azure.android.maps.control.MapMath;
import com.azure.android.maps.control.Popup;
import com.azure.android.maps.control.data.Position;
import com.azure.android.maps.control.events.OnFeatureClick;
import com.azure.android.maps.control.events.OnReady;
import com.azure.android.maps.control.layer.BubbleLayer;
import com.azure.android.maps.control.options.AnchorType;
import com.azure.android.maps.control.source.DataSource;
import com.github.meafs.recover.R;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {

    static {
        AzureMaps.setSubscriptionKey("qdC7dOIpHs7Hngg1ucQTfeQwVRV3km7lkoXpaK9xhEU");
    }

    MapControl mapControl;


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

        //Create a data source and add it to the map.
        DataSource source = new DataSource();

//Create a point feature.
        Feature feature = Feature.fromGeometry(Point.fromLngLat(-122.33, 47.64));

//Add a property to the feature.
        feature.addStringProperty("title", "Hello World!");
        Feature feature2 = Feature.fromGeometry(Point.fromLngLat(-124.33, 49.64));

//Add a property to the feature.
        feature2.addStringProperty("title", "yes");

//Create a point feature, pass in the metadata properties, and add it to the data source.
        source.add(feature);
        source.add(feature2);


        mapControl.onCreate(savedInstanceState);
        BubbleLayer layer = new BubbleLayer(source);
        Popup popup = new Popup();


        mapControl.onReady(azureMap -> {
            azureMap.sources.add(source);
            azureMap.layers.add(layer);
            azureMap.popups.add(popup);

            azureMap.events.add((OnFeatureClick) (features) -> {
                //Retrieve the title property of the feature as a string.
                String msg = features.get(0).getStringProperty("title");

                //Do something with the message.

                View customView = LayoutInflater.from(view.getContext()).inflate(R.layout.popup_text, null);

                //Display the name and entity type information of the feature into the text view of the popup layout.
                TextView tv = customView.findViewById(R.id.message);
                tv.setText(String.format("%s",
                        feature.getStringProperty("title")
//                            feature.getStringProperty("EntityType")
                ));

                //Get the position of the clicked feature.
                Position pos = MapMath.getPosition((Point) feature.geometry());

                //Set the options on the popup.
                popup.setOptions(
                        //Set the popups position.
                        position(pos),

                        //Set the anchor point of the popup content.
                        anchor(AnchorType.BOTTOM),

                        //Set the content of the popup.
                        content(customView)
                );

                //Open the popup.
                popup.open();

                //Return a boolean indicating if event should be consumed or continue bubble up.
                return false;
            }, layer.getId());

        });

        return view;
    }
}