package com.github.meafs.recover.fragments;


import static com.github.meafs.recover.utils.Constants.AzureMapsToken;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azure.android.maps.control.AzureMaps;
import com.github.meafs.recover.R;
import com.github.meafs.recover.activites.MapActivity;
import com.github.meafs.recover.adapters.EmergencyAdapter;
import com.github.meafs.recover.models.EmergencyModel;
import com.github.meafs.recover.utils.Speak;

import java.util.ArrayList;
import java.util.Locale;

public class ContactFragment extends Fragment implements TextToSpeech.OnInitListener {

    static {
        AzureMaps.setSubscriptionKey(AzureMapsToken);
    }

    private Button button;
    private static final int REQUEST_LOCATION = 1;

    private TextToSpeech engine;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private EmergencyAdapter emergencyAdapter;
    private ArrayList<EmergencyModel> list = new ArrayList<>();

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        button = view.findViewById(R.id.dial);

        engine = new TextToSpeech(view.getContext(), this);
        Speak speak = new Speak(view.getContext());

        list.add(new EmergencyModel("Ambulance", "Service", "999"));
        list.add(new EmergencyModel("Hospital", "Service", "999"));
        emergencyAdapter = new EmergencyAdapter(list, view.getContext(), engine);

        recyclerView = view.findViewById(R.id.emergencyRecView);

        recyclerView.setAdapter(emergencyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        button.setOnClickListener(view1 -> {
            view.getContext().startActivity(new Intent(view.getContext(), MapActivity.class));
        });

        return view;
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