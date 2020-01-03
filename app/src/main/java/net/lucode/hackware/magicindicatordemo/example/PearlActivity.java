package net.lucode.hackware.magicindicatordemo.example;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;
import net.lucode.hackware.magicindicatordemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//todo 하드코딩엄청많다..
//todo 뒤로가기버튼 막아야될듯?
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pearl);

        this.initializeData();

        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setClipToPadding(false);
        mViewPager.setAdapter(new PearlViewPagerAdapter(this, imageList, textList)); //viewPager에 어댑터를 통해 내용설정

        // 화면구성하는거같음
        float density = getResources().getDisplayMetrics().density;
        int margin = (int) (DP * density);
        mViewPager.setPadding(margin, 0, margin, 0);
        mViewPager.setPageMargin(margin/2);

        // 버튼... 메시지... 지정...
        btnPrev = findViewById(R.id.btn_prev);
        btnNext = findViewById(R.id.btn_next);

        btnNext.setText("다음");

        // 매직 인디케이터(진주목걸이) 띄우기
        initMagicIndicator();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if(mViewPager.getCurrentItem() == 0){
                    btnPrev.setText("");
                    btnNext.setText("다음");
                }
                else if(mViewPager.getCurrentItem()==CHANNELS.length-1){
                    btnPrev.setText("이전");
                    btnNext.setText("시작하기");
                }
                else {
                    btnPrev.setText("이전");
                    btnNext.setText("다음");
                }
            }
        });
    }

    // imageList에 이미지 추가
    public void initializeData(){
        imageList = new ArrayList();

        imageList.add(R.drawable.tp1);
        imageList.add(R.drawable.tp2);
        imageList.add(R.drawable.tp3);
    }

    public void onPrevButtonClick(View v){
        mViewPager.setCurrentItem(mViewPager.getCurrentItem()-1);
    }

    public void onNextButtonClick(View v){
        // 마지막 페이지라면 '시작하기'버튼을 눌렀을 때 다시보지못하게
        if(mViewPager.getCurrentItem() == CHANNELS.length-1) {
            int infoFirst = 1;
            SharedPreferences a = getSharedPreferences("a", MODE_PRIVATE);
            SharedPreferences.Editor editor = a.edit();
            editor.putInt("First", infoFirst);
            editor.commit();
            (Toast.makeText(getApplicationContext(), "시작합니다", Toast.LENGTH_LONG)).show();
            finish(); //액티비티 종료
        }
        //나머지는 '다음'버튼으로
        else{
            mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
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
