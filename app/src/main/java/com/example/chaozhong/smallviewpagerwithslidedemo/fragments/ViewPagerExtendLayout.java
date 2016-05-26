package com.example.chaozhong.smallviewpagerwithslidedemo.fragments;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by chaozhong on 5/27/16.
 */
public class ViewPagerExtendLayout extends LinearLayout {
    ViewPager mViewPager;
    private Point mCenter = new Point();
    private Point mInitialTouch = new Point();
    public ViewPagerExtendLayout(Context context) {
        super(context);
    }

    public ViewPagerExtendLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPagerExtendLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setViewPagerId(@IdRes int id) {
        mViewPager = (ViewPager) findViewById(id);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenter.x = w / 2;
        mCenter.y = h / 2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //We capture any touches not already handled by the ViewPager
        // to implement scrolling from a touch outside the pager bounds.
        if (mViewPager == null) {
            return super.onTouchEvent(ev);
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mInitialTouch.x = (int)ev.getX();
                mInitialTouch.y = (int)ev.getY();
            default:
                ev.offsetLocation(mCenter.x - mInitialTouch.x, mCenter.y - mInitialTouch.y);
                break;
        }
        return mViewPager.dispatchTouchEvent(ev);
    }
}
