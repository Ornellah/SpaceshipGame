package sgeorgiev.org.spaceshipgame;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Svetlozar Georgiev on 17/03/2017.
 */

public interface GameObject {
    public void update(int speed);
    public void draw(Canvas canvas, Paint paint);
}
