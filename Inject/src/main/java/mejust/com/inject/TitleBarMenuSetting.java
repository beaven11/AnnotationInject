package mejust.com.inject;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建时间: 2018/04/10
 * 创建人: 王培峰
 * 修改人: 王培峰
 * 修改时间: 2018/04/10
 * 描述: <empty/>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface TitleBarMenuSetting {

    /**
     * 菜单位置
     *
     * TitleBarMenuLocation
     */
    int location();

    /**
     * 菜单文本
     */
    String text();

    /**
     * 菜单文本颜色
     */
    int textColor() default -1;

    /**
     * 菜单文本大小
     */
    float textSize() default -1;

    /**
     * 菜单图片资源id
     */
    int drawableRes() default -1;
}
