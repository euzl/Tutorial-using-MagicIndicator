package net.lucode.hackware.magicindicatordemo.example;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;
import net.lucode.hackware.magicindicatordemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Todo : Set Text message
// Todo : Add Indicator
// Todo : Make pretty
public class PearlActivity extends AppCompatActivity {

    private static final String[] CHANNELS = new String[]{"첫번째페이지", "두번째페이지", "세번째"};
    private ArrayList<Integer> imageList;
    private List<String> textList = Arrays.asList(CHANNELS);

    private ViewPager mViewPager;

    private static final int DP = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pearl);

        this.initializeData();

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setClipToPadding(false);

        // 화면구성하는거같음
        float density = getResources().getDisplayMetrics().density;
        int margin = (int) (DP * density);
        viewPager.setPadding(margin, 0, margin, 0);
        viewPager.setPageMargin(margin/2);

        viewPager.setAdapter(new PearlViewPagerAdapter(this, imageList));

    }

    public void initializeData(){
        imageList = new ArrayList();

        imageList.add(R.drawable.image1);
        imageList.add(R.drawable.image2);
        imageList.add(R.drawable.image3);

    }

    private void initMagicIndicator() {
        MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
        CircleNavigator circlenavigator = new CircleNavigator(this);
        circlenavigator.setCircleCount(CHANNELS.length);
        circlenavigator.setFollowTouch(false);
        circlenavigator.setCircleColor(Color.GREEN);
        circlenavigator.setCircleClickListener(new CircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                mViewPager.setCurrentItem(index);
            }
        });
        magicIndicator.setNavigator(circlenavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }
}
