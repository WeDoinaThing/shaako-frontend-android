package com.github.meafs.recover.activites;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.meafs.recover.R;
import com.github.meafs.recover.models.PatientModel;
import com.github.meafs.recover.viewmodels.PatientViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class AddPatientActivity extends AppCompatActivity {

    private String sex;
    private String unit;
    private TextInputEditText name;
    private TextInputEditText dob;
    private TextInputEditText weight;
    private PatientViewModel patientViewModel;
    private final ArrayList<PatientModel> arrayList = new ArrayList<>();
    private String size;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        name = findViewById(R.id.patientName);
        dob = findViewById(R.id.patietdate);
        weight = findViewById(R.id.patientWeight);
        button = findViewById(R.id.addPatient);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            size = mBundle.getString("size");
        }

        System.out.println("Size: " + size);

        Spinner spinner1 = findViewById(R.id.weightspinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.units, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                unit = String.valueOf(adapterView.getItemAtPosition(i));
                System.out.println(unit);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner spinner2 = findViewById(R.id.gender);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.genders, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sex = String.valueOf(adapterView.getItemAtPosition(i));
                System.out.println(sex);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patientViewModel.init();


        JsonObject jsonResult = new JsonObject();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (size != null) {
                    jsonResult.addProperty("id", String.valueOf(Integer.parseInt(size) + 1));
                    jsonResult.addProperty("added_by", "replace with CHW UUID");
                    jsonResult.addProperty("date_added", "date patient was added");
                    jsonResult.addProperty("region", "village/mouja other info");
                    jsonResult.addProperty("name", name.getText().toString());
                    jsonResult.addProperty("sex", sex);
                    jsonResult.addProperty("dob", dob.getText().toString());
                    jsonResult.addProperty("bgroup", "optional blood group info");
                    jsonResult.addProperty("weight", weight.getText().toString() + unit);
                    jsonResult.addProperty("height", "optional patient height");
                    jsonResult.addProperty("comorbidity", "optional patient history of diabetes, heart disease, pregnancy, breathing issues, hypertension");
                    jsonResult.addProperty("patient_history", "dictionary with date as key, and patient notes as value");


                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            patientViewModel.addPatient(String.valueOf(Integer.parseInt(size)), jsonResult);
                            patientViewModel.addPatientDone().observe(AddPatientActivity.this, new Observer<String>() {
                                @Override
                                public void onChanged(String s) {
                                    if (s.equals("Done")) {
                                        System.out.println(s);
                                        Toast.makeText(getApplicationContext(), "Doneeee!!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error!!!!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }, 5000);
                } else {
                    Toast.makeText(AddPatientActivity.this, "Please fill all the fields!!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}