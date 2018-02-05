package cf.android666.mykotlin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import cf.android666.mykotlin.adapter.MPagerAdapter
import cf.android666.mykotlin.fragment.BaseFragment
import cf.android666.mykotlin.fragment.Fragment1
import cf.android666.mykotlin.fragment.Fragment2
import cf.android666.mykotlin.fragment.Fragment3
import cf.android666.mykotlin.utils.LogTools
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var bottomNavIds = arrayListOf<Int>(R.id.menu1, R.id.menu2, R.id.menu3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermission()

        var arrayList: ArrayList<BaseFragment> = arrayListOf()

        val fragment1 = Fragment1()

        val isLand = this.resources.configuration.layoutDirection == Configuration.ORIENTATION_LANDSCAPE

        fragment1.mListener = { url ->

            val murl = url.replace("http:// ", "http://")
//
//            if (!isLand) {
            val intent = Intent(this@MainActivity, ContentActivity::class.java)

            var bound = Bundle()

            bound.putString("url",url)

            intent.putExtras(bound)

            intent.putExtra("url", murl)

            LogTools.logd("url is $murl")

            startActivity(intent)
//            } else {
////                fragment1.web_view?.loadUrl(murl)
//            }

            startActivity(Intent(this, ContentActivity::class.java))
        }

        arrayList.add(fragment1)
        arrayList.add(Fragment2())
        arrayList.add(Fragment3())

        var fm = supportFragmentManager

        view_pager.adapter = MPagerAdapter(arrayList, fm)

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                bottom_nav.selectedItemId = bottomNavIds[position]

            }
        })


        bottom_nav.setOnNavigationItemReselectedListener(
                { item ->

                    var id = item.itemId

                    when (id) {
                        R.id.menu1 -> view_pager.currentItem = 0
                        R.id.menu2 -> view_pager.currentItem = 1
                        R.id.menu3 -> view_pager.currentItem = 2
                    }

                }
        )


    }


    private fun toast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    fun requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), 1)
        }

    }
}
