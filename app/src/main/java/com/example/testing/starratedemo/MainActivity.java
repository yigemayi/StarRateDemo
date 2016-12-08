package com.example.testing.starratedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private StarRateView mStarRateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mStarRateView = (StarRateView) findViewById(R.id.rate_stars_container);
        mStarRateView.setFullRateListener(new FullRateListener() {
            @Override
            public void onFiveStarClick() {
                Toast.makeText(getApplicationContext(), "点击了五星", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
