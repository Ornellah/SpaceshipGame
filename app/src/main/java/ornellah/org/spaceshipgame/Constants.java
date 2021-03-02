package ornellah.org.spaceshipgame;

import android.content.Context;
import android.os.Vibrator;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Svetlozar Georgiev on 17/03/2017.
 */

public class Constants {
    //screen dimensions
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static final int MIN_X = 0;
    public static final int MIN_Y = 0;

    //player constants
    public static final int GRAVITY = -10;
    public static final int MIN_SPEED = 1;
    public static final int MAX_SPEED = 20;

    //store context here as well
    public static Context CURR_CONTEXT;

    //this will be used for testing purposes
    public static boolean TEST_MODE = false;

    //used to check if the player has died
    public static boolean GAME_OVER;

    //will need the frame count for a few things
    public static int FRAME_COUNT;

    //score will be saved here as well so it can be accessed from anywhere
    public static int SCORE;

    public static int ELAPSED_TIME = 0;

    public static long GAMEOVER_TIME = 0;

    public static boolean SOUND = true;


    public static AtomicBoolean VIBRATE= new AtomicBoolean(false);

}
