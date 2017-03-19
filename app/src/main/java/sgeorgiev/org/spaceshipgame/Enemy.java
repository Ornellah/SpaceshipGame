package sgeorgiev.org.spaceshipgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by Svetlozar Georgiev on 17/03/2017.
 */

public class Enemy implements GameObject {
    //enemy sprite
    private Bitmap bitmap;

    //coordinates
    private int x;
    private int y;

    //enemy speed
    private int speed = 1;

    //random number generator
    private Random generator;

    //hit box
    private Rect hitBox;

    //constructor
    public Enemy() {
        //load image
        bitmap = BitmapFactory.decodeResource(Constants.CURR_CONTEXT.getResources(), R.drawable.enemy2);

        //create a random generator
        generator = new Random();
        speed = generator.nextInt(6) + 10;
        x = Constants.SCREEN_WIDTH;
        y = generator.nextInt(Constants.SCREEN_HEIGHT) - bitmap.getHeight();

        //create hitbox
        hitBox = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(bitmap, x, y, paint);
    }

    @Override
    public void update(int playerSpeed) {
        x -= playerSpeed;
        x -= speed;
        //if the enemy reaches the left edge
        if (x < Constants.MIN_X - bitmap.getWidth()) {
            //adding the enemy again to the right edge
            speed = generator.nextInt(10) + 10;
            x = Constants.SCREEN_WIDTH;
            y = generator.nextInt(Constants.SCREEN_HEIGHT) - bitmap.getHeight();
        }

        //update hit box
        hitBox.left = x;
        hitBox.top = y;
        hitBox.right = x + bitmap.getWidth();
        hitBox.bottom = y + bitmap.getHeight();
    }

    public Rect getHitBox() {
        return hitBox;
    }

    //setter for x
    public void setX(int value) {
        this.x = value;
    }

    //getters
    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }
}
