package com.github.meafs.recover.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.activites.ContentActivity;
import com.github.meafs.recover.activites.DiseaseScreeningActivity;
import com.github.meafs.recover.activites.QuizActivity;
import com.github.meafs.recover.adapters.StaticRvAdapter;
import com.github.meafs.recover.models.StaticRvModel;
import com.github.meafs.recover.utils.Speak;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment implements TextToSpeech.OnInitListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ConstraintLayout trainingContent;
    private RelativeLayout quizcontent;
    private RecyclerView recyclerView;
    private StaticRvAdapter staticRvAdapter;
    private ConstraintLayout quizCardView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextToSpeech engine;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
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
        inflater.getContext().setTheme(R.style.setActionBarBlue);
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        trainingContent = view.findViewById(R.id.train_card);
        quizcontent = view.findViewById(R.id.quizcard);

        quizCardView = view.findViewById(R.id.quiz_card);
        engine = new TextToSpeech(view.getContext(), this);

        Speak speak = new Speak(view.getContext());

        trainingContent.setOnClickListener(view13 -> {
            speak.speak(engine, getContext().getResources().getString(R.string.training_contents));
            startActivity(new Intent(getContext(), ContentActivity.class));
        });

        view.findViewById(R.id.useless_button).setOnClickListener(view1 -> startActivity(new Intent(getContext(), DiseaseScreeningActivity.class)));

        quizCardView.setOnClickListener(view1 -> {
            speak.speak(engine, getContext().getResources().getString(R.string.quiz));
            startActivity(new Intent(getContext(), QuizActivity.class));
        });

        ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add(new StaticRvModel(R.drawable.coursefin, "2", "courses finished", "#3498DB"));
        item.add(new StaticRvModel(R.drawable.score, "42", "points earned", "#E74C3C"));
        item.add(new StaticRvModel(R.drawable.schedule, "5", "scheduled appointment", "#F39C12"));
        item.add(new StaticRvModel(R.drawable.success, "56%", "of monthly target met", "#1ABC9C"));

        recyclerView = view.findViewById(R.id.static_rv_layout);
        staticRvAdapter = new StaticRvAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRvAdapter);
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