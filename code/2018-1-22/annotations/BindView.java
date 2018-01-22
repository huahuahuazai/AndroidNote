package cf.android666.myapplication.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jixiaoyong on 2018/1/21.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindView {
    //如果注解中的值不是value，那么在进行注解的时候，需要给出对应的值的名字
    //如@ViewInject(id = R.id.buy)
    int value();
}

/**
* @Target() 注解作用对象
*  1.CONSTRUCTOR:用于描述构造器
*　2.FIELD:用于描述字段
*　3.LOCAL_VARIABLE:用于描述局部变量
*　4.METHOD:用于描述方法
*　5.PACKAGE:用于描述包
*　6.PARAMETER:用于描述参数
*　7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
* */

/**
 * @Retention() 注解运行级别
 *  RetentionPolicy的类型有以下几种
 * 1.SOURCE:在源文件中有效（即源文件保留）
 * 2.CLASS:在class文件中有效（即class保留）
 * 3.RUNTIME:在运行时有效（即运行时保留）
 *
 * */