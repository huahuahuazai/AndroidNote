package cf.android666.mykotlin.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import java.util.*

/**
 * Created by jixiaoyong on 2018/2/6.
 */

class MenuAdapter(context: Context,layoutId:Int,data:ArrayList<HashMap<String,Objects>>)
    : ArrayAdapter<String>(context,layoutId) {

    override fun getItem(position: Int): String {
        return super.getItem(position)

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return super.getView(position, convertView, parent)

    }

    override fun getPosition(item: String?): Int {
        return super.getPosition(item)

    }

}
