package com.example.user.test;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 2016-12-22.
 */

public class CustomAdapter extends FragmentPagerAdapter {

//    LayoutInflater inflater;
//
//    public CustomAdapter(LayoutInflater inflater) {
//        this.inflater = inflater;
//    }
//
//    public int getCount() {
//        // TODO Auto-generated method stub
//        return 2;
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        // TODO Auto-generated method stub
//        View view = null;
//        switch(position) {
//            case 0:
//                view = inflater.inflate(R.layout.activity_first_main, null);
//                break;
//
//            case 1:
//                view = inflater.inflate(R.layout.activity_second_main, null);
//                break;
//        }
//        container.addView(view);
//        return view;
//    }
//
////    public interface OnTouchListener {
////        boolean OnTouchListener();
////    }
////
////    public boolean setOnTouchListener(OnTouchListener callback) {
////        ontouchlistener = callback;
////        return false;
////    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        // TODO Auto-generated method stub
//        container.removeView((View)object);
//    }
//
//    @Override
//    public boolean isViewFromObject(View v, Object obj) {
//        // TODO Auto-generated method stub
//        return v==obj;
//    }
private static final int NUM_OF_ITEMS = 2;
    private final FragmentManager mFragmentManager;

    public CustomAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new MapActivity();
        } else {
            fragment = new listActivity();
        }
        return fragment;
//            return ArrayListFragment.newInstance(position);


    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return NUM_OF_ITEMS;
    }

}
