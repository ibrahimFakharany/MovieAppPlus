package exampls.com.movieappplus.View;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import exampls.com.movieappplus.Controller.DetailController;
import exampls.com.movieappplus.Controller.MyReviewAdapter;
import exampls.com.movieappplus.Controller.MyTrailersAdapter;
import exampls.com.movieappplus.Controller.TrailerReviewsListener;
import exampls.com.movieappplus.Model.Data.Contract;
import exampls.com.movieappplus.Model.Movie;
import exampls.com.movieappplus.R;

public class DetailActivity extends AppCompatActivity implements TrailerReviewsListener {

    private static final String TAG = "detailactivity";
    ListView trailersLv, reviewLv;
    Button trailersBtn, reviewsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();

        try {

            Movie movie = bundle.getParcelable("movie");

            String posterImage = movie.getPosterImage();
            ((TextView) findViewById(R.id.movie_title_tv)).setText(movie.getTitle());
            Picasso.with(this).load("http://image.tmdb.org/t/p/w185/" + posterImage)
                    .into(((ImageView) findViewById(R.id.poster_movie)), new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            Log.e(TAG, "picasso finished");

                            TextView textView = (TextView) findViewById(R.id.movie_overview_tv);
                            textView.setText(movie.getOverview());
                            textView.setVisibility(View.VISIBLE);

                        }

                        @Override
                        public void onError() {

                        }
                    });
            ((TextView) findViewById(R.id.movie_date_tv)).setText(movie.getRealeseDate());
            ((TextView) findViewById(R.id.movie_vote_avg_tv)).setText(movie.getVote_Average() + " /10");
            ((Button) findViewById(R.id.add_favorit_btn)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!alreadyIsFavourit())
                        addFilmToFavourit();
                    else {

                        Toast.makeText(DetailActivity.this, "alreadyExist", Toast.LENGTH_SHORT).show();

                    }
                }


            });

        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

       /* trailersBtn = (Button) findViewById(R.id.trailer_button);
        trailersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrailerDialog();
            }
        });
        reviewsBtn = (Button) findViewById(R.id.review_button);
        reviewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReviewsDialog();
            }
        });
*/
        trailersLv = (ListView) findViewById(R.id.trailer_lv);

        reviewLv = (ListView) findViewById(R.id.review_lv);

        getTrailersReviews(((Movie) bundle.getParcelable("movie")).getId());
    }

    private void openReviewsDialog() {




    }

    private void openTrailerDialog() {


    }

    private boolean alreadyIsFavourit() {
        int id = ((Movie) getIntent().getExtras().getParcelable("movie")).getId();
        Cursor cr = getContentResolver().query(
                Contract.CONTENT_URI,
                new String[]{Contract.FavouritMoviesEntry.COLUMN_MOVIE_ID},
                Contract.FavouritMoviesEntry.COLUMN_MOVIE_ID + " = ?",
                new String[]{Integer.toString(id)},
                null);

        return (cr.getCount() > 0) ? true : false;
    }


    private void addFilmToFavourit() {
        Movie movie = getIntent().getExtras().getParcelable("movie");
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.FavouritMoviesEntry.COLUMN_MOVIE_ID, movie.getId());
        contentValues.put(Contract.FavouritMoviesEntry.COLUMN_TITLE, movie.getTitle());
        contentValues.put(Contract.FavouritMoviesEntry.COLUMN_POSTER_PATH, movie.getPosterImage());
        Log.e(TAG, (getContentResolver().insert(Contract.CONTENT_URI, contentValues)).toString());

        Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
    }

    private void getTrailersReviews(int filmId) {

        DetailController detailController = new DetailController(DetailActivity.this);
        detailController.setTrailerReviewsListener(this);
        detailController.callGetTrailers(filmId);
        detailController.callGetReviews(filmId);

    }

    @Override
    public void trailerGotListener(MyTrailersAdapter trailersAdapter) {

        trailersLv.setAdapter(trailersAdapter);

    }

    @Override
    public void reviewsGotListener(MyReviewAdapter reviewAdapter) {

        reviewLv.setAdapter(reviewAdapter);

    }
}
