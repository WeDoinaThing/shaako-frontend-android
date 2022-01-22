package com.github.meafs.recover.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.meafs.recover.R;
import com.github.meafs.recover.adapters.ContentRecylerAdapter;
import com.github.meafs.recover.models.ContentModel;
import com.github.meafs.recover.viewmodels.ContentViewModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class ContentActivity extends AppCompatActivity {

    private ContentViewModel contentViewModel;
    private ArrayList<ContentModel> list;
    private RecyclerView recyclerView;
    private MaterialToolbar toolbar;
    private ProgressBar progressBar;
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

        contentViewModel.getContentResponseLiveData().observe(this, contactModels -> {
            list.addAll(contactModels);
            System.out.println(contactModels.size());

        });

        new Handler().postDelayed(() -> {
            System.out.println(list.size());
            try {
                ContentRecylerAdapter contentRecylerAdapter = new ContentRecylerAdapter(list, ContentActivity.this);
                contentRecylerAdapter.setList(list);

                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                recyclerView.setAdapter(contentRecylerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ContentActivity.this));
            } catch (Exception e) {
                Toast.makeText(ContentActivity.this, "Error! Please connect to internet!", Toast.LENGTH_SHORT).show();
            }

        }, 3000);
    }
}