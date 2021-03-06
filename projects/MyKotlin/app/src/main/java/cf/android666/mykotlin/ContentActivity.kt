package cf.android666.mykotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cf.android666.mykotlin.utils.SuperUtil
import kotlinx.android.synthetic.main.activity_content.*

class ContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        var url = intent.getStringExtra("url")
        SuperUtil.loadUrl(web_view,url)
    }

}