// TaskCallBack.aidl
package cf.android666.androidlib;
import cf.android666.androidlib.People;

// Declare any non-default types here with import statements
// https://blog.csdn.net/woshiwoshiyu/article/details/54266101

interface TaskCallBack {
    void callBack(in int size);
    void onPeopleChange(in List<People> peoples);
}
