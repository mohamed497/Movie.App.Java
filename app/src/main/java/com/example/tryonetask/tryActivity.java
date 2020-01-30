package com.example.tryonetask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.Client;
import com.example.tryonetask.R;
import com.example.tryonetask.data.MovieInterface;
import com.example.tryonetask.pojo.ListingResponse;
import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.ui.main.MovieAdapter;

import java.util.List;

public class tryActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    MovieAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    RecyclerView rv;
    ProgressBar progressBar;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private int TOTAL_PAGES = 5;
    private int currentPage = PAGE_START;

    private MovieInterface movieService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try);

//        rv =  findViewById(R.id.main_recycler);
//        progressBar = findViewById(R.id.main_progress);
//
//        adapter = new MovieAdapter(this);
//
//        // rv.setLayoutManager(new GridLayoutManager(this, 2));
//        //linearLayoutManager = new GridLayoutManager(this, 2);
//
//        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        rv.setLayoutManager(linearLayoutManager);
//
//        rv.setItemAnimator(new DefaultItemAnimator());
//
//        rv.setAdapter(adapter);
//
//        rv.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
//            @Override
//            public void loadMoreItems() {
//                isLoading = true;
//                currentPage += 1;
//
//                // mocking network delay for API call
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        loadNextPage();
//                    }
//                }, 1000);
//            }
//
//            @Override
//            public int getTotalPageCount() {
//                return TOTAL_PAGES;
//            }
//
//            @Override
//            public boolean isLastPage() {
//                return isLastPage;
//            }
//
//            @Override
//            public boolean isLoading() {
//                return isLoading;
//            }
//        });
//
//        //init service and load data
//        movieService = Client.getClient().create(MovieInterface.class);
//
//        loadFirstPage();

    }


//    private void loadFirstPage() {
//        Log.d(TAG, "loadFirstPage: ");
//
//        callTopRatedMoviesApi().enqueue(new Callback<ListingResponse>() {
//            @Override
//            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
//                // Got data. Send it to adapter
//
//                List<Movie> results = fetchResults(response);
//                progressBar.setVisibility(View.GONE);
//                adapter.addAll(results);
//
//                if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
//                else isLastPage = true;
//            }
//
//            @Override
//            public void onFailure(Call<MoviesResponse> call, Throwable t) {
//                t.printStackTrace();
//
//            }
//        });
//
//    }
//
//    /**
//     * @param response extracts List<{@link Movie>} from response
//     * @return
//     */
//    private List<MovieModel> fetchResults(Response<ListingResponse> response) {
//        ListingResponse topRatedMovies = response.body();
//        return topRatedMovies.getResults();
//    }
//
//    private void loadNextPage() {
//        Log.d(TAG, "loadNextPage: " + currentPage);
////
////        Callback<ListingResponse>() {
////            @Override
////            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
////                adapter.removeLoadingFooter();
////                adapter.removeLoadingFooter();
////
////                List<Movie> results = fetchResults(response);
////                adapter.addAll(results);
////
////                if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
////                else isLastPage = true;
////            }
////
////            @Override
////            public void onFailure(Call<MoviesResponse> call, Throwable t) {
////                t.printStackTrace();
////                // TODO: 08/11/16 handle failure
////            }
////        }
//        callTopRatedMoviesApi().enqueue(new Callback<ListingResponse>() {
//            @Override
//            public void onResponse(Call<ListingResponse> call, Response<ListingResponse> response) {
//                adapter.removeLoadingFooter();
//                adapter.removeLoadingFooter();
//                List<MovieModel> results = fetchResults(response);
////                adapter.addAll(results);
//            }
//
//            @Override
//            public void onFailure(Call<ListingResponse> call, Throwable t) {
//
//            }
//        });
//    }
//
//
//    /**
//     * Performs a Retrofit call to the top rated movies API.
//     * Same API call for Pagination.
//     * As {@link #currentPage} will be incremented automatically
//     * by @{@link PaginationScrollListener} to load next page.
//     */
//    private Call<ListingResponse> callTopRatedMoviesApi() {
//        return movieService.getNewPopularMovie(
//                "f3e796f7e1ac95620354ed26f063ba",
//                currentPage
//        );
//    }
}
