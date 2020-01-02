package net.lucode.hackware.magicindicatordemo.example;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import net.lucode.hackware.magicindicatordemo.R;

import java.util.ArrayList;

public class PearlActivity extends AppCompatActivity {

    private ArrayList<Integer> contentList;
    private static final int DP = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pearl);

        this.initializeData();

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setClipToPadding(false);

        float density = getResources().getDisplayMetrics().density;
        int margin = (int) (DP * density);
        viewPager.setPadding(margin, 0, margin, 0);
        viewPager.setPageMargin(margin/2);

        viewPager.setAdapter(new PearlViewPagerAdapter(this, contentList));

    }

    public void initializeData(){
        contentList = new ArrayList();


    }
}
