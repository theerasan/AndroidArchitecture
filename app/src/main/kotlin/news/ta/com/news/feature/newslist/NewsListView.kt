package news.ta.com.news.feature.newslist

import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import com.dgreenhalgh.android.simpleitemdecoration.linear.EndOffsetItemDecoration
import com.dgreenhalgh.android.simpleitemdecoration.linear.StartOffsetItemDecoration
import news.ta.com.news.R
import news.ta.com.news.databinding.FragmentNewsListBinding
import news.ta.com.news.feature.newslist.NewsListFragment.Companion.HAS_DETAIL

interface NewsListView {
    fun setItems(items: List<NewsItem>?)
}

class NewsListViewImpl(fragment: Fragment, val binding: FragmentNewsListBinding) : NewsListView {

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

            adapter = NewsListAdapter(binding.viewModel!!)
        }

        binding.viewModel?.hasDetailView = fragment.arguments!!.getBoolean(HAS_DETAIL, false)
    }

    override fun setItems(items: List<NewsItem>?) {
        (binding.list.adapter as NewsListAdapter).items = items ?: emptyList()
    }
}