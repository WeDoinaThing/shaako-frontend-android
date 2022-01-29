package com.github.meafs.recover.activites;

import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.meafs.recover.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PatientDetails extends AppCompatActivity {

    TextView mIcon;
    TextView mName;
    TextView mArea;
    //    TextView mEmailDetails;
//    TextView mEmailTime;
    ImageView mFire;
    TextView mAge;
    TextView mWeight;
    TextView mSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        mIcon = findViewById(R.id.pdIcon);
        mName = findViewById(R.id.pdName);
        mArea = findViewById(R.id.pdArea);
//        mEmailDetails = findViewById(R.id.tvEmailDetails);
//        mEmailTime = findViewById(R.id.tvEmailTime);
        mFire = findViewById(R.id.pdFire);
        mAge = findViewById(R.id.pdAge);
        mWeight = findViewById(R.id.pdWeight);
        mSex = findViewById(R.id.gender);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mIcon.setText(mBundle.getString("icon"));
            ((GradientDrawable) mIcon.getBackground()).setColor(mBundle.getInt("colorIcon"));
            mName.setText(mBundle.getString("name"));
            mArea.setText(mBundle.getString("area"));
            mAge.setText(String.valueOf(getAge(mBundle.getString("age"))));
            mWeight.setText(mBundle.getString("weight"));
            mSex.setText(getFirstLetter(mBundle.getString("sex")));

            // TODO: Show contact
//            mBundle.getString("contact");

//            mEmailDetails.setText(mBundle.getString("details"));
//            mEmailTime.setText(mBundle.getString("time"));
        }
        mFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFire.getColorFilter() != null) {
                    mFire.clearColorFilter();
                } else {
                    mFire.setColorFilter(ContextCompat.getColor(PatientDetails.this,
                            R.color.colorGreen));
                }
            }
        });
    }

    private int getAge(String dobString) {

        Date date = null;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = sdf.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date == null) return 0;

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(date);

        int year = dob.get(Calendar.YEAR);
        int month = dob.get(Calendar.MONTH);
        int day = dob.get(Calendar.DAY_OF_MONTH);

        dob.set(year, month + 1, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }


        return age;
    }

    public String getFirstLetter(String s) {
        if (s.equals("Male")) {
            return "M";
        } else if (s.equals("Female")) {
            return "F";
        } else {
            return "O";
        }
    }

}