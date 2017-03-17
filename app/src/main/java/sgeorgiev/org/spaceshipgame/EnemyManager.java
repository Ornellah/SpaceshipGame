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
            //update enemies
            e.update(speed);
            //check for collisions
            if(Rect.intersects(player.getHitBox(), e.getHitBox())) {
                //set explosion to true so we know we have to draw it
                explode = true;
                //and initialise the object
                explosion = new Explosion(e.getX(), e.getY());
                //add the ship to be destryed to the array list
                destroyed.add(e);
            }
        }
        //destroy all ships AFTER the loop
        enemies.removeAll(destroyed);
    }

    //draw
    public void draw(Canvas canvas, Paint paint) {
        for (Enemy e : enemies) {
            //draw each space ship
            canvas.drawBitmap(e.getBitmap(), e.getX(), e.getY(), paint);
        }
        //if we need an explosion
        if(explode) {
            //draw it
            explosion.draw(canvas, paint);
            explode = false;
        }
    }
}
