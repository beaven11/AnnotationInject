package com.beaven.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.beaven.inject.TitleBarConfig;
import com.beaven.testapp.base.BasePresenterActivity;
import com.beaven.testapp.title.TitleBar;
import com.beaven.testapp.title.TitleBarSetting;
import com.beaven.testapp.title.TitleBarUtil;

/**
 * @author wangpeifeng
 * @date 2018/05/02 16:57
 */
@TitleBarConfig(textValue = "MainActivity")
public class MainActivity extends BasePresenterActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;

    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonOne = findViewById(R.id.button_one);
        buttonTwo = findViewById(R.id.button_two);
        buttonThree = findViewById(R.id.button_three);
        titleBar = findViewById(R.id.top_bar);
        TitleBarUtil.inject(this, titleBar, new TitleBarSetting.Builder().build());
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        Log.i(TAG, "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}
