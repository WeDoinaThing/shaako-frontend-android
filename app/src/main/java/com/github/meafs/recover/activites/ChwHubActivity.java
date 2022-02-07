package com.github.meafs.recover.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.ProgressBar;

import com.github.meafs.recover.R;
import com.github.meafs.recover.adapters.NotificationAdapter;
import com.github.meafs.recover.adapters.StaticRvAdapter;
import com.github.meafs.recover.models.NotificationModel;
import com.github.meafs.recover.utils.Speak;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Locale;

public class ChwHubActivity extends AppCompatActivity  implements TextToSpeech.OnInitListener{
    private MaterialToolbar toolbar;
    private RecyclerView recyclerView;
    private NotificationAdapter staticRvAdapter;
    Button SOS_button;
    private TextToSpeech engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chw_hub);

        toolbar = findViewById(R.id.toolbar_hub);
        toolbar.setNavigationOnClickListener(view -> startActivity(new Intent(ChwHubActivity.this, MainActivity.class)));
        recyclerView = findViewById(R.id.notif_rv_layout);
        SOS_button = findViewById(R.id.generate_alert);
        engine = new TextToSpeech(ChwHubActivity.this, this);
        Speak speak = new Speak(ChwHubActivity.this);

        ArrayList<NotificationModel> item = new ArrayList<>();
        item.add(new NotificationModel( "22-Sep-2022","New health campaign available for COVID-19", "#AA80deea"));
        item.add(new NotificationModel("2-June-2022","Recent Cholera surge in your region, tap to know more!","#AA80deea"));
        item.add(new NotificationModel( "19-April-2022","Report to you supervisor to collect new supplies!","#AA80deea"));
        item.add(new NotificationModel("30-March-2022", "Polio Vaccination campaign ends on January, 2023","#AA80deea"));

        staticRvAdapter = new NotificationAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(staticRvAdapter);
        SOS_button.setOnClickListener(view -> {
            speak.speak(engine, "Sending alert to all Emergency Contacts!! ");
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
}