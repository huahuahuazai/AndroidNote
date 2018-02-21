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
//
//        view.setting_night_model.setOnCheckedChangeListener { v, isChecked ->
//            updateTheme(isChecked)
//            LogTools.logd("button is $isChecked")
//        }

        return view

    }

    private fun updateTheme(b: Boolean) {
        if (!b) {
            activity.theme.applyStyle(R.style.AppTheme,true)
        } else {
            activity.theme.applyStyle(R.style.AppTheme_Night,true)
        }
    }

}