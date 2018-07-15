package news.ta.com.news.feature.hackernewsdetail

import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import com.dgreenhalgh.android.simpleitemdecoration.linear.EndOffsetItemDecoration
import com.dgreenhalgh.android.simpleitemdecoration.linear.StartOffsetItemDecoration
import news.ta.com.news.R
import news.ta.com.news.databinding.ActivityHackerNewDetailsBinding
import news.ta.com.news.feature.hackernewslist.HackerNewsItem
import news.ta.com.news.feature.hackernewslist.HackerNewsViewModel

interface HackerNewsDetailsView {
    fun setItem(items: List<HackerNewsItem>?)
}

class HackerNewsDetailsViewImpl(val binding: ActivityHackerNewDetailsBinding?,
                                private val shareViewModel: HackerNewsViewModel,
                                val activity: FragmentActivity) : HackerNewsDetailsView {

    init {
        with(binding!!.list) {
            isNestedScrollingEnabled = false

            val pixelSize = context.resources.getDimensionPixelSize(R.dimen.gap_m)
            addItemDecoration(StartOffsetItemDecoration(pixelSize))
            addItemDecoration(EndOffsetItemDecoration(pixelSize))

            val drawable = ContextCompat.getDrawable(context, R.drawable.decor_m)
            addItemDecoration(DividerItemDecoration(drawable))
            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(context)

            adapter = HackerNewsKidsAdapter(shareViewModel, activity)
        }
    }

    override fun setItem(items: List<HackerNewsItem>?) {
        (binding!!.list.adapter as HackerNewsKidsAdapter).items = items ?: emptyList()
    }
}