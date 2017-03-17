package sgeorgiev.org.spaceshipgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Svetlozar Georgiev on 17/03/2017.
 */

public class Player {
    //player image
    private Bitmap bitmap;

    //coordinates
    private int x;
    private int y;

    //speed of the player
    private int speed = 0;

    //going up?
    private boolean boosting;

    //gravity to make the ship go down the screen
    private final int GRAVITY = -10;

    //Controlling Y coordinate so that ship won't go outside the screen
    private int maxY;
    private int minY;

    //rectangle to use for collision detection
    private Rect hitBox;

    //constructor
    public Player(Context context) {
        //set initial location and speed
        x = 75;
        y = 50;
        speed = 1;

        //load asset
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player1);

        //set to false initially
        boosting = false;

        //min and max y
        minY = 0;
        maxY = Constants.SCREEN_HEIGHT - bitmap.getHeight();

        //create the hitbox
        hitBox = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    //setter for boosting
    public void setBoosting(boolean value) {
        this.boosting = value;
    }

    //update player
    public void update() {
       //if we're boosting
       if(boosting)
           //increase speed
          speed += 2;
       else
           //if not, slow down
           speed -= 5;

        //limit min and max speed
        if(speed > Constants.MAX_SPEED)
            speed = Constants.MAX_SPEED;

        if(speed < Constants.MIN_SPEED)
            speed = Constants.MIN_SPEED;

        //move the ship down
        y -= speed + Constants.GRAVITY;

        //make sure it doesn't go out of the screen
        if (y < minY) {
            y = minY;
        }
        if (y > maxY) {
            y = maxY;
        }

        //need to make sure the hitbox is updated every frame
        hitBox.left = x;
        hitBox.top = y;
        hitBox.right = x + bitmap.getWidth();
        hitBox.bottom = y + bitmap.getHeight();
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.bitmap, this.x, this.y, paint);
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

    public Rect getHitBox() {
        return hitBox;
    }


}