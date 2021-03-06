package com.beaven.inject;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author wangpeifeng
 */
@Retention(RetentionPolicy.CLASS)
@IntDef({
        TitleBarMenuLocation.leftFirstMenu, TitleBarMenuLocation.leftSecondMenu,
        TitleBarMenuLocation.rightFirstMenu, TitleBarMenuLocation.rightSecondMenu
})
public @interface TitleBarMenuLocation {

    /** The first on the left, the default is the return key position */
    int leftFirstMenu = 0x01;
    /** The second on the left */
    int leftSecondMenu = 0x02;
    /** The first on the right */
    int rightFirstMenu = 0x19;
    /** The second on the right */
    int rightSecondMenu = 0x18;
}
