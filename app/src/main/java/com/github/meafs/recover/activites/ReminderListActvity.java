package com.github.meafs.recover.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.github.meafs.recover.R;
import com.github.meafs.recover.adapters.ReminderAdapter;
import com.github.meafs.recover.models.ReminderModel;
import com.github.meafs.recover.utils.dbManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ReminderListActvity extends AppCompatActivity {


    FloatingActionButton mCreateRem;
    RecyclerView mRecyclerview;
    ArrayList<ReminderModel> dataholder = new ArrayList<>();
    ReminderAdapter adapter;
    private ArrayList<String> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_list);


        mRecyclerview = findViewById(R.id.recyclerView_reminder);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mCreateRem = findViewById(R.id.create_reminder);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            names = mBundle.getStringArrayList("namesList");
        }

        mCreateRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReminderActivity.class);
                intent.putStringArrayListExtra("namesList", names);
                startActivity(intent);
            }
        });

        Cursor cursor = new dbManager(getApplicationContext()).readallreminders();
        while (cursor.moveToNext()) {
            ReminderModel model = new ReminderModel(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            System.out.println("CURSOR: " +  cursor.getString(4));
            dataholder.add(model);
        }

        adapter = new ReminderAdapter(dataholder);
        mRecyclerview.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();

    }
}