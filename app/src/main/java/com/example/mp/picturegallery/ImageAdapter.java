package com.example.mp.picturegallery;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private Context context;

    List<String> pictures = MainActivity.urls;

// in case if need to load just part of list
//    List<String> somePictures(int num){
//        List<String> allList =  MainActivity.urls;
//        List<String> somePict = new ArrayList();
//        for(int i=0; i<num;i++) {
//            somePict.add(allList.get(i));
//        }
//        return somePict;
//    }

    public ImageAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return pictures.size();
    }

    @Override
    public Object getItem(int position) {
        return pictures.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = new ImageView(context);

        Picasso.get().load(pictures.get(position))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .resize(350, 350)
                .centerCrop().into(imageView);

        return imageView;
    }

    public  int getPictureFromPicasso(int position) {

        return 0;
    }


}
