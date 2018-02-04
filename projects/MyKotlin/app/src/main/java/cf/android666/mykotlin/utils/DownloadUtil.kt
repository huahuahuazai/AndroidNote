package cf.android666.mykotlin.utils

import android.os.Handler
import android.os.Message
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection
import kotlin.concurrent.thread

/**
 * Created by jixiaoyong on 2018/2/4.
 */
object DownloadUtil{

    fun downloadJson(urlStr: String, handler: Handler,msgWhat: Int) {

        thread {
            kotlin.run {

                var url:URL = URL(urlStr)
                var urlConn : URLConnection = url.openConnection()
                var input :InputStream = urlConn.getInputStream()
                var bufferedReader: BufferedReader = BufferedReader(InputStreamReader(input))

                var stringBuffer: StringBuffer = StringBuffer()

                var line = ""

                while (true) {

                    line = bufferedReader.readLine()?:break
                    stringBuffer.append(line)

                }

                var jsonObject = JSONObject(String(stringBuffer))

                var msg = Message()

                msg.what = msgWhat

                msg.obj = jsonObject

                handler.sendMessage(msg)
            }
        }

    }

}

/**
 * {
url: "http://www.gityuan.com",
total: "17",
update_time: "1517154204",
file_name: "gityuan_page_"
}
 *
 * */