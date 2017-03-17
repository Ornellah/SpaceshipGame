package sgeorgiev.org.spaceshipgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by Svetlozar Georgiev on 17/03/2017.
 */

public class EnemyManager {
    //array list to store enemies
    private ArrayList<Enemy> enemies;
    private boolean explode = false;
    Explosion explosion;
    ArrayList<Enemy> destroyed;

    public EnemyManager(int enemyCount) {
        enemies = new ArrayList<>();
        destroyed = new ArrayList<>();
        populateEnemies(enemyCount);
    }

    public void populateEnemies(int count) {
        for(int i = 0; i < count; i++) {
            enemies.add(new Enemy());
        }
    }

    public void update(int speed, Player player) {
        for (Enemy e : enemies) {
            e.update(speed);
            if(Rect.intersects(player.getHitBox(), e.getHitBox())) {
                explode = true;
                explosion = new Explosion(e.getX(), e.getY());
                destroyed.add(e);
            }
        }
        enemies.removeAll(destroyed);
    }

    public void draw(Canvas canvas, Paint paint) {
        for (Enemy e : enemies) {
            canvas.drawBitmap(e.getBitmap(), e.getX(), e.getY(), paint);
        }
        if(explode) {
            explosion.draw(canvas, paint);
            explode = false;
        }
    }
}
