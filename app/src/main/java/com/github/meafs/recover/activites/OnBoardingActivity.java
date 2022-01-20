package com.github.meafs.recover.activites;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;
import com.github.meafs.recover.R;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AhoyOnboarderCard card1 = new AhoyOnboarderCard("Learn", "Learn and review training modules smartly", R.drawable.ic_first);
        AhoyOnboarderCard card2 = new AhoyOnboarderCard("Document", "Diagnose and document patients automatically", R.drawable.ic_second);
        AhoyOnboarderCard card3 = new AhoyOnboarderCard("Find", "Find emergency contacts to nearby hospitals and pharmacies", R.drawable.ic_third);
        AhoyOnboarderCard card4 = new AhoyOnboarderCard("Prepare", "Prepare, prevent, provide\n", R.drawable.four);

        setCardVariables(card1);
        setCardVariables(card2);
        setCardVariables(card3);
        setCardVariables(card4);

        List<AhoyOnboarderCard> pages = new ArrayList<>();
        pages.add(card1);
        pages.add(card2);
        pages.add(card3);
        pages.add(card4);

        setGradientBackground();
        Typeface face = ResourcesCompat.getFont(this, R.font.nunito_regular);
        setFont(face);
        setOnboardPages(pages);
        setFinishButtonTitle("Get Started");
        setFinishButtonDrawableStyle(ContextCompat.getDrawable(this, R.drawable.button_rounded));
    }

    private void setCardVariables(AhoyOnboarderCard card){
        card.setBackgroundColor(R.color.white);
        card.setTitleColor(R.color.black);
        card.setDescriptionColor(R.color.black);
        card.setTitleTextSize(dpToPixels(10, this));
        card.setDescriptionTextSize(dpToPixels(8, this));
        card.setIconLayoutParams(500, 800, 0, 0, 0, 0);
    }

    @Override
    public void onFinishButtonPressed() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(this, ScannerActivity.class));
        } else if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 5);
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                startActivity(new Intent(this, ScannerActivity.class));
            }
        } else {
            Toast.makeText(this, "Please allow camera permission", Toast.LENGTH_SHORT).show();
        }
    }
}
