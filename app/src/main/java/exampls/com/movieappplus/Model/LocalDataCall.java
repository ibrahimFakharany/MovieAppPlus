package exampls.com.movieappplus.Model;

import android.content.Context;
import android.database.Cursor;

import exampls.com.movieappplus.Model.Data.Contract;

/**
 * Created by 450 G1 on 21/09/2017.
 */

public class LocalDataCall {

    Context context;

    public LocalDataCall(Context context) {
        this.context = context;
    }

    public Cursor getFavourit() {
        Cursor cursor = context.getContentResolver()
                .query(Contract.CONTENT_URI
                        , null
                        , null
                        , null
                        , null);

        return cursor;
    }

}
