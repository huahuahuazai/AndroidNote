package cf.android666.mykotlin.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import cf.android666.mykotlin.base.BaseFragment
import java.util.*

class MPagerAdapter(arrayList: ArrayList<BaseFragment>, fm:FragmentManager) : FragmentPagerAdapter(fm) {

    private val mArraylist = arrayList

    override fun getItem(position: Int): Fragment? {
        return mArraylist[position]
    }

    override fun getCount(): Int {
        return mArraylist.size
    }

}