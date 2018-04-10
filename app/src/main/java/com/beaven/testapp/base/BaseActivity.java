package com.beaven.testapp.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.beaven.testapp.title.TopBarSetting;
import mejust.com.annotations.InjectLayout;

public abstract class BaseActivity extends AppCompatActivity {

    public TopBarSetting.Builder topBarSettingBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topBarSettingBuilder = new TopBarSetting.Builder();
        InjectLayout.injectActivity(this);
    }
}
