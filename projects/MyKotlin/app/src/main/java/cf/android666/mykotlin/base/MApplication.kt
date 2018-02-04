package cf.android666.mykotlin.base

import android.app.Application
import cf.android666.mykotlin.utils.LogTools

/**
 * Created by jixiaoyong on 2018/2/4.
 */

class MApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        LogTools.isDebug = true
    }
}