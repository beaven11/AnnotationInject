package mejust.com.annotations;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 创建时间: 2018/03/30 10:49<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/30 10:49<br>
 * 描述:
 */
public class InjectLayout {

    public static void injectActivity(Activity activity) {
        try {
            Class cls = getLayoutId(activity.getClass());
            Method method = cls.getDeclaredMethod("layout", activity.getClass());
            method.invoke(null, activity);
        } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException | NoSuchMethodException e) {
            throw new IllegalArgumentException("layout Id is not init,please check layout id");
        }
    }

    public static View injectFragment(Fragment fragment) {
        try {
            Class cls = getLayoutId(fragment.getClass());
            Method method = cls.getDeclaredMethod("layout", fragment.getClass());
            return (View) method.invoke(null, fragment);
        } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException | NoSuchMethodException e) {
            throw new IllegalArgumentException("layout Id is not init,please check layout id");
        }
    }

    private static Class getLayoutId(Class host) throws ClassNotFoundException {
        String fullName = host.getName() + "_InjectLayout";
        return Class.forName(fullName);
    }
}
