package cf.android666.test.hook;

import android.content.Context;
import android.content.Intent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by jixiaoyong on 2018/1/15.
 */

public class MAmsInvocationHandler implements InvocationHandler {

    public static final String TARGET_KEY = "targetIntent";
    private Context mContext;
    private Object mOldAms;

    public MAmsInvocationHandler(Context context, Object oldAms) {
        mContext = context;
        mOldAms = oldAms;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("startActivity".equals(method.getName())) {
            int index = 0;
            Intent targetIntent = null;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Intent) {
                    index = i;
                    targetIntent = (Intent) args[i];
                    break;
                }
            }
            if (targetIntent != null) {
                Intent proxyIntent = new Intent(mContext, ProxyActivity.class);
                proxyIntent.putExtra(TARGET_KEY, targetIntent);
                args[index] = proxyIntent;
            }
        }
        return method.invoke(mOldAms,args);
    }
}
