package com.beaven.testapp.test;

import android.app.Application;
import dagger.Module;

/**
 * 创建时间: 2018/03/28 16:36<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/28 16:36<br>
 * 描述:
 */
@Module
public class TestAppModule extends AppModule {

    public TestAppModule(Application application) {
        super(application);
    }
}
