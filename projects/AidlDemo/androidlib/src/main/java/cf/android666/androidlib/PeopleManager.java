package cf.android666.androidlib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jixiaoyong on 2018/8/6.
 * email:jixiaoyong1995@gmail.com
 */
public class PeopleManager {

    private static PeopleManager mPeopleManager;

    private  Context mContext;
    private  Listener mListener;
    private ManagerAidl managerAidl;

    private List<People> peopleList;

    private TaskCallBack callBack = new TaskCallBack() {
        @Override
        public void callBack(int size) throws RemoteException {
            mListener.onCallback(size);
        }

        @Override
        public IBinder asBinder() {
            return null;
        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("tag", "onServiceConnected peopleManager连接上了 Service" );
            managerAidl =  ManagerAidl.Stub.asInterface(service);
            try {
                peopleList = managerAidl.getPeopleList();
                managerAidl.registerCallBack(callBack);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mListener.onCreate(mPeopleManager);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            try {
                managerAidl.unregisterCallBack(callBack);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    };

    private PeopleManager(Context context,Listener listener) {
        mContext = context;
        mListener = listener;
        peopleList = new ArrayList<>();

        Intent intent = new Intent();
        intent.setComponent(new ComponentName("cf.android666.demo",
                "cf.android666.demo.MService"));
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public static void init(Context context,Listener listener) {
        Log.d("tag", "library start init people manager " );
        mPeopleManager = new PeopleManager( context, listener);
    }

    public void addPeople(People people) {
        try {
            managerAidl.addPeople(people);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public List<People> getPeopleList() {
        return peopleList;
    }

    public interface Listener {

        void onCreate(PeopleManager peopleManager);

        void onCallback(int size);
    }

}
