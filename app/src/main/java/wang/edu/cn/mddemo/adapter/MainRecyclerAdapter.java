package wang.edu.cn.mddemo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import wang.edu.cn.mddemo.MainActivity;
import wang.edu.cn.mddemo.R;


/**
 * Created by wangdechang on 2016/3/9.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>{
    private static final String TAG = "MainRecyclerAdapter";
    private Context mContext;
    private LayoutInflater mInflater = null;
    private List<Map<String, Object>> list = null;

    public MainRecyclerAdapter(Context mContext, List<Map<String, Object>> list) {
        this.list = list;
        this.mContext = mContext;
        mInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public MainRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recyclerview_dummyfragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainRecyclerAdapter.ViewHolder viewHolder, int position) {
        viewHolder.mContext = mContext;
        viewHolder.textView_item_title.setText(list.get(position).get("title") + "");
        viewHolder.textView_item_teacher.setText("讲师：" + list.get(position).get("teacher") + "");
        viewHolder.textView_item_desc.setText(list.get(position).get("desc") + "");
        viewHolder.textView_item_star.setText("难度系数：" + list.get(position).get("star") + "");
        viewHolder.imageView_item_icon.setImageDrawable((Drawable) list.get(position).get("image"));
        viewHolder.ratingBar_item.setRating(Integer.parseInt(list.get(position).get("star") + ""));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView_item_icon;
        public RatingBar ratingBar_item;
        public TextView textView_item_title;
        public TextView textView_item_desc;
        public TextView textView_item_teacher;
        public TextView textView_item_star;
        public Button button_item_subscribe;
        public Context mContext;
        public ViewHolder(View view) {
            super(view);
            imageView_item_icon = (ImageView) view.findViewById(R.id.imageView_item_icon);
            textView_item_title = (TextView) view.findViewById(R.id.textView_item_title);
            textView_item_desc = (TextView) view.findViewById(R.id.textView_item_desc);
            textView_item_teacher = (TextView) view.findViewById(R.id.textView_item_teacher);
            textView_item_star = (TextView) view.findViewById(R.id.textView_item_star);
            button_item_subscribe = (Button) view.findViewById(R.id.button_item_subscribe);
            ratingBar_item = (RatingBar) view.findViewById(R.id.ratingBar_item);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) mContext).startActivity(v, getPosition());
                }
            });
        }

    }
}
