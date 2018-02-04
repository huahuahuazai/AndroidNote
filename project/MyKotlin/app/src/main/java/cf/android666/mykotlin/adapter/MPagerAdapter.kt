package cf.android666.mykotlin

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList

class MPagerAdapter(arrayList: ArrayList<BaseFragment>,fm:FragmentManager) : FragmentPagerAdapter(fm) {

    private val mArraylist = arrayList

    override fun getItem(position: Int): Fragment? {
        return mArraylist[position]
    }

    override fun getCount(): Int {
        return mArraylist.size
    }

}