package sgeorgiev.org.spaceshipgame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Svetlozar Georgiev on 17/03/2017.
 */

public class GameView extends SurfaceView implements Runnable {
    // track if the game is running
    volatile boolean playing = true;

    //game thread
    private Thread gameThread = null;

    //player
    private Player player;

    //objects needed for drawing
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    //stars
    private StarManager starManager;

    //enemies
    private EnemyManager enemyManager;

    //create asteroids
    private AsteroidManager asteroidManager;

    //track if game is over
    private boolean gameOver;

    //constructor
    public GameView(Context context) {
        super(context);

        //store the context in Constants
        Constants.CURR_CONTEXT = context;

        //initialise player
        player = new Player(context);

        //and initialise drawing objects
        surfaceHolder = getHolder();

        //initialise paint
        paint = new Paint();

        //initialise star manager
        starManager = new StarManager();

        //initialise enemy manager
        enemyManager = new EnemyManager(3);

        //initialise asteroids
        asteroidManager = new AsteroidManager(3);

        //need to track if game is over
        gameOver = false;
    }

    @Override
    public void run() {
        // while playing the game
        while (playing) {
            //update the frame
            update();
            //draw objects on the frame
            draw();
            //control
            control();
        }
    }

    private void update() {
        //if game is nt over
        if(!gameOver) {
            //call update on every object
            player.update();
            enemyManager.update(player);
            starManager.update(player.getSpeed());
            gameOver = asteroidManager.update(player);
        } else {
            return;
        }
    }

    private void draw() {
        //if surface is valid
        if (surfaceHolder.getSurface().isValid()) {
            //lock the canvas
            canvas = surfaceHolder.lockCanvas();
            //set background colour
            canvas.drawColor(Color.BLACK);
            //draw stars
            paint.setColor(Color.WHITE);
            starManager.draw(this.canvas, this.paint);
            //draw player
            player.draw(this.canvas, this.paint);
            //draw enemies
            enemyManager.draw(this.canvas, this.paint);
            //draw asteroids
            asteroidManager.draw(this.canvas, this.paint);

            if (gameOver) {
                paint.setTextSize(150);
                paint.setTextAlign(Paint.Align.CENTER);

                int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
                canvas.drawText("Game over", canvas.getWidth() / 2, yPos, paint);
            }
            //unlock canvas
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    //this should result in the game having around 60 FPS
    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) { }
    }

    //pause
    public void pause() {
        //set paying to false
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {}
    }

    //resume
    public void resume() {
        // playing is true now
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    //override onTouchEvent to implement movement of the player
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction() & event.ACTION_MASK) {
            //when released
            case MotionEvent.ACTION_UP:
                //stop boosting
                player.setBoosting(false);
                break;
            //when pressed
            case MotionEvent.ACTION_DOWN:
                //bost
                player.setBoosting(true);
                if(gameOver)
                    Constants.CURR_CONTEXT.startActivity(new Intent(Constants.CURR_CONTEXT, MainActivity.class));
                break;
        }
        return true;
    }
}
