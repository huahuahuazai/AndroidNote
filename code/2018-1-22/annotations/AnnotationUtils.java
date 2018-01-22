package cf.android666.myapplication.annotations;

import android.app.Activity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by jixiaoyong on 2018/1/21.
 */

public class AnnotationUtils {
    public static void bindViews(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            BindView bindView = field.getAnnotation(BindView.class);
            if (bindView != null) {
                int viewId = bindView.value();
                if (viewId != -1) {
                    try {
                        Method findViewById = clazz.getMethod("findViewById", int.class);
                        findViewById.setAccessible(true);
                        Object o = findViewById.invoke(activity, viewId);
                        field.setAccessible(true);
                        field.set(activity,o);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
