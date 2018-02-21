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
import cf.android666.mykotlin.adapter.MPagerAdapter
import cf.android666.mykotlin.base.BaseFragment
import cf.android666.mykotlin.fragment.Fragment1
import cf.android666.mykotlin.fragment.Fragment2
import cf.android666.mykotlin.fragment.Fragment3
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

// 待实现
// 玩转AppBarLayout，更酷炫的顶部栏 - 简书  https://www.jianshu.com/p/d159f0176576
// CoordinatorLayout的使用如此简单 - CSDN博客  http://blog.csdn.net/huachao1001/article/details/51554608

    var bottomNavIds = arrayListOf(R.id.menu1, R.id.menu2, R.id.menu3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            drawer_layout.openDrawer(menu_layout)
        }


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

        val fragment2 = Fragment2()

//        fragment2.listener = {
//            LogTools.logd("main frag2 click")
//
//            it.web_view.loadUrl("http://jixiaoyong.github.io/blog")
//        }

        arrayList.add(fragment1)
        arrayList.add(fragment2)
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


        bottom_nav.setOnNavigationItemSelectedListener(
                { item ->

                    when (item.itemId) {
                        R.id.menu1 -> {
                            view_pager.currentItem = 0
                            return@setOnNavigationItemSelectedListener true
                        }
                        R.id.menu2 -> {
                            view_pager.currentItem = 1
                            return@setOnNavigationItemSelectedListener true
                        }
                        R.id.menu3 -> {
                            view_pager.currentItem = 2
                            return@setOnNavigationItemSelectedListener true
                        }
                        else -> return@setOnNavigationItemSelectedListener false
                    }

                }
        )

        menu_layout.setNavigationItemSelectedListener {

            var url = "http://gityuan.com/"

            when (it.itemId) {
                R.id.about -> {openUrl(url + "about/")
                    return@setNavigationItemSelectedListener true}
                R.id.archive -> {openUrl(url + "archive/")
                    return@setNavigationItemSelectedListener true}
                R.id.friends -> {openUrl(url + "friends/")
                    return@setNavigationItemSelectedListener true}
                else -> {
                    return@setNavigationItemSelectedListener false
                }
            }

        }


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

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//
//        when (item!!.itemId) {
//        //do sth...
//        }
//
//        return true
//    }
}

