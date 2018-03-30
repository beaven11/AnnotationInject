package com.beaven.testapp.test;

import android.app.Application;
import dagger.Component;
import javax.inject.Singleton;

/**
 * @author : Beaven
 * @date : 2017-12-20 11:41
 */
@Singleton
@Component(modules = { TestAppModule.class })
public interface AppComponent {

    Application getApplication();

    MainComponent mainComponent(MainModule mainModule);
}
