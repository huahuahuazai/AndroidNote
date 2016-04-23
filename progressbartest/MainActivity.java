package com.jixiaoyong.progressbartest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;

/*
* 2016年4月22日 21:54:24
* 内容：各种进度条的展示，以及设置第一、第二进度条的进度*/

public class MainActivity extends Activity {

    private ProgressBar progressBar;
    private Button firstButton;
    private Button secondButton;
    private SeekBar seekBar;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //在代码里面创建progressbar，并设置进度，会覆盖xml中的定义
        progressBar = (ProgressBar)findViewById(R.id.progress_bar1);
        progressBar.setProgress(100);
        progressBar.setSecondaryProgress(152);

        //判断progress是否能看到具体的进度（能——水平，否——圆圈）
        boolean flag = progressBar.isIndeterminate();

        //点击按钮在当前进度上增加进度

        firstButton = (Button)findViewById(R.id.first_button);
        secondButton = (Button)findViewById(R.id.Second_button);

        firstButton.setOnClickListener(new FirstBtnListener());
        secondButton.setOnClickListener(new SecondBtnListener());
        progressBar.setMax(500);

        //SeekBar
        seekBar = (SeekBar)findViewById(R.id.seek_bar);
        seekBar.setProgress(30);
        seekBar.setSecondaryProgress(50);
        seekBar.setOnSeekBarChangeListener(new SeekBarListener());

        //RatingBar
        ratingBar = (RatingBar)findViewById(R.id.rating_bar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Log.d("RatingBar","得分rating: " + v + " fromUser: " + b);
            }
        });

    }

    class FirstBtnListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            progressBar.incrementProgressBy(20);
        }
    }

    class SecondBtnListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            progressBar.incrementSecondaryProgressBy(10);
        }
    }

    class SeekBarListener implements SeekBar.OnSeekBarChangeListener {
        /*
        SeekBar seekBar指的是触发监听器的SeekBar对象
        boolean fromUser判断进度变化是否由用户引起
        * */
        @Override
        public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser){
            Log.d("SeekBarProgresss","progress: " + progress + " fromUser: " + fromUser);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            Log.d("SeekBarProgresss","onStart");
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar){
            Log.d("SeekBarProgresss","onStop");
        }


    }
}
