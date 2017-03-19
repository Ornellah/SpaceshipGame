package sgeorgiev.org.spaceshipgame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by Svetlozar Georgiev on 18/03/2017.
 */

public class AsteroidManager {
    private ArrayList<Asteroid> asteroids;

    public AsteroidManager(int count) {
        asteroids = new ArrayList<>();
        populateAsteroids(count);
    }

    public void populateAsteroids(int count) {
        for(int i = 0; i < count; i++)
            asteroids.add(new Asteroid());
    }

    public boolean update(Player player) {
        for(Asteroid a : asteroids) {
            a.update(player.getSpeed());
            if (Rect.intersects(player.getHitBox(), a.getHitBox())) {
                return true;
            }
        }
        return false;
    }

    public void draw(Canvas canvas, Paint paint) {
        for(Asteroid a : asteroids)
            a.draw(canvas, paint);
    }
}
