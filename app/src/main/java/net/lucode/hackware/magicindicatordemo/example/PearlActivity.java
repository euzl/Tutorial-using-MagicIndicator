package net.lucode.hackware.magicindicatordemo.example;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    // 화면전환용 버튼
    TextView btnPrev;
    TextView btnNext;
    // 현재 index 저장
    private int currentPage;

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

        //현재페이지 설정
        currentPage = mViewPager.getCurrentItem();

        setTextViewMessage();
    }

    // imageList에 이미지 추가
    public void initializeData(){
        imageList = new ArrayList();

        imageList.add(R.drawable.tp1);
        imageList.add(R.drawable.tp2);
        imageList.add(R.drawable.tp3);
    }

    private void setTextViewMessage(){
        if(currentPage == 0){
            btnPrev.setText("");
            btnNext.setText("다음");
        }
        else if(currentPage==CHANNELS.length){
            btnPrev.setText("이전");
            btnNext.setText("시작하기");
        }
        else {
            btnPrev.setText("이전");
            btnNext.setText("다음");
        }
    }
    public void onPrevButtonClick(View v){
        //첫페이지
        if(currentPage == 0){
            return;
        }

        mViewPager.setCurrentItem(currentPage-1);
    }

    public void onNextButtonClick(View v){
        // 마지막 페이지라면 '시작하기'버튼을 눌렀을 때 다시보지못하게
        if(currentPage == CHANNELS.length) {
            int infoFirst = 1;
            SharedPreferences a = getSharedPreferences("a", MODE_PRIVATE);
            SharedPreferences.Editor editor = a.edit();
            editor.putInt("First", infoFirst);
            editor.commit();
            (Toast.makeText(getApplicationContext(), "first End", Toast.LENGTH_LONG)).show();
            finish(); //액티비티 종료
        }
        //나머지는 '다음'버튼으로
        else{
            mViewPager.setCurrentItem(currentPage+1);
        }
    }

    // 진주목걸이
    private void initMagicIndicator() {
        MagicIndicator magicIndicator = findViewById(R.id.magic_indicator);
        CircleNavigator circlenavigator = new CircleNavigator(this);
        circlenavigator.setCircleCount(CHANNELS.length); // 총 페이지 개수
        circlenavigator.setFollowTouch(false);
        circlenavigator.setCircleColor(Color.RED);
        circlenavigator.setCircleClickListener(new CircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                mViewPager.setCurrentItem(index); // 현재페이지를 index로 변경 (버튼 누른페이지로 이동)
            }
        });
        magicIndicator.setNavigator(circlenavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }
}
