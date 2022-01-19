package com.github.meafs.recover.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.meafs.recover.R;
import com.github.meafs.recover.adapters.ContentRecylerAdapter;
import com.github.meafs.recover.models.ContentModel;
import com.github.meafs.recover.viewmodels.ContentViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {

    private ContentViewModel contentViewModel;
    private ArrayList<ContentModel> list;
    private RecyclerView recyclerView;

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

        SharedPreferences pref = getContext().getSharedPreferences("CHW", Context.MODE_PRIVATE);

        contentViewModel = ViewModelProviders.of(this).get(ContentViewModel.class);
        contentViewModel.init(pref.getString("authToken", ""));

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        recyclerView = view.findViewById(R.id.recylerview);
        list = new ArrayList<>();

        contentViewModel.getContentResponseLiveData().observe(getViewLifecycleOwner(), new Observer<List<ContentModel>>() {
            @Override
            public void onChanged(List<ContentModel> contactModels) {
                list.addAll(contactModels);
                System.out.println(contactModels.size());

            }
        });

        new Handler().postDelayed(new Runnable() {
            public void run() {
                System.out.println(list.size());
                try {
                    ContentRecylerAdapter contentRecylerAdapter = new ContentRecylerAdapter(list, view.getContext());
                    contentRecylerAdapter.setList(list);

                    recyclerView.setAdapter(contentRecylerAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                } catch (Exception e) {
                    Toast.makeText(view.getContext(), "Error! Please connect to internet!", Toast.LENGTH_SHORT).show();
                }

            }
        }, 3000);


        return view;
    }
}