package com.github.meafs.recover.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.github.meafs.recover.R;

public class SymptomScreen extends AppCompatActivity {

    private CheckBox ck1, ck2, ck3, ck4, ck5, ck6;
    private Button button;
    int totalamount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_screen);
        button = findViewById(R.id.checkbtn);
        ck1 = findViewById(R.id.cb1);
        ck2 = findViewById(R.id.cb2);
        ck3 = findViewById(R.id.cb3);
        ck4 = findViewById(R.id.cb4);
        ck5 = findViewById(R.id.cb5);
        ck6 = findViewById(R.id.cb6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ck1.isChecked()) {
                    totalamount += 1;
                }
                if (ck2.isChecked()) {
                    totalamount += 1;
                }
                if (ck3.isChecked()) {
                    totalamount += 1;
                }
                if (ck4.isChecked()) {
                    totalamount += 1;
                }
                if (ck5.isChecked()) {
                    totalamount += 1;
                }
                if (ck6.isChecked()) {
                    totalamount += 1;
                }
                if (totalamount > 3) {
                    Toast.makeText(SymptomScreen.this, "Risk", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SymptomScreen.this, "No issues", Toast.LENGTH_SHORT).show();
                }
                totalamount = 0;
            }
        });


    }
}