package exampls.com.movieappplus.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import exampls.com.movieappplus.Model.Movie;
import exampls.com.movieappplus.R;

/**
 * Created by 450 G1 on 21/09/2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>{
    private Context context;
    private List<Movie> list;
    private LayoutInflater inflater;
    private OnImageViewClickListener onImageViewClickListener;

    public void setOnImageViewClickListener(OnImageViewClickListener onImageViewClickListener) {
        this.onImageViewClickListener = onImageViewClickListener;
    }

    public MyRecyclerViewAdapter(Context context, List<Movie> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_main_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.imageView.setOnClickListener((v) -> {
            onImageViewClickListener.onClick(list.get(position));
        });

        Picasso.with(context).load("http://image.tmdb.org/t/p/w185/"+list.get(position).getPosterImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view_main);
        }
    }
}
