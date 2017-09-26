package exampls.com.movieappplus.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import exampls.com.movieappplus.Model.Movie;
import exampls.com.movieappplus.R;

/**
 * Created by 450 G1 on 21/09/2017.
 */

public class FavouritAdapter  extends RecyclerView.Adapter<FavouritAdapter.MyFavouritViewHolder> {
    LayoutInflater inflater;
    Context context;
    List<Movie> list;

    public FavouritAdapter(Context context, List<Movie> list) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    @Override
    public MyFavouritViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.favourit_item, parent, false);
        return new MyFavouritViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyFavouritViewHolder holder, int position) {
        holder.titleTv.setText(list.get(position).getTitle());
        holder.idTv.setText(list.get(position).getId()+"");
        Picasso.with(context).load("http://image.tmdb.org/t/p/w185/"+list.get(position).getPosterImage()).into(holder.posterIv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyFavouritViewHolder extends RecyclerView.ViewHolder{

        TextView idTv, titleTv;
        ImageView posterIv;

        public MyFavouritViewHolder(View itemView) {
            super(itemView);

            idTv = (TextView) itemView.findViewById(R.id.id_favourit);
            titleTv = (TextView) itemView.findViewById(R.id.title_favourit);
            posterIv = (ImageView) itemView.findViewById(R.id.poster_favourit);

        }
    }

}
