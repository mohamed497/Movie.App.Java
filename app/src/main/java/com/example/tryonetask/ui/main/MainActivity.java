package com.example.tryonetask.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.tryonetask.Detalis.SingleMovieActivity;
import com.example.tryonetask.Detalis.view_pager.DetailsFragment;
import com.example.tryonetask.R;
import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.tryCache.RoomViewModel;
import com.example.tryonetask.tryPaging.ItemAdapter;
import com.example.tryonetask.tryPaging.ItemViewModel;
import com.example.tryonetask.ui.ViewPager.PagerAdapter;
import com.example.tryonetask.ui.ViewPager.PopularMovieFragment;
import com.example.tryonetask.ui.ViewPager.TopMovieFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "MainActivity";

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    MovieModel movieModel;
    private DrawerLayout drawerLayout;
    int movieId;
    String movieTitle,overView,poster,imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        boolean mIsDualPane = false;

        drawerLayout = findViewById(R.id.drawer_layout);
        if(drawerLayout != null){
            mIsDualPane = drawerLayout.getVisibility() == View.VISIBLE;
        }

        if (mIsDualPane){

            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }

            if (savedInstanceState == null){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PopularMovieFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_popularMovie);
            }

//            Toast.makeText(MainActivity.this, "TABLET", Toast.LENGTH_SHORT).show();

            Toolbar toolbar = findViewById(R.id.toolbarr);
            setSupportActionBar(toolbar);


            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout  , toolbar,
                    R.string.navigation_drawer_open, R.string.navigation_drawer_close);

            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

//            FrameLayout frameLayout = findViewById(R.id.largeDetails);

            Intent intentThatStartedThisActivity = getIntent();
            if (intentThatStartedThisActivity.hasExtra("movie")){
                movieModel = getIntent().getParcelableExtra("movie");
                movieId = movieModel.id;
                movieTitle = movieModel.getTitle();
                poster = movieModel.getPoster_path();
                overView = movieModel.getOverview();
                Log.d("zxc","moview OverView : " +overView);
                Log.d("zxc","movie title : "+movieTitle);
                Log.d("zxc","movie id : "+movieId);


            }
            else{
                Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show();
            }

            Bundle bundle = new Bundle();
            bundle.putString("MOVIE_TITLE", movieTitle);
            bundle.putString("MOVIE_OVERVIEW", overView);
            bundle.putString("MOVIE_POSTER", poster);
            bundle.putInt("MOVIE_ID",movieId);
            bundle.putParcelable("MOVIE",movieModel);
            DetailsFragment myFrag = new DetailsFragment();
            myFrag.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.largeDetails,myFrag,"TagName").addToBackStack(null).commit();
//            getSupportFragmentManager().beginTransaction().replace(R.id.largeDetails,
//                    new DetailsFragment()).commit();

        }
        else{
            mPagerAdapter = new PagerAdapter(getSupportFragmentManager());

            mViewPager = findViewById(R.id.view_pager);
            setupViewPager(mViewPager);

            TabLayout tabLayout = findViewById(R.id.tablLayOut);
            tabLayout.setupWithViewPager(mViewPager);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_popularMovie:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PopularMovieFragment()).commit();
                break;
            case R.id.nav_topMovie:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TopMovieFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupViewPager(ViewPager viewPager){
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PopularMovieFragment(),"Popular Movies");
        adapter.addFragment(new TopMovieFragment(),"Top Movies");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}
