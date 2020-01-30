package com.example.tryonetask.data;

import com.example.tryonetask.pojo.ListingResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieInterface {

    @GET("popular?api_key=f3e796f7e1ac95620354ed26f063ba80&language=en-US&page=1")
    public Single<ListingResponse> getPopularMovie();
//    public Single<ListingResponse> getPopularMovie(@Query("page") int page);


    @GET("top_rated?api_key=f3e796f7e1ac95620354ed26f063ba80&language=en-US&page=1")
    public Single<ListingResponse> getTopMovie();





    @GET("popular?api_key=f3e796f7e1ac95620354ed26f063ba80&language=en-US")
    public Single<ListingResponse> getNewPopularMovie( @Query("page") int pageIndex, @Query("pagesize") int pagesize );

    @GET("top_rated?api_key=f3e796f7e1ac95620354ed26f063ba80&language=en-US")
    public Single<ListingResponse> getNewTopMovie( @Query("page") int pageIndex, @Query("pagesize") int pagesize );


}
