package cf.android666.test.hook;

import android.content.Context;
import android.os.Handler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by jixiaoyong on 2018/1/15.
 */

class Utils {

    public static void hookAms(Context context) {
        try {
            Class hookActivityManagerNative = Class.forName("android.app.ActivityManagerNative");
            //在api>26时无此变量：gDefault，该方法失效
            Field gDefault = hookActivityManagerNative.getDeclaredField("gDefault");
            gDefault.setAccessible(true);
            Object object = gDefault.get(null);

            Class hookSingleton = Class.forName("android.util.Singleton");
            Field mInstance = hookSingleton.getDeclaredField("mInstance");
            mInstance.setAccessible(true);

            Object oldAms = mInstance.get(object);

            Class hookIActivityManagerService = Class.forName("android.app.IActivityManager");
            Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class<?>[]{hookIActivityManagerService},
                    new MAmsInvocationHandler(context,oldAms));

            mInstance.set(object,proxy);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void hookHandle() {
        try {
            Class activityThreadCls = Class.forName("android.app.ActivityThread");
            Method currentActivityThread = activityThreadCls.getDeclaredMethod("currentActivityThread");
            currentActivityThread.setAccessible(true);

            Object activityThread = currentActivityThread.invoke(null);

            Field mH = activityThreadCls.getDeclaredField("mH");
            mH.setAccessible(true);
            Handler handler = (Handler) mH.get(activityThread);
            Field callBack = Handler.class.getDeclaredField("mCallback");
            callBack.setAccessible(true);
            callBack.set(handler, new ActivityThreadHandlerCallBack(handler));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
