package com.beaven.testapp.test;

import com.beaven.testapp.MainActivity;
import dagger.Subcomponent;
import javax.inject.Named;

/**
 * @author : Beaven
 * @date : 2017-12-20 12:49
 */
@Named
@ActivityScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity activity);
}
