package com.anwesome.ui.circulargridimagelist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 05/05/17.
 */
public class CircularGridImageLayout extends ViewGroup {
    private int w,h,gap;
    public void onMeasure(int wspec,int hspec) {
        int newH = gap;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            measureChild(child,wspec,hspec);
            if(i%3 == 0) {
                newH += 2*gap;
            }
        }
        if(getChildCount()%3 != 0){
            newH += 2*gap;
        }
        newH+=gap;
        setMeasuredDimension(w,Math.max(newH,h));
    }
    public void onLayout(boolean reloaed,int a,int b,int w,int h) {
        int x = gap,y = gap;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            if(i%3 == 2) {
                child.layout(x,y+gap/2,x+gap,y+gap/2+gap);
            }
            else {
                child.layout(x, y, x + gap, y + gap);
            }
            if(i%3 == 0) {
                x = gap;
                y += 2*gap;
            }
            else {
                x+=2*gap;
            }
        }
    }
    public void addImage(Bitmap bitmap) {
        CircularImageView circularImageView = new CircularImageView(getContext(),bitmap);
        addView(circularImageView,new LayoutParams(gap,gap));
        requestLayout();
    }
    public CircularGridImageLayout(Context context) {
        super(context);
        initWH(context);
    }
    public void initWH(Context context) {
        DisplayManager displayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        Point size = new Point();
        display.getRealSize(size);
        w = size.x;
        h = size.y;
        gap = w/7;
    }
}
