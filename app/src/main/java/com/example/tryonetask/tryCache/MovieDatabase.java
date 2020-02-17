package com.example.tryonetask.tryCache;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.tryPaging.ItemDataSource;
import com.example.tryonetask.ui.ViewPager.PopularMovieFragment;
import com.example.tryonetask.ui.main.MainActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Created by Alaa Moaataz on 2020-02-02.
 */

@Database(entities = {MovieModel.class},version = 1,exportSchema = false)

public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase INSTANCE;

    public abstract MovieDao MovieDao();

    static MovieDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (MovieDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, "movie_db")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void , Void , Void> {

        private final MovieDao movieDao;

        PopulateDbAsync(MovieDatabase db){
            movieDao = db.MovieDao();
        }



        @Override
        protected Void doInBackground(final Void... params) {


            if(ItemDataSource.getMoviesToDB!= null){

                Log.d("zxc","" + ItemDataSource.getMoviesToDB);
            movieDao.insertMovies(ItemDataSource.getMoviesToDB);
            }
             LiveData<List<MovieModel>>  x =  movieDao.getMovies();


            //\\ //\\ //\\
//            try {
//                Thread.sleep(10000);
//                movieDao.deleteAllMovies();
//
//                movieDao.getMovies();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            return null;
        }
    }


}
