package com.smorrison.simplemtglifecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.smorrison.simplemtglifecounter.databinding.ActivityMainBinding;


/**
 * Main Activity for app
 */
public class MainActivity extends AppCompatActivity {
    private AdView adView;
    private static int startLife = 20;
    private TextView lifeTotal1;
    private TextView lifeTotal2;
    private int player1Life = 20;
    private int player2Life = 20;
    private ActivityMainBinding binding;
    private FrameLayout adContainerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Use View binding to target UI elements
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        lifeTotal1 = binding.CurrectLifePlayer1;
        lifeTotal2 = binding.CurrentLifePlayer2;

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        adContainerView = findViewById(R.id.ad_view_container);
        // Step 1 - Create an AdView and set the ad unit ID on it.
        adView = new AdView(this);
        adView.setAdUnitId(getString(R.string.adaptive_banner_ad_unit_id));
        adContainerView.addView(adView);
        loadBanner();
    }

    private void loadBanner() {
        AdRequest adRequest = new AdRequest.Builder().build();
        AdSize adSize = getAdSize();
        // Step 4 - Set the adaptive ad size on the ad view.
        adView.setAdSize(adSize);
        // Step 5 - Start loading the ad in the background.
        adView.loadAd(adRequest);

    }

    private AdSize getAdSize() {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        AdSize currentOrientationAnchoredAdaptiveBannerAdSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
        return currentOrientationAnchoredAdaptiveBannerAdSize;
    }

    public void chageLifeColor(int lifeleft, TextView atextView) {
        if (lifeleft >= 15) {
            atextView.setTextColor(Color.GREEN);
        } else if (lifeleft < 15 && lifeleft > 5)
            atextView.setTextColor(Color.rgb(255, 128, 0)); //Orange
        else if (lifeleft <= 5)
            atextView.setTextColor(Color.RED);
    }

    public void AddLifePlayer1(View view) {
        player1Life++;
        if (lifeTotal1 != null) {
            lifeTotal1.setText(Integer.toString(player1Life));
            chageLifeColor(player1Life, lifeTotal1);

        }
    }

    public void SubtractLifePlayer1(View view) {
        player1Life--;
        if (lifeTotal1 != null) {
            lifeTotal1.setText(Integer.toString(player1Life));
            chageLifeColor(player1Life, lifeTotal1);
        }
        if (player1Life <= 0) {
            Toast toast = Toast.makeText(this, R.string.Player_Two_Wins, Toast.LENGTH_LONG);
            toast.show();
            resetGame();
        }
    }

    public void AddLifePlayer2(View view) {
        player2Life++;
        if (lifeTotal2 != null) {
            lifeTotal2.setText(Integer.toString(player2Life));
            chageLifeColor(player2Life, lifeTotal2);
        }

    }

    public void SubtractLifePlayer2(View view) {
        player2Life--;
        if (lifeTotal2 != null) {
            lifeTotal2.setText(Integer.toString(player2Life));
            chageLifeColor(player2Life, lifeTotal2);
        }
        if (player2Life <= 0) {
            Toast toast = Toast.makeText(this, R.string.Player_One_Wins, Toast.LENGTH_LONG);
            toast.show();
            resetGame();

        }
    }

    public void resetGame() {
        player1Life = startLife;
        player2Life = startLife;
        lifeTotal1.setText(Integer.toString(player1Life));
        lifeTotal2.setText(Integer.toString(player2Life));

        chageLifeColor(player1Life, lifeTotal1);
        chageLifeColor(player2Life, lifeTotal2);
    }

    public void SetPlayer1Name(View view) {
    }

    public void SetPlayer2Name(View view) {
    }

}