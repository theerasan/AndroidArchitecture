package news.ta.com.news.feature.newslist

import android.content.Context
import news.ta.com.news.feature.newsdetail.NewsDetailActivity
import news.ta.com.news.feature.newsdetail.NewsDetailsViewModel

interface NewsListRouter {
    fun showDetail(detailViewModel: NewsDetailsViewModel, item: NewsItem?)
    fun gotoDetail(item: NewsItem?)
}

class NewsListRouterImpl(val context: Context) : NewsListRouter {
    override fun showDetail(detailViewModel: NewsDetailsViewModel, item: NewsItem?) {
        detailViewModel.item.set(item)
    }

    override fun gotoDetail(item: NewsItem?) {
        if (item == null) return
        NewsDetailActivity.route(context, item)
    }
}