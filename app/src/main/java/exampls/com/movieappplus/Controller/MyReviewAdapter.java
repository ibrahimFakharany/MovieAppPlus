package exampls.com.movieappplus.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import exampls.com.movieappplus.Model.Review;
import exampls.com.movieappplus.R;

/**
 * Created by 450 G1 on 21/09/2017.
 */

public class MyReviewAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<Review> list;

    public MyReviewAdapter(Context context, List<Review> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.review_item, parent, false);

        } else {

            ((TextView) convertView.findViewById(R.id.name_tv)).setText(list.get(position).getReviewName());
            ((TextView) convertView.findViewById(R.id.review_tv)).setText(list.get(position).getReview());

        }

        return convertView;
    }
}
