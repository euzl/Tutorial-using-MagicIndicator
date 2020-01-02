package net.lucode.hackware.magicindicatordemo.example;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.lucode.hackware.magicindicatordemo.R;

import java.util.ArrayList;

public class PearlViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<Integer> imageList;

    public PearlViewPagerAdapter(Context context, ArrayList<Integer> imageList){
        this.mContext = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_pearl, null);

        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(imageList.get(position));

        container.addView(view);

        return view;
    }

    @Override
    public int getCount(){
        return imageList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object){
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o){
        return (view == o);
    }

}
