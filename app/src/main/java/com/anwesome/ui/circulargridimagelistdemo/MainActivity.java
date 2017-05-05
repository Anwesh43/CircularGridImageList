package com.anwesome.ui.circulargridimagelistdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.anwesome.ui.circulargridimagelist.CircularGridImageList;
import com.anwesome.ui.circulargridimagelist.OnClickListener;

public class MainActivity extends AppCompatActivity {
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(bitmap == null) {
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.stp2);
        }
        CircularGridImageList circularGridImageList = new CircularGridImageList(this);
        for(int i=0;i<21;i++) {
            final int index = i+1;
            circularGridImageList.addImage(bitmap, new OnClickListener() {
                @Override
                public void onClick() {
                    Toast.makeText(MainActivity.this, index + " clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
        circularGridImageList.show();
    }
}
