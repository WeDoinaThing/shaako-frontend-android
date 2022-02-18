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
    ArrayList<ReminderModel> dataholder = new ArrayList<ReminderModel>();                                               //Array list to add reminders and display in recyclerview
    ReminderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_list);


        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerView_reminder);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mCreateRem = (FloatingActionButton) findViewById(R.id.create_reminder);                     //Floating action button to change activity
        mCreateRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReminderActivity.class);
                startActivity(intent);                                                              //Starts the new activity to add Reminders
            }
        });

        Cursor cursor = new dbManager(getApplicationContext()).readallreminders();                  //Cursor To Load data From the database
        while (cursor.moveToNext()) {
            ReminderModel model = new ReminderModel(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            dataholder.add(model);
        }

        adapter = new ReminderAdapter(dataholder);
        mRecyclerview.setAdapter(adapter);                                                          //Binds the adapter with recyclerview

    }

    @Override
    public void onBackPressed() {
        finish();                                                                                   //Makes the user to exit from the app
        super.onBackPressed();

    }
}