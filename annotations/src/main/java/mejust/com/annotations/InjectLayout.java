package mejust.com.annotations;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
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
    }

    public static View injectFragment(Fragment fragment, ViewGroup container) {
        return container;
    }

    private static int getLayoutId(Class host) {
        String fullName = host.getName() + "_InjectLayout";
        try {
            Class cls = Class.forName(fullName);
            Method layoutId = cls.getDeclaredMethod("getLayoutId");
            return (int) layoutId.invoke(null);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("layoutId is error");
        }
    }
}
