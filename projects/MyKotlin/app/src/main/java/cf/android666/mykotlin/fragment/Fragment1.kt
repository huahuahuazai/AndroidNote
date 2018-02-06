package cf.android666.mykotlin.fragment


import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cf.android666.mykotlin.R
import cf.android666.mykotlin.adapter.RecyclerAdapter
import cf.android666.mykotlin.base.BaseFragment
import cf.android666.mykotlin.utils.DownloadUtil
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

    var page = 1

    var mListener : ((url:String) -> Unit)? = null

    val mData: ArrayList<Map<String, String>> = arrayListOf()


    val handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            if (msg!!.what == msgWhat1) {
                var jsonObject: JSONObject = msg.obj as JSONObject
                var map: Map<String, Objects>
                var url = jsonObject.getString("url")
                var total = jsonObject.getInt("total")
                var updateTime = jsonObject.getInt("update_time")
                var fileName = jsonObject.getString("file_name")

                var fileUrl = "http://jixiaoyong.github.io/download/data/gityuan_blog_data/$fileName$page.json"

                DownloadUtil.downloadJson(fileUrl, this, 1)

            } else if (msg!!.what == msgWhat2){
                var jsonObject: JSONObject = msg.obj as JSONObject
                var jsonArray:JSONArray = jsonObject.getJSONArray("data")

                //最后一条是空数据
                for (i in 0..jsonArray.length() - 2) {
                    var jsonObj = jsonArray.getJSONObject(i)
                    var map = hashMapOf<String, String>()
                    map.put("title",jsonObj.getString("title").trim())
                    map.put("summary", jsonObj.getString("summary"))
                    map.put("url", jsonObj.getString("url"))
                    mData.add(map)
                    recycler.adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view: View = inflater!!.inflate(R.layout.fragment1, container, false)

        var recycler = view.recycler

        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        recycler.adapter = RecyclerAdapter(context, R.layout.item_recycler, mData){url ->  mListener?.invoke(url)}

        getData()

        return view

    }

    private fun getData(){

        DownloadUtil.downloadJson(mConfigUrl, handler, msgWhat1)

    }

}