package cf.android666.mykotlin.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_recycler.view.*

class RecyclerAdapter(context: Context,layoutId : Int,data : ArrayList<Map<String,String>>) : RecyclerView.Adapter<RecyclerAdapter.MViewHolder>() {

    private val mContext: Context = context

    private val mLayoutId : Int = layoutId

    private var mData = data

    override fun onBindViewHolder(holder: MViewHolder?, position: Int) {

        holder!!.text.text = mData[position]["title"]

    }

    override fun getItemCount(): Int {
       return mData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MViewHolder {

        val view: View = View.inflate(mContext, mLayoutId, null)

        return MViewHolder(view)

    }


    class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var text :TextView = view.text
    }
}