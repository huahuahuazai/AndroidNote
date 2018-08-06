// ManagerAidl.aidl
package cf.android666.androidlib;

import cf.android666.androidlib.People;
import cf.android666.androidlib.TaskCallBack;
// Declare any non-default types here with import statements

interface ManagerAidl {

    List<People> getPeopleList();

    void addPeople(in People people);

    void registerCallBack(in TaskCallBack callback);
    void unregisterCallBack(in TaskCallBack callback);

}
