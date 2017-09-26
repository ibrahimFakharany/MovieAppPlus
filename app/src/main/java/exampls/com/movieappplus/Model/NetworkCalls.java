package exampls.com.movieappplus.Model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import exampls.com.movieappplus.Controller.OnCallFinishCallback;

/**
 * Created by 450 G1 on 21/09/2017.
 */

public class NetworkCalls {
    Context context;
    public final String TAG = "networkcalls";



    public NetworkCalls(Context context) {
        this.context = context;
    }

    public void getMovies(String category, final OnCallFinishCallback callback){
        String URL = "http://api.themoviedb.org/3/movie/"+category+"?api_key=98424b94af97868ad653ab4fc3f9b912";
        Response.Listener listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String  response) {
                callback.onSuccess(response);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };

        StringRequest stringRequest  = new StringRequest(Request.Method.GET, URL,  listener, errorListener);

        SingleTon.getInstance(context).addRequest(stringRequest);
    }

    public void getTrailers(int id, OnCallFinishCallback callback){
        String URL = "http://api.themoviedb.org/3/movie/" + id +"/videos?api_key=98424b94af97868ad653ab4fc3f9b912";
        Response.Listener listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };

        StringRequest stringRequest  = new StringRequest(Request.Method.GET, URL,  listener, errorListener);

        SingleTon.getInstance(context).addRequest(stringRequest);

    }

    public void getReviews(int id, OnCallFinishCallback callback){
        String URL = "http://api.themoviedb.org/3/movie/" + id +"/reviews?api_key=98424b94af97868ad653ab4fc3f9b912";

        Response.Listener listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };

        StringRequest stringRequest  = new StringRequest(Request.Method.GET, URL,  listener, errorListener);

        SingleTon.getInstance(context).addRequest(stringRequest);
    }



}
