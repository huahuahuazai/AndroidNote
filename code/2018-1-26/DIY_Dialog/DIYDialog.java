package cf.android666.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import cf.android666.myapplication.R;

/**
 * Created by jixiaoyong on 2018/1/26.
 */

public class DIYDialog extends Dialog {

    public DIYDialog(@NonNull Context context) {
        super(context,0);
    }

    public DIYDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    protected DIYDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);

    }

    private void initView(Context context) {

        setCancelable(false);

        View view = View.inflate(context, R.layout.layout_dialog, null);
        ImageView imageView = view.findViewById(R.id.image);
        imageView.setBackgroundResource(R.drawable.dialog_progress);
        setContentView(view);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.run();


    }
}
