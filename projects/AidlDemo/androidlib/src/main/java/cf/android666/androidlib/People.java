package cf.android666.androidlib;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jixiaoyong on 2018/8/6.
 * email:jixiaoyong1995@gmail.com
 */
public class People implements Parcelable {

    private String name;
    private int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name :" + name + ",age:" + age;
    }

    protected People(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    public static final Creator<People> CREATOR = new Creator<People>() {
        @Override
        public People createFromParcel(Parcel in) {
            return new People(in);
        }

        @Override
        public People[] newArray(int size) {
            return new People[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }
}
