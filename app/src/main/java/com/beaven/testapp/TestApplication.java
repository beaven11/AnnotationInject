package com.beaven.testapp;

import android.app.Application;
import com.beaven.testapp.test.AppComponent;
import com.beaven.testapp.test.DaggerAppComponent;
import com.beaven.testapp.test.TestAppModule;

/**
 * @author wangpeifeng
 * @date 2018/05/02 16:57
 */
public class TestApplication extends Application {

    private static AppComponent appComponent;
    private static TestApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .testAppModule(new TestAppModule(application))
                    .build();
        }
        return appComponent;
    }
}
