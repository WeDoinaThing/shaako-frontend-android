package com.github.meafs.recover.fragments;


import static com.azure.android.maps.control.options.PopupOptions.anchor;
import static com.azure.android.maps.control.options.PopupOptions.content;
import static com.azure.android.maps.control.options.PopupOptions.position;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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
import com.google.gson.JsonObject;
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

        //Alternatively use Azure Active Directory authenticate.
        //AzureMaps.setAadProperties("<Your aad clientId>", "<Your aad AppId>", "<Your aad Tenant>");
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

        DataSource source = new DataSource();

        source.importDataFromUrl("asset://SamplePoiDataSet.json");

        mapControl.onCreate(savedInstanceState);
        BubbleLayer layer = new BubbleLayer(source);
        Popup popup = new Popup();


        //Wait until the map resources are ready.
        mapControl.onReady(map -> {
            //Add your post map load code here.
            map.sources.add(source);
            map.layers.add(layer);
            map.popups.add(popup);

//Close it initially.
            popup.close();

            //Add a click event to the layer.
            map.events.add((OnFeatureClick) (feature) -> {
                //Get the first feature and it's properties.
                Feature f = feature.get(0);
                JsonObject props = f.properties();

                //Retrieve the custom layout for the popup.
                View customView = LayoutInflater.from(view.getContext()).inflate(R.layout.popup_text, null);

                //Display the name and entity type information of the feature into the text view of the popup layout.
                TextView tv = customView.findViewById(R.id.message);
                tv.setText(String.format("%s\n%s",
                        f.getStringProperty("Name"),
                        f.getStringProperty("EntityType")
                ));

                //Get the position of the clicked feature.
                Position pos = MapMath.getPosition((Point) f.geometry());

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

                //Return a boolean indicating if event should be consumed or continue to bubble up.
                return false;
            }, layer);
        });

        return view;
    }
}