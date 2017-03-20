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
                Constants.GAMEOVER_TIME = System.currentTimeMillis();
            }
            for(Projectile p : player.getProjectiles()) {
                if(Rect.intersects(p.getHitBox(), a.getHitBox()))
                    player.destroyProjectile(p);
            }
        }

        //every 11 seconds (60 fps * 11 seconds) add a new asteroid to increase the difficulty of the game
        if(Constants.FRAME_COUNT % (60 * 11) == 0 && asteroids.size() < 4 && Constants.FRAME_COUNT != 0) {
            asteroids.add(new Asteroid());
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
