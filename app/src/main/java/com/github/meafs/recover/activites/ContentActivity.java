package com.github.meafs.recover.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.adapters.ContentRecylerAdapter;
import com.github.meafs.recover.models.ContentModel;
import com.github.meafs.recover.utils.Speak;
import com.github.meafs.recover.viewmodels.ContentViewModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Locale;

public class ContentActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private ContentViewModel contentViewModel;
    private ArrayList<ContentModel> list;
    private RecyclerView recyclerView;
    private MaterialToolbar toolbar;
    private ProgressBar progressBar;
    private TextToSpeech engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        SharedPreferences pref = getSharedPreferences("CHW", Context.MODE_PRIVATE);

        contentViewModel = ViewModelProviders.of(this).get(ContentViewModel.class);
        contentViewModel.init(pref.getString("authToken", ""));
        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.progressBar);

        toolbar.setNavigationOnClickListener(view -> startActivity(new Intent(ContentActivity.this, MainActivity.class)));

        recyclerView = findViewById(R.id.recylerview);
        list = new ArrayList<>();
        engine = new TextToSpeech(ContentActivity.this, this);
        Speak speak = new Speak(ContentActivity.this);

        contentViewModel.getContentResponseLiveData().observe(this, contactModels -> {
            if (contactModels != null && contactModels.size() != 0) {
                list.addAll(contactModels);
            } else {
                Toast.makeText(this, getResources().getString(R.string.noInternet), Toast.LENGTH_LONG).show();
                speak.speak(engine, getResources().getString(R.string.noInternet));
            }

        });

        new Handler().postDelayed(() -> {
            System.out.println(list.size());
            try {
                ContentRecylerAdapter contentRecylerAdapter = new ContentRecylerAdapter(list, ContentActivity.this, engine);
                contentRecylerAdapter.setList(list);

                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                recyclerView.setAdapter(contentRecylerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ContentActivity.this));
            } catch (Exception e) {
                Toast.makeText(ContentActivity.this, getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
                speak.speak(engine, getResources().getString(R.string.noInternet));

            }

        }, 3000);
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