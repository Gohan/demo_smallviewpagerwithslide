package com.example.chaozhong.smallviewpagerwithslidedemo.fragments;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.example.chaozhong.smallviewpagerwithslidedemo.MainActivity;

/**
 * Created by chaozhong on 5/25/16.
 */
public class CustomView extends FrameLayout implements GestureDetector.OnGestureListener {
    GestureDetectorCompat mCompat;
    FrameLayout mRootView = null;
    PointF mDownPoint = null;
    PointF mCurrentPoint = null;
    private int mPosition;
    public static int sActivePosition;

    public CustomView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setClickable(true);
        mCompat = new GestureDetectorCompat(getContext(), this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isClickable() && sActivePosition == mPosition) {
            mCompat.onTouchEvent(event);
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_UP:
                    Log.i("GOHAN", "onUp");
                    mDownPoint = null;
                    setAlpha(1);
                    MainActivity.sActivity.clearView();
                    break;
                case MotionEvent.ACTION_CANCEL:
                    Log.i("GOHAN", "onCancel");
                    mDownPoint = null;
                    setAlpha(1);
                    MainActivity.sActivity.clearView();
                    break;

            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.i("GOHAN", "onDown");
        mDownPoint = new PointF(e.getX(), e.getY());
        int locations[] = new int[2];
        getLocationOnScreen(locations);

        MainActivity.sActivity.updateViewSize(getWidth(), getHeight(), locations[0], locations[1]);
        setAlpha(0);
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.i("GOHAN", "onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.i("GOHAN", "onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.i("GOHAN", "onScroll");
        if (Math.abs(e2.getY() - e1.getY()) > Math.abs(e2.getX() - e1.getX())) {
            mCurrentPoint = new PointF(e2.getX(), e2.getY());
            int locations[] = new int[2];
            getLocationOnScreen(locations);
            MainActivity.sActivity.updateViewPos(locations[0], locations[1] + (mCurrentPoint.y - mDownPoint.y));
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.i("GOHAN", "onFling");
        return false;
    }

    public void setPosition(int position) {
        this.mPosition = position;
    }
}
