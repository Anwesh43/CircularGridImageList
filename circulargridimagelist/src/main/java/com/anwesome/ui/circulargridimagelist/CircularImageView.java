package com.anwesome.ui.circulargridimagelist;

import android.animation.ValueAnimator;
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
    private AnimationHandler animationHandler  = new AnimationHandler();
    public CircularImageView(Context context, Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
        init();
    }
    public void init() {
        setScaleX(0.5f);
        setScaleY(0.5f);
    }
    public void onDraw(Canvas canvas) {
        drawingController.draw(canvas,paint);
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if(getScaleX() >= 1) {
                animationHandler.end();
            }
            else if (getScaleX()<=0.5f) {
                animationHandler.start();
            }
        }
        return true;
    }
    public void update(float factor) {
        float scale = 0.5f +0.5f*factor;
        setScaleX(scale);
        setScaleY(scale);
        setRotation(360*factor);
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
    private class AnimationHandler implements ValueAnimator.AnimatorUpdateListener{
        private ValueAnimator startAnimator = ValueAnimator.ofFloat(0,1),endAnimator = ValueAnimator.ofFloat(1,0);
        public AnimationHandler() {
            startAnimator.setDuration(1000);
            endAnimator.setDuration(1000);
            startAnimator.addUpdateListener(this);
            endAnimator.addUpdateListener(this);

        }
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float factor = (float)(valueAnimator.getAnimatedValue());
            update(factor);
        }
        public void start() {
            startAnimator.start();
        }
        public void end() {
            endAnimator.end();
        }
    }
}
