package cf.android666.mykotlin.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cf.android666.mykotlin.R
import cf.android666.mykotlin.base.BaseFragment
import cf.android666.mykotlin.utils.SuperUtil
import kotlinx.android.synthetic.main.fragment3.view.*

class Fragment3 : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view: View = inflater!!.inflate(R.layout.fragment3, container, false)

        var url = "http://jixiaoyong.github.io/download/data/gityuan_blog_data/aboutme.html"

        SuperUtil.loadUrl(view.web_view,url)

        return view

    }

}