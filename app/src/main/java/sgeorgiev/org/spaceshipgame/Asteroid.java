package sgeorgiev.org.spaceshipgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by Svetlozar Georgiev on 18/03/2017.
 */

public class Asteroid implements GameObject{
    //sprite
    private Bitmap bitmap;
    //coordinates
    private int x;
    private int y;
    //speed - will probably not need speed as they will not move towards the player
   // int speed = 0;

    //random generator
    Random generator;
    //hit box
    private Rect hitBox;

    //constructor
    public Asteroid() {
        //load image
        bitmap = BitmapFactory.decodeResource(Constants.CURR_CONTEXT.getResources(), R.drawable.asteroid);
        //initialise random generator
        generator = new Random();
        //set coordinates
        x = generator.nextInt((Constants.SCREEN_WIDTH - 2 * Constants.SCREEN_WIDTH / 3) + 1) + 2 * Constants.SCREEN_WIDTH/3;
        y = generator.nextInt(Constants.SCREEN_HEIGHT) - bitmap.getHeight();
        //calculate hit box
        hitBox = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    @Override
    public void update(int speed) {
        //move the asteroid with the screen
        x -= speed;
        //when it reaches x = 0, move to the right again
        if(x < Constants.MIN_X) {
            x = generator.nextInt((Constants.SCREEN_WIDTH - 2 * Constants.SCREEN_WIDTH / 3) + 1) + 2 * Constants.SCREEN_WIDTH/3;
            y = generator.nextInt(Constants.SCREEN_HEIGHT) - bitmap.getHeight();
        }

        //update hit box every frame
        hitBox.left = x;
        hitBox.top = y;
        hitBox.right = x + bitmap.getWidth();
        hitBox.bottom = y + bitmap.getHeight();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(bitmap, x, y, paint);
    }

    //getter for the hitbox
    public Rect getHitBox() {
        return hitBox;
    }

}
