package cf.android666.mykotlin.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cf.android666.mykotlin.R
import cf.android666.mykotlin.base.BaseFragment
import kotlinx.android.synthetic.main.fragment2.view.*

class Fragment2 : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view: View = inflater!!.inflate(R.layout.fragment2, container, false)

        view.web_view.loadUrl("http://jixiaoyong.github.io/blog/")

        return view

    }

}