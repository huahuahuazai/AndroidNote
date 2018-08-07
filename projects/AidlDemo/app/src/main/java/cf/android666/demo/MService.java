package cf.android666.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.InputFilter;
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
            onPeopleChange(mPeopleList);
        }

        @Override
        public void registerCallBack(TaskCallBack callback) throws RemoteException {
            mCallBack = callback;
            Log.d("TAG", "registerCallBack注册回调方法 callback == null" + callback);
            if (callback != null) {
                callbackList.register(callback);
            }
        }

        @Override
        public void unregisterCallBack(TaskCallBack callback) throws RemoteException {
            if (callback != null) {
                callbackList.unregister(callback);
            }
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

    private void onPeopleChange(List<People> peoples) {
        Log.d("tag", "onBind  onPeopleChange开始在service调用" + callbackList );
        if (callbackList == null) {
            return;
        }
        int len = callbackList.beginBroadcast();
        Log.d("tag", "onBind  onPeopleChange开始在service调用 + len" + +len);

        try {
            for (int i = 0; i < len; i++) {
                callbackList.getBroadcastItem(i).onPeopleChange(peoples);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }finally {
            callbackList.finishBroadcast();
        }
    }
}
