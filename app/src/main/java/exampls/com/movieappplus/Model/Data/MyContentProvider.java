package exampls.com.movieappplus.Model.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by 450 G1 on 21/09/2017.
 */

public class MyContentProvider extends ContentProvider {
    Context context;
    MyDatabase database;


    @Override
    public boolean onCreate() {

        context = getContext();
        database = new MyDatabase(context);

        return true;

    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = database.getReadableDatabase();

        Cursor cursor = db.query(
                Contract.FavouritMoviesEntry.TABLE_NAME
                , projection
                , selection
                , selectionArgs
                , null
                , null
                , sortOrder);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = database.getWritableDatabase();
        long i = db.insert(Contract.FavouritMoviesEntry.TABLE_NAME,null, values );
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(Contract.CONTENT_URI, i);

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
