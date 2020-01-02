package net.lucode.hackware.magicindicatordemo.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.lucode.hackware.magicindicatordemo.R;

public class ExampleMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_main_layout);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.custom_navigator:
                startActivity(new Intent(this, CustomNavigatorExampleActivity.class));
                break;
            default:
                break;
        }
    }
}
