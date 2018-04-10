package mejust.com.inject;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建时间:2017/12/23 21:25<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/23 21:25<br/>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface TitleBarSetting {
    /**
     * 标题内容
     */
    String titleValue();

    /**
     * 文字的颜色
     */
    int textColor() default -1;

    /**
     * 文字大小
     */
    float textSize() default -1;

    /**
     * 背景色
     */
    int background() default -1;

    /**
     * 隐藏返回键
     */
    boolean hideBack() default false;
}
