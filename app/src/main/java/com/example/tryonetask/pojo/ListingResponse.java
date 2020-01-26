package com.example.tryonetask.pojo;

import java.util.List;

/**
 * Created by Alaa Moaataz on 2020-01-22.
 */
public class ListingResponse {
    public List<MovieModel> results;
    int page;
    int total_results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
