package cf.android666.mykotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cf.android666.mykotlin.utils.LogTools
import kotlinx.android.synthetic.main.activity_content.*

class ContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        var bounde  = intent.extras

        var urlq = bounde["url"]

        LogTools.logd("url is ${intent!!.getStringExtra("url")}")

        var url = "http://gityuan.com/"

        web_view.loadUrl(url)

    }

}