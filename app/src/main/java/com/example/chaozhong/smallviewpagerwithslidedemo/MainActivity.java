package com.example.chaozhong.smallviewpagerwithslidedemo;

import android.graphics.PointF;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.chaozhong.smallviewpagerwithslidedemo.fragments.CustomView;
import com.example.chaozhong.smallviewpagerwithslidedemo.fragments.MyFragment;

public class MainActivity extends AppCompatActivity {
    public static MainActivity sActivity = null;

    private CustomView mCustomView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCustomView = (CustomView) ((ViewStub) findViewById(R.id.custom_view_stub)).inflate();
        mCustomView.setClickable(false);
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new MyFragment(), "fragment").commit();
        sActivity = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sActivity = null;
    }

    public void clearView() {
        mCustomView.setVisibility(View.INVISIBLE);
    }

    public void updateViewPos(float x, float y) {
        int locations[] = new int[2];
        ((View) mCustomView.getParent()).getLocationOnScreen(locations);
        locations[0] += ((View) mCustomView.getParent()).getPaddingLeft();
        locations[1] += ((View) mCustomView.getParent()).getPaddingTop();
        Log.i("GOHAN", String.format("updateViewPos %f %f", x, y));
        ((RelativeLayout.LayoutParams) mCustomView.getLayoutParams()).leftMargin = (int) x - locations[0];
        ((RelativeLayout.LayoutParams) mCustomView.getLayoutParams()).topMargin = (int) y - locations[1];
        mCustomView.requestLayout();
    }

    public void updateViewSize(int width, int height, float x, float y) {
        int locations[] = new int[2];
        ((View) mCustomView.getParent()).getLocationOnScreen(locations);
        locations[0] += ((View) mCustomView.getParent()).getPaddingLeft();
        locations[1] += ((View) mCustomView.getParent()).getPaddingTop();
        Log.i("GOHAN", String.format("updateViewSize %d %d %f %f", width, height, x, y));
        mCustomView.getLayoutParams().width = width;
        mCustomView.getLayoutParams().height = height;
        ((RelativeLayout.LayoutParams) mCustomView.getLayoutParams()).leftMargin = (int) x - locations[0];
        ((RelativeLayout.LayoutParams) mCustomView.getLayoutParams()).topMargin = (int) y - locations[1];

        mCustomView.requestLayout();
        mCustomView.setVisibility(View.VISIBLE);
    }
}
