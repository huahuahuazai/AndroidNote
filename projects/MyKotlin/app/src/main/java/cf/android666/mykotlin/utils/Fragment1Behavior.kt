package cf.android666.mykotlin.utils

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView




/**
 * Created by jixiaoyong on 2018/2/7.
 */
class Fragment1Behavior(var context: Context, attributeSet: AttributeSet)
    : CoordinatorLayout.Behavior<ImageView>(context, attributeSet) {

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: ImageView?, dependency: View?): Boolean {
        return dependency is RecyclerView

    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: ImageView?, dependency: View?): Boolean {

        val top = dependency!!.top
        val left = dependency!!.left

        val display = context.resources.displayMetrics
        var width = display.widthPixels

        val x = width - left - child!!.width

        setPosition(child, x, top)
        return true

    }

    private fun setPosition(v: View, x: Int, y: Int) {
        val layoutParams = v.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.leftMargin = x
        layoutParams.topMargin = y
        v.layoutParams = layoutParams
    }
}