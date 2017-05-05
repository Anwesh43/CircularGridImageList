package com.anwesome.ui.circulargridimagelist;

import android.content.Context;
import android.graphics.*;
import android.view.*;

/**
 * Created by anweshmishra on 05/05/17.
 */
public class CircularImageView extends View {
    private Bitmap bitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private DrawingController drawingController = new DrawingController();
    public CircularImageView(Context context, Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
    }
    public void onDraw(Canvas canvas) {
        drawingController.draw(canvas,paint);
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
    public void update(float factor) {
        postInvalidate();
    }
    private class DrawingController {
        private int render = 0;
        public void draw(Canvas canvas,Paint paint) {
            int r = canvas.getWidth()/2;
            if(render == 0) {
                bitmap = Bitmap.createScaledBitmap(bitmap,r*2,r*2,true);
            }
            canvas.save();
            canvas.translate(r,r);
            Path path = new Path();
            path.addCircle(0,0,r*4/5, Path.Direction.CCW);
            paint.setColor(Color.WHITE);
            canvas.drawCircle(0,0,r,paint);
            canvas.clipPath(path);
            canvas.drawBitmap(bitmap,-bitmap.getWidth()/2,-bitmap.getHeight()/2,paint);
            canvas.restore();
            render++;
        }
    }
}
