package cf.android666.myapplication.annotations;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import cf.android666.myapplication.R;

/**
 * Created by jixiaoyong on 2018/1/21.
 */

public class ASampleActivity extends Activity {

    @BindView(R.id.text)
    private TextView textView;

    @NonNull
    private String string;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annot_sample);
        AnnotationUtils.bindViews(ASampleActivity.this);
        textView.setText("hello annotation");

        string = null;
    }
}
