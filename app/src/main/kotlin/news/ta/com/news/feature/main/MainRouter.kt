package news.ta.com.news.feature.main

import android.support.v4.app.FragmentManager
import news.ta.com.news.common.replaceWith
import news.ta.com.news.databinding.ActivityMainBinding
import news.ta.com.news.feature.hackernewslist.HackerNewListFragment
import news.ta.com.news.feature.newsdetail.NewsDetailFragment

interface MainRouter {
    fun showList()
    fun showDetail()
}

class MainRouterImpl(val fm: FragmentManager, val binding: ActivityMainBinding?) : MainRouter {
    override fun showList() {
        fm.replaceWith(binding?.listItem?.id, HackerNewListFragment.newInstance())
    }

    override fun showDetail() {
        fm.replaceWith(binding?.detail?.id, NewsDetailFragment.newInstance())
    }
}