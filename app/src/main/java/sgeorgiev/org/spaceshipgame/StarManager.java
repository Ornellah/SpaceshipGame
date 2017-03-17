package sgeorgiev.org.spaceshipgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by Svetlozar Georgiev on 17/03/2017.
 */

public class StarManager {
    //store the stars in an array list
    private ArrayList<Star> stars;

    public StarManager() {
        stars = new ArrayList<>();
        populateStars(100);
    }

    public void populateStars(int numOfStars) {
        for (int i = 0; i < numOfStars; i++) {
            Star s  = new Star();
            stars.add(s);
        }
    }

    public void update(int playerSpeed) {
        for(Star s : stars)
            s.update(playerSpeed);
    }

    public void draw(Canvas canvas, Paint paint) {
        for (Star s : stars)
            s.draw(canvas, paint);
    }
}
