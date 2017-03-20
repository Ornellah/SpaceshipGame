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
    private int count;

    public EnemyManager(int enemyCount) {
        this.count = enemyCount;
        enemies = new ArrayList<>();
        destroyed = new ArrayList<>();
        populateEnemies(enemyCount);
    }

    public void populateEnemies(int count) {
        for(int i = 0; i < count; i++) {
            enemies.add(new Enemy());
        }
    }

    public void update(Player player) {
        int diff = 0;
        if(enemies.size() < count) {
            diff = count - enemies.size();
        }

        for(int i = 0; i < diff; i++)
            enemies.add(new Enemy());


        for (Enemy e : enemies) {
            //update enemies
            e.update(player.getSpeed());
            //check for collisions
            for(Projectile p : player.getProjectiles()) {
                if (Rect.intersects(p.getHitBox(), e.getHitBox())) {
                    //set explosion to true so we know we have to draw it
                    explode = true;
                    //and initialise the object
                    explosion = new Explosion(e.getX(), e.getY());
                    //add the ship to be destryed to the array list
                    destroyed.add(e);
                    player.destroyProjectile(p);
                    //increment score
                    Constants.SCORE += 10;
                }
            }
            if(Rect.intersects(player.getHitBox(), e.getHitBox()) || Rect.intersects(player.getHitBoxWings(), e.getHitBox()))
                Constants.GAME_OVER = true;
        }
        //destroy all ships AFTER the loop
        enemies.removeAll(destroyed);
    }

    //draw
    public void draw(Canvas canvas, Paint paint) {
        for (Enemy e : enemies) {
            //draw each space ship
            e.draw(canvas, paint);
        }
        //if we need an explosion
        if(explode) {
            //draw it
            explosion.draw(canvas, paint);
            explode = false;
        }
    }
}
