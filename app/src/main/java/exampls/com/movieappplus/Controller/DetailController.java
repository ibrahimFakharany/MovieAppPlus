package exampls.com.movieappplus.Controller;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import exampls.com.movieappplus.Model.Data.Contract;
import exampls.com.movieappplus.Model.LocalDataCall;
import exampls.com.movieappplus.Model.Movie;
import exampls.com.movieappplus.Model.NetworkCalls;
import exampls.com.movieappplus.Model.Review;

/**
 * Created by 450 G1 on 21/09/2017.
 */

public class DetailController {
    private Context context;
    private String TAG = "detailcontroller";
    TrailerReviewsListener trailerReviewsListener;

    public void setTrailerReviewsListener(TrailerReviewsListener trailerReviewsListener) {
        this.trailerReviewsListener = trailerReviewsListener;
    }

    public DetailController(Context context) {

        this.context = context;

    }

    public void callGetTrailers(int filmId) {
        NetworkCalls networkCalls = new NetworkCalls(context);
        networkCalls.getTrailers(filmId, new OnCallFinishCallback() {
            @Override
            public void onSuccess(String response) {
                initializeTrailersAdapter(response);
            }
        });
    }

    public void callGetReviews(int filmId) {
        NetworkCalls networkCalls = new NetworkCalls(context);
        networkCalls.getReviews(filmId, new OnCallFinishCallback() {
            @Override
            public void onSuccess(String response) {
                initializeRevewsAdapter(response);
            }
        });
    }

    private void initializeTrailersAdapter(String response) {
        List<String> list = cuttingTrailersJSON(response);
        Log.e("trailers list ", list.size() + "");
        MyTrailersAdapter adapter = new MyTrailersAdapter(context, list);
        trailerReviewsListener.trailerGotListener(adapter);
    }

    private void initializeRevewsAdapter(String response) {
        List<Review> list = cuttingReviewsJSON(response);
        Log.e("reviews list ", list.size() + "");
        MyReviewAdapter adapter = new MyReviewAdapter(context, list);
        trailerReviewsListener.reviewsGotListener(adapter);
    }

    private List<String> cuttingTrailersJSON(String response) {
        final ArrayList<String> strings = new ArrayList<>();
        try {

            JSONObject object = new JSONObject(response);
            JSONArray jsonArray = object.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {

                strings.add(jsonArray.getJSONObject(i).getString("key"));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return strings;
    }

    private List<Review> cuttingReviewsJSON(String response) {
        JSONObject object = null;
        ArrayList<Review> strings = new ArrayList<>();
        try {
            object = new JSONObject(response);
            JSONArray jsonArray = object.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {

                strings.add(new Review(jsonArray.getJSONObject(i).getString("author"), jsonArray.getJSONObject(i).getString("content")));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return strings;
    }



}
