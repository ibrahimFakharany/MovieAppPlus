package exampls.com.movieappplus.Model.Data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by 450 G1 on 21/09/2017.
 */

public class Contract {
    static String CONTENT_AUTHORITY = "exampls.com.movieappplus.Model.Data";
    public static Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    public static String PATH_FAVOURIT = "movies";

    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVOURIT).build();

    public class FavouritMoviesEntry implements BaseColumns {
        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_POSTER_PATH = "poster_path";
    }
}
