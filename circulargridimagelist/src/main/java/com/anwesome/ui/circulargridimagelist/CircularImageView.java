package com.anwesome.ui.circulargridimagelist;

import android.content.Context;
import android.graphics.*;
import android.view.*;

/**
 * Created by anweshmishra on 05/05/17.
 */
public class CircularImageView extends View {
    private Bitmap bitmap;
    public CircularImageView(Context context, Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
    }
    public void onDraw(Canvas canvas) {

    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
    public void update(float factor) {
        postInvalidate();
    }
}
