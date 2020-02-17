package com.example.tryonetask.tryCache;

import android.content.Context;
import android.os.AsyncTask;

import com.example.tryonetask.pojo.TopMovieModel;
import com.example.tryonetask.tryPaging.TopDataSource;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Created by Alaa Moaataz on 2020-02-11.
 */

@Database(entities = {TopMovieModel.class}, version = 1, exportSchema = false)

public abstract class TopMovieDatabase extends RoomDatabase {


    private static TopMovieDatabase INSTANCE;
    public abstract TopMovieDao MovieDao();

    static TopMovieDatabase getDataBase(final Context context){
        if (INSTANCE == null){
            synchronized (TopMovieDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TopMovieDatabase.class, "topMovie_db")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback =
            new Callback(){
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDBAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void> {

        private final TopMovieDao movieDao;

        PopulateDBAsync(TopMovieDatabase db) {
            movieDao = db.MovieDao();
        }


        @Override
        protected Void doInBackground(final Void... params) {

//            movieDao.deleteAllTopMovies();

            if (TopDataSource.getTopMoviesToDB != null){
                movieDao.insertTopMovies(TopDataSource.getTopMoviesToDB);
            }


            return null;
        }
    }
}
