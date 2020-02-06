package com.example.tryonetask.Detalis.favorite_movie;

import android.provider.BaseColumns;

/**
 * Created by Alaa Moaataz on 2020-02-05.
 */
public class FavoriteContract {

    public static final class FavoriteEntry implements BaseColumns{



        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_MOVIEID = "movieid";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POSTER_PATH = "posterpath";
        public static final String COLUMN_PLOT_SYNOPSIS = "overview";



    }

}
