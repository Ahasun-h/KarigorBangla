package com.mdrayefenam.karigorbangla.ServiceProvider.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mdrayefenam.karigorbangla.R;
import com.mdrayefenam.karigorbangla.ServiceProvider.ImageSliderModel.ActiveImage;

import java.util.ArrayList;
import java.util.List;

public class ImageSliderAdapter  extends BaseAdapter {

    private Context mCtx;
    private ArrayList <ActiveImage> activeImages;

    public ImageSliderAdapter(Context mCtx, ArrayList<ActiveImage> activeImages){
        this.mCtx = mCtx;
        this.activeImages = activeImages;
    }
    @Override
    public int getCount() {
        return activeImages.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ActiveImage activeImage = activeImages.get(position);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate( R.layout.flipper_items, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        Glide.with(mCtx).load(activeImage.getImage()).into(imageView);




        return view;
    }

}
