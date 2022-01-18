package com.github.meafs.recover.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.meafs.recover.R;
import com.github.meafs.recover.models.TodoModel;
import com.github.meafs.recover.viewmodels.ApiViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientFragment extends Fragment {

    private ApiViewModel apiViewModel;
    private List<TodoModel> list;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PatientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PatientFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        list = new ArrayList<>();

        apiViewModel = ViewModelProviders.of(this).get(ApiViewModel.class);
        apiViewModel.init();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient, container, false);

        apiViewModel.getPatientResponseLiveData().observe(getViewLifecycleOwner(), new Observer<List<TodoModel>>() {
            @Override
            public void onChanged(List<TodoModel> todoModels) {
                list.addAll(todoModels);
                System.out.println("HERE");
            }
        });

        new Handler().postDelayed(new Runnable() {
            public void run() {
                System.out.println(list.size());
                System.out.println(list.get(1).getTitle());
            }
        }, 2000);

        return view;
    }
}