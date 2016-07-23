package com.dhananjay.spiderappdevt3;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MovieGridAdapter extends BaseAdapter{

    List<MovieGridItem> gridItemList;
    Context context;

    MovieGridAdapter(Context context,List<MovieGridItem> gridItemList){
        this.gridItemList=gridItemList;
        this.context=context;
    }

    @Override
    public int getCount() {
        return gridItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return gridItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return gridItemList.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row=view;
        ViewHolder viewHolder=null;
        if(view==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.movie_grid_item_layout,viewGroup,false);
            viewHolder=new ViewHolder(row);
            row.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        MovieGridItem gridItem=gridItemList.get(i);
        viewHolder.title.setText(gridItem.title);
        viewHolder.genre.setText(gridItem.genre);
        Bitmap bmp= BitmapFactory.decodeByteArray(gridItem.imageBytes,0,gridItem.imageBytes.length);
        viewHolder.poster.setImageBitmap(bmp);

        return row;
    }

    class ViewHolder{
        ImageView poster;
        TextView title;
        TextView genre;
        ViewHolder(View v){
            poster= (ImageView) v.findViewById(R.id.imageView);
            title= (TextView) v.findViewById(R.id.textView);
            genre= (TextView) v.findViewById(R.id.textView2);
        }
    }
}
