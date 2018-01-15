package cf.android666.test.hook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import cf.android666.test.R;

/**
 * Created by jixiaoyong on 2018/1/15.
 */

public class IndexActivity extends Activity {

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        mContext = IndexActivity.this;

        ((Button)findViewById(R.id.btn1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动未在AndroidManifest.xml注册的activity
                mContext.startActivity(new Intent(mContext,TargetActivity.class));
            }
        });
    }
}
