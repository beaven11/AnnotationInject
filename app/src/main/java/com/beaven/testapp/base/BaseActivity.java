package com.beaven.testapp.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import mejust.com.annotations.InjectLayout;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectLayout.injectActivity(this);
    }
}
