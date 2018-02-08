package cf.android666.mykotlin.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cf.android666.mykotlin.R
import cf.android666.mykotlin.base.BaseFragment
import cf.android666.mykotlin.utils.SuperUtil
import kotlinx.android.synthetic.main.fragment2.*
import kotlinx.android.synthetic.main.fragment2.view.*

class Fragment2 : BaseFragment() ,View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!.id) {

            R.id.back_ibtn -> if (web_view.canGoBack())web_view.goBack()
            R.id.refresh_ibtn -> web_view.reload()
            R.id.forward_ibtn -> if (web_view.canGoForward())web_view.goForward()

        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view: View = inflater!!.inflate(R.layout.fragment2, container, false)

        var url = "http://jixiaoyong.github.io/"

        SuperUtil.loadUrl(view.web_view,url)

        view.back_ibtn.setOnClickListener(this)
        view.refresh_ibtn.setOnClickListener(this)
        view.forward_ibtn.setOnClickListener(this)

        return view

    }


}

