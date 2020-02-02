package com.example.tryonetask.ui.ViewPager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by Alaa Moaataz on 2020-01-22.
 */
public class PagerAdapter extends FragmentPagerAdapter {


    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();


    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

//    @NonNull
//    @Override
//    public Fragment getItem(int position) {
//
//        switch (position) {
//            case 0:
//                PopularMovieFragment popularMovieFragment = new PopularMovieFragment();
//                return popularMovieFragment;
//            case 1:
//                TopMovieFragment topMovieFragment = new TopMovieFragment();
//                return topMovieFragment;
//
//            default:
//                return null;
//        }
//
//    }

//    @Override
//    public int getCount() {
//        return mNoOfTaps;
//    }
}