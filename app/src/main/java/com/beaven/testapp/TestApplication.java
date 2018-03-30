package com.beaven.testapp;

import android.app.Application;
import com.beaven.testapp.test.AppComponent;
import com.beaven.testapp.test.DaggerAppComponent;
import com.beaven.testapp.test.TestAppModule;

/**
 * 创建时间: 2018/03/28 15:27<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/28 15:27<br>
 * 描述:
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
