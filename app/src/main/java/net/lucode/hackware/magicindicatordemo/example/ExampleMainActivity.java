package net.lucode.hackware.magicindicatordemo.example;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.lucode.hackware.magicindicatordemo.R;

public class ExampleMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_main_layout);

        // 첫 실행여부 체쿠
        SharedPreferences preference = getSharedPreferences("a", MODE_PRIVATE);
        int firstviewshow = preference.getInt("First", 0);

        if(firstviewshow != 1){
            startActivity(new Intent(this, PearlActivity.class));
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.custom_navigator:
                startActivity(new Intent(this, PearlActivity.class));
                break;
            default:
                break;
        }
    }
}
