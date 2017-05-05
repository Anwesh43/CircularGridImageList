package com.anwesome.ui.circulargridimagelist;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by anweshmishra on 05/05/17.
 */
public class CircularGridImageList  {
    private Activity activity;
    private ScrollView scrollView;
    private CircularGridImageLayout circularGridImageLayout;
    private boolean isShown = false;
    public CircularGridImageList(Activity activity) {
        this.activity = activity;
        scrollView = new ScrollView(activity);
        circularGridImageLayout = new CircularGridImageLayout(activity);
        scrollView.addView(circularGridImageLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
    public void addImage(Bitmap bitmap,OnClickListener onClickListener) {
        if(!isShown) {
            circularGridImageLayout.addImage(bitmap,onClickListener);
        }
    }
    public void show() {
        if(!isShown) {
            activity.setContentView(scrollView);
        }
    }
}
