package com.jixiaoyong.autocompletetextview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
* 2016年4月23日 11:24:51
* AutoCompleteTextView自动填充文本的使用
* */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获得AutoCompleteTextView对象
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.auto_complete_text_view);

        //预设的联想字符数组，注意这里是ArrayList而不是List
        List<String> list = new ArrayList<String>();
        list.add("just for test1");
        list.add("just for test2");
        list.add("just for test3");
        list.add("just for test4");
        list.add("just for test5");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        //将ArrayAdapter设置给autoCompleteTextView
        autoCompleteTextView.setAdapter(adapter);
    }
}
