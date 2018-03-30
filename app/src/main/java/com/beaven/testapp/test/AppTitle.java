package com.beaven.testapp.test;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 创建时间: 2018/03/28 16:22<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/28 16:22<br>
 * 描述:
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface AppTitle {
}
