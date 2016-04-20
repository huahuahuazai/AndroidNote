package com.jixiaoyong.nicecalculator;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //声明各个按钮
    Button no0 = null;
    Button no1 = null;
    Button no2 = null;
    Button no3 = null;
    Button no4 = null;
    Button no5 = null;
    Button no6 = null;
    Button no7 = null;
    Button no8 = null;
    Button no9 = null;
    Button point = null;

    Button clear = null;
    Button delete = null;
    Button add = null;
    Button reduce = null;
    Button multiply = null;
    Button divide = null;
    Button equal = null;
    TextView result = null;

    Double num1 = null;
    int tag = 0;//标志当前的运算符是+-*/哪一个
    boolean isSign = true;//判断此前输入的是数字还是运算符
    boolean isClickEqual = false;//判断是否点击了等于号
    boolean isPoint = false;//判断之前是否有小数点



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView)findViewById(R.id.result);

        //定义各个0~9按钮
        no0 = (Button)findViewById(R.id.no0);
        no1 = (Button)findViewById(R.id.no1);
        no2 = (Button)findViewById(R.id.no2);
        no3 = (Button)findViewById(R.id.no3);
        no4 = (Button)findViewById(R.id.no4);
        no5 = (Button)findViewById(R.id.no5);
        no6 = (Button)findViewById(R.id.no6);
        no7 = (Button)findViewById(R.id.no7);
        no8 = (Button)findViewById(R.id.no8);
        no9 = (Button)findViewById(R.id.no9);
        point = (Button)findViewById(R.id.point);

        //定义各种按钮
        clear = (Button)findViewById(R.id.clear);
        delete = (Button)findViewById(R.id.delete);
        equal = (Button)findViewById(R.id.equal);

        //定义+-*/
        add = (Button)findViewById(R.id.add);
        reduce = (Button)findViewById(R.id.reduce);
        multiply = (Button)findViewById(R.id.multiply);
        divide = (Button)findViewById(R.id.divide);

        //添加监听事件
        no1.setOnClickListener(this);
        no2.setOnClickListener(this);
        no3.setOnClickListener(this);
        no4.setOnClickListener(this);
        no5.setOnClickListener(this);
        no6.setOnClickListener(this);
        no7.setOnClickListener(this);
        no8.setOnClickListener(this);
        no9.setOnClickListener(this);
        no0.setOnClickListener(this);
        point.setOnClickListener(this);

        clear.setOnClickListener(this);
        delete.setOnClickListener(this);
        equal.setOnClickListener(this);
        add.setOnClickListener(this);
        reduce.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //0~9
            case R.id.no0:
            {
                if (isClickEqual|isSign) {
                    result.setText("");
                    isClickEqual = false;
                    isSign = false;
                }
                String str = result.getText().toString();
                str = str + "0";
                result.setText(str);
                break;
            }
            case R.id.no1:
            {
                if (isClickEqual|isSign) {
                    result.setText("");
                    isClickEqual = false;
                    isSign = false;
                }
                String str = result.getText().toString();
                str = str + "1";
                result.setText(str);
                break;
            }

            case R.id.no2:
            {
                if (isClickEqual|isSign) {
                    result.setText("");
                    isClickEqual = false;
                    isSign = false;
                }
                String str = result.getText().toString();
                str = str + "2";
                result.setText(str);
                break;
            }
            case R.id.no3:
            {
                if (isClickEqual|isSign) {
                    result.setText("");
                    isClickEqual = false;
                    isSign = false;
                }
                String str = result.getText().toString();
                str = str + "3";
                result.setText(str);
                break;
            }
            case R.id.no4:
            {
                if (isClickEqual|isSign) {
                    result.setText("");
                    isClickEqual = false;
                    isSign = false;
                }
                String str = result.getText().toString();
                str = str + "4";
                result.setText(str);
                break;
            }
            case R.id.no5:
            {
                if (isClickEqual|isSign) {
                    result.setText("");
                    isClickEqual = false;
                    isSign = false;
                }
                String str = result.getText().toString();
                str = str + "5";
                result.setText(str);
                break;
            }
            case R.id.no6:
            {
                if (isClickEqual|isSign) {
                    result.setText("");
                    isClickEqual = false;
                    isSign = false;
                }
                String str = result.getText().toString();
                str = str + "6";
                result.setText(str);
                break;
            }
            case R.id.no7:
            {
                if (isClickEqual|isSign) {
                    result.setText("");
                    isClickEqual = false;
                    isSign = false;
                }
                String str = result.getText().toString();
                str = str + "7";
                result.setText(str);
                break;
            }
            case R.id.no8:
            {
                if (isClickEqual|isSign) {
                    result.setText("");
                    isClickEqual = false;
                    isSign = false;
                }
                String str = result.getText().toString();
                str = str + "8";
                result.setText(str);
                break;
            }
            case R.id.no9:
            {
                if (isClickEqual|isSign) {
                    result.setText("");
                    isClickEqual = false;
                    isSign = false;
                }
                String str = result.getText().toString();
                str = str + "9";
                result.setText(str);
                break;
            }
            case R.id.point:
            {
                if (isClickEqual|isSign) {
                    result.setText("");
                    isClickEqual = false;
                    isSign = false;
                }
                String str = result.getText().toString();
                if(isPoint){
                    Toast.makeText(this,"已经有小数点啦~",Toast.LENGTH_SHORT).show();
                }else{
                    str = str + ".";
                    isPoint = true;
                }
                result.setText(str);
                break;
            }

            //清除和删除按钮
            case R.id.clear:
                result.setText("");
                num1 = null;
                tag = 0;
                isClickEqual = false;
                break;
            case R.id.delete:
                String str = result.getText().toString();
                try {
                    result.setText(str.substring(0,str.length()-1));
                }
                catch (Exception e){
                    Toast.makeText(this,R.string.toast_delete,Toast.LENGTH_SHORT).show();
                }
                break;

            //+-*/
            case R.id.add:
                {
                    Double buffer = Double.valueOf(result.getText().toString());
                    switch (tag){
                        case 0:
                            num1 = buffer;
                            break;
                        case 1:
                            num1 = num1 + buffer;
                            break;
                        case 2:
                            num1 = num1 - buffer;
                            break;
                        case 3:
                            num1 = num1 * buffer;
                            break;
                        case 4:
                            num1 = num1 / buffer;
                            break;
                    }
                    result.setText(String.valueOf(num1));
                    tag = 1;
                    isSign = true;
                    break;
                }
            case R.id.reduce:
            {
                Double buffer = Double.valueOf(result.getText().toString());
                switch (tag){
                    case 0:
                        num1 = buffer;
                        break;
                    case 1:
                        num1 = num1 + buffer;
                        break;
                    case 2:
                        num1 = num1 - buffer;
                        break;
                    case 3:
                        num1 = num1 * buffer;
                        break;
                    case 4:
                        num1 = num1 / buffer;
                        break;
                }
                result.setText(String.valueOf(num1));
                tag = 2;
                isSign = true;
                break;
            }
            case R.id.multiply:
            {
                Double buffer = Double.valueOf(result.getText().toString());
                switch (tag){
                    case 0:
                        num1 = buffer;
                        break;
                    case 1:
                        num1 = num1 + buffer;
                        break;
                    case 2:
                        num1 = num1 - buffer;
                        break;
                    case 3:
                        num1 = num1 * buffer;
                        break;
                    case 4:
                        num1 = num1 / buffer;
                        break;
                }
                result.setText(String.valueOf(num1));
                tag = 3;
                isSign = true;
                break;
            }
            case R.id.divide:
            {
                Double buffer = Double.valueOf(result.getText().toString());
                switch (tag){
                    case 0:
                        num1 = buffer;
                        break;
                    case 1:
                        num1 = num1 + buffer;
                        break;
                    case 2:
                        num1 = num1 - buffer;
                        break;
                    case 3:
                        num1 = num1 * buffer;
                        break;
                    case 4:
                        num1 = num1 / buffer;
                        break;
                }
                result.setText(String.valueOf(num1));
                tag = 4;
                isSign = true;
                break;
            }

            //=
            case R.id.equal:
                Double buffer = Double.valueOf(result.getText().toString());
                switch (tag){
                    case 0:
                        num1 = buffer;
                        break;
                    case 1:
                        num1 = num1 + buffer;
                        break;
                    case 2:
                        num1 = num1 - buffer;
                        break;
                    case 3:
                        num1 = num1 * buffer;
                        break;
                    case 4:
                        num1 = num1 / buffer;
                        break;
                }
                result.setText(String.valueOf(num1));
                isClickEqual = true;
                tag = 0;
                break;

        }
    }

}
