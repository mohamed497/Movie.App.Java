package com.example.tryonetask.ui.main;

import android.os.Bundle;

import com.example.tryonetask.R;
import com.example.tryonetask.ui.ViewPager.PagerAdapter;
import com.example.tryonetask.ui.ViewPager.PopularMovieFragment;
import com.example.tryonetask.ui.ViewPager.TopMovieFragment;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity  {

//    MovieViewModel movieViewModel;
//    MovieViewModel topMovieViewModel;

    private static final String TAG = "MainActivity";

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.view_pager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tablLayOut);
        tabLayout.setupWithViewPager(mViewPager);
//
//        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container ,
//                new BaseFragment()).commit();

    }

    private void setupViewPager(ViewPager viewPager){
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PopularMovieFragment(),"Popular Movies");
        adapter.addFragment(new TopMovieFragment(),"Top Movies");
        viewPager.setAdapter(adapter);
    }

}
