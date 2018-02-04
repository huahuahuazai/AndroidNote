package cf.android666.mykotlin

import android.app.FragmentTransaction
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.MenuItem
import android.widget.Toast
import cf.android666.mykotlin.fragment.Fragment2
import cf.android666.mykotlin.fragment.Fragment3
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var mContext: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var arrayList : ArrayList<BaseFragment>  = arrayListOf()

        arrayList.add(Fragment1())
        arrayList.add(Fragment2())
        arrayList.add(Fragment3())

        var fm  = supportFragmentManager

        view_pager.adapter = MPagerAdapter(arrayList,fm)

        bottom_nav.setOnNavigationItemReselectedListener(
                {
                    var id = it.itemId
                    when (id) {
                        R.id.menu1 -> view_pager.currentItem = 1
                        R.id.menu2 -> {toast("menu2");view_pager.currentItem = 2

                        }
                        R.id.menu3 -> {toast("menu3");view_pager.currentItem = 3
                        }
                    }

                }
        )

    }

    private fun toast(s: String) {
        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show()
    }
}
