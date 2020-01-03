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

public class PearlActivity extends AppCompatActivity {
    // page마다 표시될 이미지 리스트
    private ArrayList<Integer> imageList;

    // page마다 표시될 text 메시지 리스트
    // todo 현재 하드코딩이지만 더 깔끔하게 수정가능할 듯
    private static final String[] CHANNELS = new String[]{"첫번째페이지\n놀랍게도 고양이다", "두번째페이지\n얘가 더 졸랍다 강아지", "세번째\n토끼 작은척함"};
    private List<String> textList = Arrays.asList(CHANNELS);

    private ViewPager mViewPager;

    private static final int DP = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pearl);

        this.initializeData();

        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setClipToPadding(false);
        mViewPager.setAdapter(new PearlViewPagerAdapter(this, imageList, textList)); //viewPager에 어댑터를 통해 내용설정

        // 매직 인디케이터(진주목걸이) 띄우기
        initMagicIndicator();

        // 화면구성하는거같음
        float density = getResources().getDisplayMetrics().density;
        int margin = (int) (DP * density);
        mViewPager.setPadding(margin, 0, margin, 0);
        mViewPager.setPageMargin(margin/2);
    }

    // imageList에 이미지 추가
    public void initializeData(){
        imageList = new ArrayList();

        imageList.add(R.drawable.tp1);
        imageList.add(R.drawable.tp2);
        imageList.add(R.drawable.tp3);

    }

    // 진주목걸이
    private void initMagicIndicator() {
        MagicIndicator magicIndicator = findViewById(R.id.magic_indicator);
        CircleNavigator circlenavigator = new CircleNavigator(this);
        circlenavigator.setCircleCount(CHANNELS.length);
        circlenavigator.setFollowTouch(false);
        circlenavigator.setCircleColor(Color.RED);
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
