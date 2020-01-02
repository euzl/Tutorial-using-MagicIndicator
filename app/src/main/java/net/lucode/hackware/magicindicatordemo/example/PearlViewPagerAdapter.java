package net.lucode.hackware.magicindicatordemo.example;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.lucode.hackware.magicindicatordemo.R;

import java.util.ArrayList;
import java.util.List;

public class PearlViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<Integer> imageList;
    private List<String> textList;

    public PearlViewPagerAdapter(Context context, ArrayList<Integer> imageList, List<String> textList){
        this.mContext = context;
        this.imageList = imageList;
        this.textList = textList;
    }

    // page에 들어갈 내용 설정
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.page_pearl, null);

        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(imageList.get(position));

        TextView textView = view.findViewById(R.id.textView);
        textView.setText(textList.get(position));

        container.addView(view);

        return view;
    }

    // viewPage에서 페이지 수 였던듯
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
