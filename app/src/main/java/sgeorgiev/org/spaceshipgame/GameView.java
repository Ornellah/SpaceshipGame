package sgeorgiev.org.spaceshipgame;

import android.content.Context;
import android.view.SurfaceView;

/**
 * Created by Svetlozar Georgiev on 17/03/2017.
 */

public class GameView extends SurfaceView implements Runnable {
    // track if the game is running
    volatile boolean playing = true;

    //game thread
    private Thread gameThread = null;

    //constructor
    public GameView(Context context) {
        super(context);
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

    private void update() {}

    private void draw() {}

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
}
