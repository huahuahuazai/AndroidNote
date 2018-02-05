package cf.android666.mykotlin

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import cf.android666.mykotlin.utils.LogTools
import kotlinx.android.synthetic.main.activity_content.*

class ContentActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_content)

        val url = intent.getStringExtra("url")

        LogTools.logd("url is $url")

        web_view.loadUrl(url)

    }
}