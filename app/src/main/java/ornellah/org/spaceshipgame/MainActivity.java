package ornellah.org.spaceshipgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import static com.google.firebase.analytics.FirebaseAnalytics.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton buttonPlay, buttonScore, buttonSound, buttonHelp;
    private FirebaseAnalytics mFirebaseAnalytics;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = getInstance(this);


        //test crashing
        /*Button crashButton = new Button(this);
        crashButton.setText("Crash!");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                throw new RuntimeException("Test Crash"); // Force a crash
            }
        });

        addContentView(crashButton, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));*/

        //définis les user id
        FirebaseCrashlytics.getInstance().setUserId("12345");

        //log personnalisé
        FirebaseCrashlytics.getInstance().log("problem detected ! Crash button");

        //custom key
        FirebaseCrashlytics.getInstance().setCustomKey("str_key", "crash button");



        //set the orientation to landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //get screen width and height and store them in Constants
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        Constants.SCREEN_WIDTH = dm.widthPixels;

        //get the buttons
        buttonPlay = (ImageButton) findViewById(R.id.play);
        buttonScore = (ImageButton) findViewById(R.id.highscore);
        buttonSound = (ImageButton) findViewById(R.id.sound);
        buttonHelp = (ImageButton) findViewById(R.id.help);

        //click listener
        buttonPlay.setOnClickListener(this);
        buttonScore.setOnClickListener(this);
        buttonSound.setOnClickListener(this);
        buttonHelp.setOnClickListener(this);
    }

    //start game activity when the button is tapped
    @Override
    public void onClick(View v) {
        if(v == buttonPlay)
        {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
            startActivity(new Intent(this, GameActivity.class));
        }
        else if(v == buttonScore)
            startActivity(new Intent(this, HighScore.class));
        else if(v == buttonHelp)
            startActivity(new Intent(this, Help.class));
        else if(v == buttonSound) {
            Constants.SOUND = !Constants.SOUND;
            if(Constants.SOUND) {
                Toast.makeText(getBaseContext(), "Sound is now on.", Toast.LENGTH_SHORT).show();
                buttonSound.setBackgroundResource(R.drawable.soundon);
            }
            else {
                Toast.makeText(getBaseContext(), "Sound is now off", Toast.LENGTH_SHORT).show();
                buttonSound.setBackgroundResource(R.drawable.soundoff);
            }
        }
    }

    //override back button as well so it asks you whether you're sure you want to exit
    //and it doesnt return to the last game over screen
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
