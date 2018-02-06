package cf.android666.mykotlin

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.SimpleAdapter
import cf.android666.mykotlin.adapter.MPagerAdapter
import cf.android666.mykotlin.fragment.BaseFragment
import cf.android666.mykotlin.fragment.Fragment1
import cf.android666.mykotlin.fragment.Fragment2
import cf.android666.mykotlin.fragment.Fragment3
import cf.android666.mykotlin.utils.LogTools
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var bottomNavIds = arrayListOf(R.id.menu1, R.id.menu2, R.id.menu3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //暂时禁止横屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        requestPermission()

        var arrayList: ArrayList<BaseFragment> = arrayListOf()

        val fragment1 = Fragment1()

        val isLand = this.resources.configuration.layoutDirection == Configuration.ORIENTATION_LANDSCAPE

        fragment1.mListener = { url ->

            //            if (!isLand) {
            openUrl(url.replace("http:// ", "http://"))
//            } else {
////                fragment1.web_view?.loadUrl(murl)
//            }

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

        var data: ArrayList<HashMap<String, Any>> = arrayListOf()

        initData(data)

        list.adapter = SimpleAdapter(this, data, R.layout.item_menu_list,
                arrayOf("menu","icon"), intArrayOf(R.id.text,R.id.icon))

        list.setOnItemClickListener { parent, view, position, id ->

            LogTools.logd("position is $position,url is ${data[position]["url"].toString()}")

            openUrl(data[position]["url"].toString())

        }

    }

    private fun initData(data: ArrayList<HashMap<String, Any>>) {

        var map = hashMapOf<String, Any>()
        map["menu"] = "About" as Any
        map["icon"] = R.drawable.about as Any
        map["url"] = "http://gityuan.com/about/" as Any
        data.add(map)
        map = hashMapOf()
        map["menu"] = "Archive" as Any
        map["icon"] = R.drawable.archive as Any
        map["url"] = "http://gityuan.com/archive/" as Any
        data.add(map)
        map = hashMapOf()
        map["menu"] = "Friends" as Any
        map["icon"] = R.drawable.friends as Any
        map["url"] = "http://gityuan.com/friends/" as Any
        data.add(map)

    }

    fun requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), 1)
        }

    }

    fun openUrl(url: String) {

        val intent = Intent(this@MainActivity, ContentActivity::class.java)

        intent.putExtra("url", url)

        startActivity(intent)
    }
}

