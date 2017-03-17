package sgeorgiev.org.spaceshipgame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton buttonPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the orientation to landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //get the button
        buttonPlay = (ImageButton) findViewById(R.id.play);

        //click listener
        buttonPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //start game activity when the button is tapped
        startActivity(new Intent(this, GameActivity.class));
    }
}
