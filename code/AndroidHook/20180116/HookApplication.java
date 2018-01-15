package cf.android666.test.hook;

import android.app.Application;

/**
 * Created by jixiaoyong on 2018/1/15.
 */

public class HookApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.hookAms(this);
        Utils.hookHandle();

    }
}
