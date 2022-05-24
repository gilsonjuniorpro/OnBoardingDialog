package onboardingdialog.ca.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import onboardingdialog.ca.R

class IndicatorView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var imageViews: Array<ImageView?>? = null

    fun setRecyclerView(recyclerView: RecyclerView?,
        layoutManager: LinearLayoutManager?, countList: Int, last: Int) {
        val itemCount = layoutManager?.itemCount ?: 0
        if (itemCount < 1) return

        val indicatorActiveDrawable = AppCompatResources.getDrawable(context, R.drawable.indicator_selected_state)
        val indicatorInactiveDrawable = AppCompatResources.getDrawable(context, R.drawable.indicator_default_state)

        val lastObj = if (countList == last) last - 1 else last

        if (countList > 1) {
            configIndicator(countList, lastObj, indicatorActiveDrawable, indicatorInactiveDrawable)
        } else {
            removeAllViews()
        }

        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val position = if (layoutManager?.findFirstCompletelyVisibleItemPosition() != -1) {
                    layoutManager?.findFirstCompletelyVisibleItemPosition()
                } else {
                    layoutManager.findLastVisibleItemPosition()
                }

                countList.let {
                    imageViews?.let { imageViewsObj ->
                        if (it == imageViewsObj.size)
                            for (i in 0 until it) {
                                imageViewsObj[i]?.setImageDrawable(indicatorInactiveDrawable)
                            }
                    }
                }

                position?.let {
                    imageViews?.get(it)?.setImageDrawable(indicatorActiveDrawable)
                }
            }
        })
    }

    private fun configIndicator(itemCount: Int, selectedIndex: Int = 0, indicatorActive: Drawable?,
        indicatorInactive: Drawable?) {

        imageViews = arrayOfNulls(itemCount)
        var indexSelection = 0
        if (selectedIndex != -1) {
            indexSelection = selectedIndex
        }

        removeAllViews()
        imageViews?.let {
            for (i in 0 until itemCount) {
                it[i] = ImageView(context)
                it[i]?.setImageDrawable(indicatorInactive)
                val dotSize = resources.getDimensionPixelSize(R.dimen.indicator_dot_size)
                val params = LayoutParams(dotSize, dotSize)
                params.setMargins(dotSize / 2, 0, dotSize / 2, 0)
                it[i]?.layoutParams = params
                addView(it[i])
            }
            it[indexSelection]?.setImageDrawable(indicatorActive)
        }
    }
}
