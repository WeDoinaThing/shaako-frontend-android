package com.github.meafs.recover.activites;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.meafs.recover.R;
import com.github.meafs.recover.models.PatientModel;
import com.github.meafs.recover.utils.Speak;
import com.github.meafs.recover.viewmodels.PatientViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddPatientActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private static final String TAG = "AddPatientActivity";
    private final Calendar calendar = Calendar.getInstance();
    private final ArrayList<PatientModel> arrayList = new ArrayList<>();
    private String sex;
    private String unit;
    private Date date;
    private TextInputEditText name;
    private TextInputEditText dob;
    private TextInputEditText weight;
    private TextInputEditText contact;
    private PatientViewModel patientViewModel;
    private String size;
    private Button button;
    private String CHWid;
    private String CHWRegion;
    private MaterialToolbar toolbar;
    private TextToSpeech engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        toolbar = findViewById(R.id.toolbar);
        name = findViewById(R.id.patientName);
        dob = findViewById(R.id.patietdate);
        weight = findViewById(R.id.patientWeight);
        button = findViewById(R.id.addPatient);
        contact = findViewById(R.id.patientContact);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            size = mBundle.getString("size");
        }

        System.out.println("Size: " + size);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        SharedPreferences pref = getSharedPreferences("CHW", Context.MODE_PRIVATE);
        engine = new TextToSpeech(AddPatientActivity.this, this);
        Speak speak = new Speak(AddPatientActivity.this);

        CHWid = pref.getString("chwId", "");
        CHWRegion = pref.getString("chwRegion", "");

        System.out.println("Model: " + "Id: " + CHWid + "Region: " + CHWRegion);
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

        ArrayAdapter<CharSequence> sexAdapter = ArrayAdapter.createFromResource(this,
                R.array.genders, R.layout.dropdown_menu_item);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.sex_dropdown);
        autoCompleteTextView.setAdapter(sexAdapter);

        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
            sex = (String) adapterView.getItemAtPosition(i);
        });

        setupDatePicker();

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patientViewModel.init();


        JsonObject jsonResult = new JsonObject();

        toolbar.setNavigationOnClickListener(
                view -> redirectMain()
        );

        button.setOnClickListener(view -> {
            if (size != null && !TextUtils.isEmpty(name.getText()) && !TextUtils.isEmpty(dob.getText()) && !TextUtils.isEmpty(weight.getText()) && !TextUtils.isEmpty(contact.getText())) {
                jsonResult.addProperty("id", String.valueOf(Integer.parseInt(size) + 1));
                jsonResult.addProperty("added_by", CHWid);
                jsonResult.addProperty("date_added", formatter.format(date));
                jsonResult.addProperty("region", CHWRegion);
                jsonResult.addProperty("name", name.getText().toString());
                jsonResult.addProperty("sex", sex);
                jsonResult.addProperty("dob", dob.getText().toString());
                jsonResult.addProperty("bgroup", "O+");
                jsonResult.addProperty("weight", weight.getText().toString() + unit);
                jsonResult.addProperty("height", "");
                jsonResult.addProperty("comorbidity", "");
                jsonResult.addProperty("patient_history", "");
                jsonResult.addProperty("contact", contact.getText().toString());

                patientViewModel.addPatient(String.valueOf(Integer.parseInt(size)), jsonResult);
                patientViewModel.addPatientDone().observe(AddPatientActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        if (s.equals("Done")) {
                            System.out.println(s);
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.patientAdded), Toast.LENGTH_SHORT).show();
                            speak.speak(engine, getResources().getString(R.string.patientAdded));
                            redirectMain();
                        } else {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.noInternet), Toast.LENGTH_LONG).show();
                            speak.speak(engine, getResources().getString(R.string.noInternet));
                        }
                    }
                });
            } else {
                Toast.makeText(AddPatientActivity.this, getResources().getString(R.string.emptyFields), Toast.LENGTH_SHORT).show();
                speak.speak(engine, getResources().getString(R.string.emptyFields));
            }
        });
    }

    private void setDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, day);
        date = calendar.getTime();
    }

    private void setupDatePicker() {
        DatePickerDialog.OnDateSetListener date = (datePicker, year, month, day) -> {
            setDate(day, month, year);
            String dateString = day + "/" + (month + 1) + "/" + year;
            dob.setText(dateString);
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dob.setOnClickListener(view -> new DatePickerDialog(this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        redirectMain();
    }

    public void redirectMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("redirect", "Patient");
        startActivity(intent);
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