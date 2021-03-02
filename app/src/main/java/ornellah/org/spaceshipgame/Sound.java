package ornellah.org.spaceshipgame;

import android.media.MediaPlayer;

/**
 * Created by Svetlozar Georgiev on 21/03/2017.
 */

public class Sound {
    private MediaPlayer bgMusic;
    private final MediaPlayer enemyDestroyed;
    private final MediaPlayer shoot;

    public Sound() {
        bgMusic = MediaPlayer.create(Constants.CURR_CONTEXT, R.raw.background);
        enemyDestroyed = MediaPlayer.create(Constants.CURR_CONTEXT, R.raw.explosion);
        shoot = MediaPlayer.create(Constants.CURR_CONTEXT, R.raw.shoot2);
    }

    public void playBg() {
        if(Constants.SOUND)
            bgMusic.start();
    }

    public void stopBg() {
        bgMusic.stop();
    }

    public void playExplosion() {
        if(Constants.SOUND)
            enemyDestroyed.start();
    }

    public void playShoot() {
        shoot.start();
    }

}
