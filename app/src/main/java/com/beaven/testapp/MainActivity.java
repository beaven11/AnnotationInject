package com.beaven.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.beaven.testapp.base.BasePresenterActivity;
import com.beaven.testapp.title.TopBar;
import mejust.com.annotations.InjectLayout;
import mejust.com.inject.LayoutId;
import mejust.com.inject.TitleBarSetting;

@TitleBarSetting(titleValue = "首页")
@LayoutId(R.layout.activity_main)
public class MainActivity extends BasePresenterActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;

    TopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TestApplication.getAppComponent().mainComponent(new MainModule()).inject(this);
        buttonOne = findViewById(R.id.button_one);
        buttonTwo = findViewById(R.id.button_two);
        buttonThree = findViewById(R.id.button_three);
        topBar = findViewById(R.id.top_bar);
        InjectLayout.injectTitleBar(this);
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        Log.i(TAG, "onCreate: ");
        //topBar.setTopBarSetting(topBarSettingBuilder.build());
        Log.i(TAG, "onCreate: ---" + topBar.getTopBarSetting());
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
