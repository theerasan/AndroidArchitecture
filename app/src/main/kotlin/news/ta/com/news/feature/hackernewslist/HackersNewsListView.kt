package news.ta.com.news.feature.hackernewslist

import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import com.dgreenhalgh.android.simpleitemdecoration.linear.EndOffsetItemDecoration
import com.dgreenhalgh.android.simpleitemdecoration.linear.StartOffsetItemDecoration
import news.ta.com.news.R
import news.ta.com.news.databinding.FragmentNewsListBinding

interface HackersNewsListView {
    fun setItem(items: List<HackerNewsItem>?)
}

class HackersNewsListViewImpl(fragment: Fragment, val binding: FragmentNewsListBinding, viewModel: HackerNewsViewModel) : HackersNewsListView {

    init {
        with(binding.list) {
            isNestedScrollingEnabled = false

            val pixelSize = context.resources.getDimensionPixelSize(R.dimen.gap_m)
            addItemDecoration(StartOffsetItemDecoration(pixelSize))
            addItemDecoration(EndOffsetItemDecoration(pixelSize))

            val drawable = ContextCompat.getDrawable(context, R.drawable.decor_m)
            addItemDecoration(DividerItemDecoration(drawable))
            setHasFixedSize(true)

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)

            adapter = HackerNewsListAdapter(viewModel)
        }
    }

    override fun setItem(items: List<HackerNewsItem>?) {
        (binding.list.adapter as HackerNewsListAdapter).items = items ?: emptyList()
    }
}