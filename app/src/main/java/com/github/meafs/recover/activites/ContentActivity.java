package com.github.meafs.recover.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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
    private RecyclerView recyclerView;
    private MaterialToolbar toolbar;
    private ProgressBar progressBar;
    private TextToSpeech engine;
    private ContentRecylerAdapter contentRecylerAdapter;
    private EditText etSearchText;

    private final ArrayList<ContentModel> arrayList = new ArrayList<>();
    private final ArrayList<ContentModel> visibleArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        SharedPreferences pref = getSharedPreferences("CHW", Context.MODE_PRIVATE);

        contentViewModel = ViewModelProviders.of(this).get(ContentViewModel.class);
        contentViewModel.init(pref.getString("authToken", ""));
        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.progressBar);
        etSearchText = findViewById(R.id.search_text);

        toolbar.setNavigationOnClickListener(view -> startActivity(new Intent(ContentActivity.this, MainActivity.class)));

        recyclerView = findViewById(R.id.recylerview);
        engine = new TextToSpeech(ContentActivity.this, this);
        Speak speak = new Speak(ContentActivity.this);

        contentRecylerAdapter = new ContentRecylerAdapter(visibleArrayList, ContentActivity.this, engine) {

            @Override
            public void chipClicks(String chipText) {
                etSearchText.setText(chipText);
                etSearchText.setSelection(chipText.length());
                filter(chipText);
            }
        };
        contentRecylerAdapter.setList(visibleArrayList);

        contentViewModel.getContentResponseLiveData().observe(this, contactModels -> {
            if (contactModels != null && contactModels.size() != 0) {
                arrayList.addAll(contactModels);
                filter("");
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(this, getResources().getString(R.string.noInternet), Toast.LENGTH_LONG).show();
                speak.speak(engine, getResources().getString(R.string.noInternet));
            }

        });

        recyclerView.setAdapter(contentRecylerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ContentActivity.this));

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
    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            //Setting speech Language
            engine.setLanguage(Locale.ENGLISH);
            engine.setPitch(1);
        }
    }

    public void filter(String text) {
        visibleArrayList.clear();
        if (text.isEmpty()) {
            visibleArrayList.addAll(arrayList);
        } else {
            text = text.toLowerCase();
            for (ContentModel item : arrayList) {
                if (item.getFields().getTitle().toLowerCase().contains(text)) {
                    visibleArrayList.add(item);
                } else if (item.getFields().getTags().toLowerCase().contains(text)) {
                    visibleArrayList.add(item);
                }
            }
        }
        contentRecylerAdapter.notifyDataSetChanged();
    }

}