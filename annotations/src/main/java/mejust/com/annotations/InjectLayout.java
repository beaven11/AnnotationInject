package mejust.com.annotations;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Constructor;
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

    public static void injectTitleBar(Activity activity) {
        try {
            Constructor constructor =
                    getLayoutId(activity.getClass()).getConstructor(activity.getClass());
            constructor.newInstance(activity);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("layout Id is not init,please check layout id");
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static View injectFragment(Fragment fragment, ViewGroup viewGroup) {
        try {
            Class cls = getLayoutId(fragment.getClass());
            Method method = cls.getDeclaredMethod("layout", fragment.getClass(), ViewGroup.class);
            return (View) method.invoke(null, fragment, viewGroup);
        } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException | NoSuchMethodException e) {
            throw new IllegalArgumentException("layout Id is not init,please check layout id");
        }
    }

    private static Class getLayoutId(Class host) throws ClassNotFoundException {
        String fullName = host.getName() + "_InjectLayout";
        return Class.forName(fullName);
    }
}
