package sgeorgiev.org.spaceshipgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by Svetlozar Georgiev on 17/03/2017.
 */

public class EnemyManager {
    //array list to store enemies
    private ArrayList<Enemy> enemies;

    public EnemyManager(int enemyCount) {
        enemies = new ArrayList<>();
        populateEnemies(enemyCount);
    }

    public void populateEnemies(int count) {
        for(int i = 0; i < count; i++) {
            enemies.add(new Enemy());
        }
    }

    public void update(int speed) {
        for (Enemy e : enemies) {
            e.update(speed);
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        for (Enemy e : enemies) {
            canvas.drawBitmap(e.getBitmap(), e.getX(), e.getY(), paint);
        }
    }
}
