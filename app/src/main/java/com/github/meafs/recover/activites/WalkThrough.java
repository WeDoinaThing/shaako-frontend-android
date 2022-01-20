package com.github.meafs.recover.activites;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.github.meafs.recover.R;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;
import java.util.List;

public class WalkThrough extends FancyWalkthroughActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FancyWalkthroughCard card1 = new FancyWalkthroughCard("Learn", "Learn and review training modules smartly", R.drawable.ic_first);
        FancyWalkthroughCard card2 = new FancyWalkthroughCard("Document", "Diagnose and document patients automatically", R.drawable.ic_second);
        FancyWalkthroughCard card3 = new FancyWalkthroughCard("Find", "Find emergency contacts to nearby hospitals and pharmacies", R.drawable.ic_third);
        FancyWalkthroughCard card4 = new FancyWalkthroughCard("Prepare", "Prepare, prevent, provide\n", R.drawable.four);

        setCardVariable(card1);
        setCardVariable(card2);
        setCardVariable(card3);
        setCardVariable(card4);

        List<FancyWalkthroughCard> pages = new ArrayList<>();

        pages.add(card1);
        pages.add(card2);
        pages.add(card3);
        pages.add(card4);

        for (FancyWalkthroughCard page : pages) {
            page.setTitleColor(R.color.black);
            page.setDescriptionColor(R.color.black);
        }
        setFinishButtonTitle("Get Started");
        showNavigationControls(true);
        setColorBackground(R.color.white);
        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.white);
        setOnboardPages(pages);
    }

    private void setCardVariable(FancyWalkthroughCard card){
        card.setBackgroundColor(R.color.primaryColor);
        card.setIconLayoutParams(500, 800, 0, 0, 0, 0);
        card.setDisplaySkip(false);
    }

    @Override
    public void onFinishButtonPressed() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(WalkThrough.this, ScannerActivity.class));
        } else if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(WalkThrough.this, new String[]{Manifest.permission.CAMERA}, 5);
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                startActivity(new Intent(WalkThrough.this, ScannerActivity.class));
            }
        } else {
            Toast.makeText(this, "Please allow camera permission", Toast.LENGTH_SHORT).show();
        }


    }
}