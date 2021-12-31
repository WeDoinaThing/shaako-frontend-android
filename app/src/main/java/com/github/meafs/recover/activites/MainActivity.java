package com.github.meafs.recover.activites;


import android.os.Bundle;
import android.widget.Toast;

import com.github.meafs.recover.R;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FancyWalkthroughActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FancyWalkthroughCard card1 = new FancyWalkthroughCard("Unique QR code login", "", R.drawable.first);
        FancyWalkthroughCard card2 = new FancyWalkthroughCard("Online lessons and quizzes", "", R.drawable.second);
        FancyWalkthroughCard card3 = new FancyWalkthroughCard("Video demonstration of first aid", "", R.drawable.third);

        card1.setBackgroundColor(R.color.white);
        card1.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        card1.setDisplaySkip(false);
        card2.setBackgroundColor(R.color.white);
        card2.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        card2.setDisplaySkip(false);

        card3.setBackgroundColor(R.color.white);
        card3.setIconLayoutParams(300, 300, 0, 0, 0, 0);
        card3.setDisplaySkip(false);

        List<FancyWalkthroughCard> pages = new ArrayList<>();

        pages.add(card1);
        pages.add(card2);
        pages.add(card3);

        for (FancyWalkthroughCard page : pages) {
            page.setTitleColor(R.color.black);
            page.setDescriptionColor(R.color.black);
        }
        setFinishButtonTitle("Get Started");

        showNavigationControls(true);
        setColorBackground(R.color.colorGreen);
        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.colorGreen);
        setOnboardPages(pages);


    }

    @Override
    public void onFinishButtonPressed() {
        Toast.makeText(this, "Finish Pressed", Toast.LENGTH_SHORT).show();
    }
}