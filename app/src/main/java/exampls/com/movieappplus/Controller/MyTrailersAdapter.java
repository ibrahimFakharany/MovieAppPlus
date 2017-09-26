package exampls.com.movieappplus.Controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import exampls.com.movieappplus.R;

/**
 * Created by 450 G1 on 21/09/2017.
 */

public class MyTrailersAdapter extends BaseAdapter{

    Context context;
    List<String> list;
    LayoutInflater inflater;

    public MyTrailersAdapter(Context context, List<String> list) {
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

        if (convertView == null){

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.trailer_item, parent, false);

        }

        TextView textView = (TextView) convertView.findViewById(R.id.tv_number);
        textView.setText( ( position + 1 ) +"");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.youtube.com/watch?v="+list.get(position)));
                context.startActivity(intent);
            }
        });

        return convertView;

    }
}
