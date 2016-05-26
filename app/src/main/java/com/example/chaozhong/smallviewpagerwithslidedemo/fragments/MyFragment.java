package com.example.chaozhong.smallviewpagerwithslidedemo.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.chaozhong.smallviewpagerwithslidedemo.R;

/**
 * Created by chaozhong on 5/25/16.
 */
public class MyFragment extends DialogFragment implements ViewPager.OnPageChangeListener {

    ViewPager mViewPager;
    PagerAdapter mViewPagerAdapter;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        LinearLayout view = (LinearLayout) v;

        mViewPagerAdapter = new MyPagerAdapter();
        mViewPager = (ViewPager) v.findViewById(R.id.view_pager);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setPageMargin(100);
        mViewPager.addOnPageChangeListener(this);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mViewPager.removeOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        CustomView.sActivePosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            MyViewHolder holder = (MyViewHolder) object;
            return holder.view == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            CustomView view = (CustomView) LayoutInflater.from(container.getContext()).inflate(R.layout.item_viewpager, container, false);
            view.setPosition(position);
            container.addView(view);
            return new MyViewHolder(view);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            MyViewHolder holder = (MyViewHolder) object;
            container.removeView(holder.view);
        }
    }

    public static class MyViewHolder {
        private final View view;
        public MyViewHolder(View v) {
            this.view = v;
        }
    }

}
