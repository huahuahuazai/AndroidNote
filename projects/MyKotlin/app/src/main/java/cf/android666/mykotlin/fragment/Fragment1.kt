package cf.android666.mykotlin.fragment


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cf.android666.mykotlin.R
import cf.android666.mykotlin.adapter.RecyclerAdapter
import cf.android666.mykotlin.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment1.view.*

class Fragment1 : BaseFragment() {

    private lateinit var mListener: onClickListener

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view: View = inflater!!.inflate(R.layout.fragment1, container, false)

        val data : ArrayList<Map<String,String>> = getData()

        var recycler = view.recycler

        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        recycler.adapter = RecyclerAdapter(context, R.layout.item_recycler,data)


        return view

    }

    private fun getData(): ArrayList<Map<String, String>> {

        var array : ArrayList<Map<String, String>> = arrayListOf()

        for (x in 1..20) {
            var map  = hashMapOf<String,String>()
            map.put("title","title is $x")
            map.put("summary","summary is $x")
            map.put("url","http://www.android666.cf")
            array.add(map)
        }

        return array
    }

    fun setOnClickListener(x: onClickListener) {
        mListener = x
    }

    interface onClickListener{
        fun listener(x: Any)
    }

}