package news.ta.com.news.feature.main

import androidx.core.widget.NestedScrollView
import androidx.fragment.app.FragmentManager
import news.ta.com.news.R
import news.ta.com.news.common.replaceWith
import news.ta.com.news.databinding.ActivityMainBinding
import news.ta.com.news.feature.newsdetail.NewsDetailFragment
import news.ta.com.news.feature.newslist.NewsListFragment

interface MainView {
    fun scrollDetailToTop()
    fun showList()
    fun showDetail()
}

class MainViewImpl(val binding: ActivityMainBinding?, private val fm: FragmentManager) : MainView {
    override fun scrollDetailToTop() {
        val scrollView = binding?.detail?.findViewById<NestedScrollView>(R.id.scrollContainer)
        scrollView?.scrollTo(0, 0)
    }

    override fun showList() {
        fm.replaceWith(binding?.listItem?.id, NewsListFragment.newInstance(binding?.detail != null))
    }

    override fun showDetail() {
        fm.replaceWith(binding?.detail?.id, NewsDetailFragment.newInstance())
    }
}