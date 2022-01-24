package com.github.meafs.recover.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.adapters.PatientRvAdapter;
import com.github.meafs.recover.models.Document;
import com.github.meafs.recover.viewmodels.PatientViewModel;

import java.util.ArrayList;
import java.util.List;


public class PatientFragment extends Fragment {

    private RecyclerView recyclerView;
    private PatientRvAdapter patientRvAdapter;
    private PatientViewModel patientViewModel;
    private ArrayList<Document> arrayList = new ArrayList<>();
    private ProgressBar progressBar;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PatientFragment() {
    }

    public static PatientFragment newInstance(String param1, String param2) {
        PatientFragment fragment = new PatientFragment();
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
        View view = inflater.inflate(R.layout.fragment_patient, container, false);
        progressBar = view.findViewById(R.id.progressBar2);

        patientRvAdapter = new PatientRvAdapter(getContext(), arrayList);
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patientViewModel.init();

        patientViewModel.getPatientResponseLiveData().observe(getViewLifecycleOwner(), new Observer<List<Document>>() {
            @Override
            public void onChanged(List<Document> documents) {
                if (documents != null && documents.size() != 0) {
                    arrayList.addAll(documents);
                    patientRvAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                } else {
                    Toast.makeText(view.getContext(), "Please connect to the internet!!", Toast.LENGTH_LONG).show();
                }
            }
        });

//        ArrayList <PatientRvModel> item = new ArrayList<>();
//        item.add(new PatientRvModel("nazia","ctg","high"));
//        item.add(new PatientRvModel("pazia2","ctg2","high2"));
//        item.add(new PatientRvModel("kazia3","ctg3","high3"));
//        item.add(new PatientRvModel("hazia4","ctg4","high4"));

        recyclerView = view.findViewById(R.id.patient_rv_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(patientRvAdapter);

        return view;
    }
}