package com.recyclerviewtest.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.recyclerviewtest.R;
import com.recyclerviewtest.bean.News;

import java.util.ArrayList;

/**
 * Created by asus on 2017/3/21.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private ArrayList<News.result> list;
    private final Context context;
    private final LayoutInflater inflater;

    public MyAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }



    public void addData(ArrayList<News.result> list){
        this.list = list;
        notifyDataSetChanged();
    }
    /**
     * 创建Holder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.list_item_layout,parent,false);
        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.title.setText(list.get(position).getTitle());
       // holder.imageView.setImageDrawable(R.drawable.test_image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,list.get(position)+"被点击",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(null == list){
            return 0;
        }

        return list.size();

    }


    /**
     * 自定义Holder
     */
    public class MyHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{
        ImageView imageView;
        TextView title;
        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.textViewTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
