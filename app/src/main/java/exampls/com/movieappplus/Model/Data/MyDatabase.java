package exampls.com.movieappplus.Model.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 450 G1 on 21/09/2017.
 */

public class MyDatabase extends SQLiteOpenHelper {
    public static final String dbName = "movieDB";
    public static final String tableName = "favourit";
    public static final int version = 1;
    public MyDatabase(Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_statement = "create table " +Contract.FavouritMoviesEntry.TABLE_NAME +" ( "+
                Contract.FavouritMoviesEntry._ID +"INTEGER PRIMARY KEY ,  "+
                Contract.FavouritMoviesEntry.COLUMN_MOVIE_ID +" INTEGER NOT NULL , "+
                Contract.FavouritMoviesEntry.COLUMN_TITLE + " TEXT NOT NULL , "+
                Contract.FavouritMoviesEntry.COLUMN_POSTER_PATH+" TEXT NOT NULL );";

        db.execSQL(create_statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+Contract.FavouritMoviesEntry.TABLE_NAME);
        onCreate(db);
    }
}
