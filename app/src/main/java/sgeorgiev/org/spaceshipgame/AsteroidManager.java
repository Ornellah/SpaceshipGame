package sgeorgiev.org.spaceshipgame;

import android.graphics.Canvas;
import android.graphics.Color;
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

    public void update(Player player) {
        for(Asteroid a : asteroids) {
            a.update(player.getSpeed());
            if (Rect.intersects(player.getHitBox(), a.getHitBox()) || Rect.intersects(player.getHitBoxWings(), a.getHitBox())) {
                Constants.GAME_OVER = true;
            }
            for(Projectile p : player.getProjectiles()) {
                if(Rect.intersects(p.getHitBox(), a.getHitBox()))
                    player.destroyProjectile(p);
            }
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        for(Asteroid a : asteroids) {
            if(Constants.TEST_MODE) {
                paint.setColor(Color.RED);
                canvas.drawRect(a.getHitBox(), paint);
            }
            a.draw(canvas, paint);
        }
    }
}
