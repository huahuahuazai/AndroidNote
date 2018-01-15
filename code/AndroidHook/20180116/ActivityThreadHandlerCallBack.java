package cf.android666.test.hook;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import java.lang.reflect.Field;

/**
 * Created by jixiaoyong on 2018/1/15.
 */

public class ActivityThreadHandlerCallBack implements Handler.Callback {
    private Handler mHandler;

    public ActivityThreadHandlerCallBack(Handler handler) {
        mHandler = handler;
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what == 100) {
            handleLaunchActivity(msg);
        }
        mHandler.handleMessage(msg);
        return true;
    }

    private void handleLaunchActivity(Message msg) {
        Object object = msg.obj;
        try {
            Field intent = object.getClass().getDeclaredField("intent");
            intent.setAccessible(true);
            Intent proxyIntent = (Intent) intent.get(object);
            Intent targetIntent = proxyIntent.getParcelableExtra(MAmsInvocationHandler.TARGET_KEY);
            if (targetIntent != null) {
                proxyIntent.setComponent(targetIntent.getComponent());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
