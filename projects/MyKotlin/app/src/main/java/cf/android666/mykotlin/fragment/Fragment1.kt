package cf.android666.mykotlin.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cf.android666.mykotlin.R
import cf.android666.mykotlin.adapter.RecyclerAdapter
import cf.android666.mykotlin.base.BaseFragment
import cf.android666.mykotlin.utils.DownloadUtil
import cf.android666.mykotlin.utils.SuperUtil
import kotlinx.android.synthetic.main.fragment1.*
import kotlinx.android.synthetic.main.fragment1.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class Fragment1 : BaseFragment() {

    var mConfigUrl: String = "http://jixiaoyong.github.io/download/data/gityuan_blog_data/config.json"

    //获取api总的信息
    val msgWhat1 = 0

    val msgWhat2 = 1

    var total = 0

    var page = 1

    var mListener : ((url:String) -> Unit)? = null

    val mData: ArrayList<Map<String, String>> = arrayListOf()

    var isLoading = false

    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {

            if (msg!!.obj == null) {

                SuperUtil.toast(context,"数据有误，加载失败，请稍后重试~")

                return
            }

            if (msg!!.what == msgWhat1) {

                var jsonObject: JSONObject = msg.obj as JSONObject

                var map: Map<String, Objects>
                var url = jsonObject.getString("url")
                total = jsonObject.getInt("total")
                var updateTime = jsonObject.getInt("update_time")
                var fileName = jsonObject.getString("file_name")

                var fileUrl = "http://jixiaoyong.github.io/download/data/gityuan_blog_data/$fileName$page.json"

                DownloadUtil.downloadJson(fileUrl, this, 1)

            } else if (msg!!.what == msgWhat2) {

                var jsonObject: JSONObject = msg.obj as JSONObject
                var jsonArray: JSONArray = jsonObject.getJSONArray("data")

                var begin = 0

                if (page == 1) {
                    mData.clear()
                } else {
                    //去掉重复的置顶文章
                    begin = 1
                }

                //最后一条是空数据
                for (i in begin..jsonArray.length() - 2) {

                    isLoading = false

                    var jsonObj = jsonArray.getJSONObject(i)

                    var map = hashMapOf<String, String>()
                    map.put("title", jsonObj.getString("title").trim())
                    map.put("summary", jsonObj.getString("summary"))
                    map.put("url", jsonObj.getString("url"))

                    mData.add(map)


                }

                recycler.adapter.notifyDataSetChanged()

                swipe_refresh.isRefreshing = false

                SuperUtil.toast(context, "已更新~")
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view: View = inflater!!.inflate(R.layout.fragment1, container, false)

        var recycler = view.recycler

        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        recycler.adapter = RecyclerAdapter(context, R.layout.item_recycler, mData){url ->  mListener?.invoke(url)}

        recycler.setOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

                super.onScrolled(recyclerView, dx, dy)

                val manager = recycler.layoutManager as LinearLayoutManager

                val lastP = manager.findLastVisibleItemPosition()

                val totalP = manager.itemCount

                if (lastP > totalP - 3 && dy > 0) {
                    if (isLoading) {

                    } else {

                        page += 1

                        if (page > total) {

                            SuperUtil.toast(context, "没有更多了")

                        } else {

                            getData()

                            isLoading = true
                        }

                    }
                }

            }
        })



        getData()

        view.swipe_refresh.setOnRefreshListener {

            page = 1

            getData()
        }


        return view

    }

    private fun getData(){

        DownloadUtil.downloadJson(mConfigUrl, handler, msgWhat1)

    }

}