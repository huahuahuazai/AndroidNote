package cf.android666.test.hook;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import cf.android666.test.R;

/**
 * Created by jixiaoyong on 2018/1/15.
 */

public class ProxyActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
    }
}
