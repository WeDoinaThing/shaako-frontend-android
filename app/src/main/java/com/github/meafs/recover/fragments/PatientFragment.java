package com.github.meafs.recover.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.activites.AddPatientActivity;
import com.github.meafs.recover.adapters.PatientRvAdapter;
import com.github.meafs.recover.models.Document;
import com.github.meafs.recover.viewmodels.PatientViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class PatientFragment extends Fragment {

    private RecyclerView recyclerView;
    private PatientRvAdapter patientRvAdapter;
    private PatientViewModel patientViewModel;
    private ArrayList<Document> arrayList = new ArrayList<>();
    private ArrayList<Document> visibleArrayList = new ArrayList<>();
    private ProgressBar progressBar;
    private ExtendedFloatingActionButton extendedFloatingActionButton;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TextView tvPatientAmount;
    private TextView tvVisitedAmount;
    private TextView tvScheduledAmount;

    private EditText etSearchText;

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
        tvPatientAmount = view.findViewById(R.id.patient_amount);
        tvScheduledAmount = view.findViewById(R.id.scheduled_amount);
        tvVisitedAmount = view.findViewById(R.id.visited_amount);
        etSearchText = view.findViewById(R.id.search_text);

        extendedFloatingActionButton = view.findViewById(R.id.add_card1);

        patientRvAdapter = new PatientRvAdapter(getContext(), visibleArrayList);
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patientViewModel.init();

        patientViewModel.getPatientResponseLiveData().observe(getViewLifecycleOwner(), documents -> {
            if (documents != null && documents.size() != 0) {
                arrayList.addAll(documents);
                tvPatientAmount.setText(String.valueOf(documents.size()));
                tvVisitedAmount.setText(String.valueOf(0));
                tvScheduledAmount.setText(String.valueOf(0));
                filter("");
                progressBar.setVisibility(View.GONE);
            } else {
                tvPatientAmount.setText(String.valueOf(0));
                tvVisitedAmount.setText(String.valueOf(0));
                tvScheduledAmount.setText(String.valueOf(0));
                Toast.makeText(view.getContext(), "Please connect to the internet!!", Toast.LENGTH_LONG).show();
            }
        });

        recyclerView = view.findViewById(R.id.patient_rv_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(patientRvAdapter);

        extendedFloatingActionButton.setOnClickListener(view1 -> {
            Intent mIntent = new Intent(view1.getContext(), AddPatientActivity.class);
            mIntent.putExtra("size", String.valueOf(arrayList.size()));
            startActivity(mIntent);
        });

        etSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

    public void filter(String text) {
        visibleArrayList.clear();
        if(text.isEmpty()){
            visibleArrayList.addAll(arrayList);
        } else{
            text = text.toLowerCase();
            for(Document item: arrayList){
                if(item.getName().toLowerCase().contains(text)){
                    visibleArrayList.add(item);
                }
            }
        }
        patientRvAdapter.notifyDataSetChanged();
    }
}