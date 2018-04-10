package mejust.com.annotations;

import android.support.annotation.IntDef;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 创建时间: 2018/04/10
 * 创建人: 王培峰
 * 修改人: 王培峰
 * 修改时间: 2018/04/10
 * 描述: <empty/>
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({
        TitleBarMenuLocation.MENU_LEFT_FIRST, TitleBarMenuLocation.MENU_LEFT_SECOND,
        TitleBarMenuLocation.MENU_RIGHT_FIRST, TitleBarMenuLocation.MENU_RIGHT_SECOND
})
@Documented
public @interface TitleBarMenuLocation {
    /**
     * 左边第一个，默认返回键位置
     */
    int MENU_LEFT_FIRST = 0x00;
    /**
     * 左边第二个
     */
    int MENU_LEFT_SECOND = 0x01;
    /**
     * 右边第一个
     */
    int MENU_RIGHT_FIRST = 0x10;
    /**
     * 右边第二个
     */
    int MENU_RIGHT_SECOND = 0x11;
}
