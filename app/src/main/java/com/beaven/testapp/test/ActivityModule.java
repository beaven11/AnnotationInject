package com.beaven.testapp.test;

import dagger.Module;
import dagger.Provides;

/**
 * 创建时间: 2018/03/28 16:27<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/28 16:27<br>
 * 描述:
 */
@Module
public abstract class ActivityModule {

    @ActivityScope
    @Provides
    public TitleBar provideTitleBar(@AppTitle TitleBar titleBar) {
        return getTitle(titleBar);
    }

    abstract TitleBar getTitle(TitleBar titleBar);
}
