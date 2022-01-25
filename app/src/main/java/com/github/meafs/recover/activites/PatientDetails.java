package com.github.meafs.recover.activites;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.meafs.recover.R;

public class PatientDetails extends AppCompatActivity {

    TextView mIcon;
    TextView mName;
    TextView mArea;
//    TextView mEmailDetails;
//    TextView mEmailTime;
    ImageView mFire;

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

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mIcon.setText(mBundle.getString("icon"));
            ((GradientDrawable) mIcon.getBackground()).setColor(mBundle.getInt("colorIcon"));
            mName.setText(mBundle.getString("name"));
            mArea.setText(mBundle.getString("area"));
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
}