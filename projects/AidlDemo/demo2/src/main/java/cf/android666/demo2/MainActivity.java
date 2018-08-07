package cf.android666.demo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import cf.android666.androidlib.People;
import cf.android666.androidlib.PeopleManager;

public class MainActivity extends AppCompatActivity implements PeopleManager.Listener, View.OnClickListener {

    private List<People> peopleList;
    private PeopleManager mPeopleManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(this);
        Log.d("tag", "demo2 start init people manager ");

        PeopleManager.init(this, this);

    }

    @Override
    public void onCreate(PeopleManager peopleManager) {
        mPeopleManager = peopleManager;
        peopleList = peopleManager.getPeopleList();

        for (int i = 0; i < peopleList.size(); i++) {
            Log.d("tag", "people list is " + peopleList.get(i));
        }
    }

    @Override
    public void onCallback(int size) {
        Log.d("tag", "onCallback people size is " + size);
    }

    @Override
    public void onPeopleListChange(List<People> peoples) {
        Log.d("TAG", "demo2 people变化了" + peoples.size());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                mPeopleManager.addPeople(new People("demo2", 22));
                Log.d("TAG", "btn1点击了添加people");
                break;
            default:
                break;
        }
    }
}
