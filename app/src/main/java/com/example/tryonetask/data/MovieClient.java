package com.example.tryonetask.data;

import com.example.tryonetask.pojo.ListingResponse;
import com.example.tryonetask.pojo.MovieModel;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieClient {
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    public static MovieInterface movieInterface;
    private static MovieClient INSTANCE;
    int mCurrentPage = 1;

    public MovieClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        request.url().newBuilder().addQueryParameter("API_KEY", "f3e796f7e1ac95620354ed26f063ba80")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        movieInterface = retrofit.create(MovieInterface.class);

    }

    public static MovieClient getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new MovieClient();
        }
        return INSTANCE;
    }


//    public Single<List<MovieModel>> getPopularMovies() {
//        return movieInterface.getPopularMovie()
//                .map(new Function<ListingResponse, List<MovieModel>>() {
//                    @Override
//                    public List<MovieModel> apply(ListingResponse listingResponse) throws Exception {
//                        return listingResponse.results;
//                    }
//                }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
    public Single<List<MovieModel>> getPopularMovies() {
//        mCurrentPage = mCurrentPage + 1;
        return movieInterface.getPopularMovie()
                .map(new Function<ListingResponse, List<MovieModel>>() {
                    @Override
                    public List<MovieModel> apply(ListingResponse listingResponse) throws Exception {
                        return listingResponse.results;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Single<List<MovieModel>> getTopMovies() {
        return movieInterface.getTopMovie()
                .map(new Function<ListingResponse, List<MovieModel>>() {
                    @Override
                    public List<MovieModel> apply(ListingResponse listingResponse) throws Exception {
                        return listingResponse.results;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
