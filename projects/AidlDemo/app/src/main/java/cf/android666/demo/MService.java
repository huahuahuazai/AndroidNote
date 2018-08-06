package cf.android666.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cf.android666.androidlib.ManagerAidl;
import cf.android666.androidlib.People;
import cf.android666.androidlib.TaskCallBack;


/**
 * Created by jixiaoyong on 2018/8/6.
 * email:jixiaoyong1995@gmail.com
 */
public class MService extends Service implements ManagerAidl.Stub.DeathRecipient {

    private List<People> mPeopleList;
    private static RemoteCallbackList<TaskCallBack> callbackList = new RemoteCallbackList<>();;
    private TaskCallBack mCallBack;

    private IBinder mIBinder = new ManagerAidl.Stub() {

        @Override
        public List<People> getPeopleList() throws RemoteException {
            return mPeopleList;
        }

        @Override
        public void addPeople(People people) throws RemoteException {
            mPeopleList.add(people);
        }

        @Override
        public void registerCallBack(TaskCallBack callback) throws RemoteException {
            mCallBack = callback;
//            if (callbackList != null) {
//                callbackList.register(callback);
//            }
        }

        @Override
        public void unregisterCallBack(TaskCallBack callback) throws RemoteException {
            callbackList.unregister(callback);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("tag", "onBind  MService开始了" );

        if (mPeopleList == null) {
            mPeopleList = new ArrayList<>();
        }

        for (int i = 0; i < 20; i++) {
            mPeopleList.add(new People("people" + i, i));
        }

        return mIBinder;
    }

    @Override
    public void binderDied() {
        callbackList.unregister(mCallBack);
    }
}
