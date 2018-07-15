package news.ta.com.news.feature.hackernewslist

import android.content.Context
import news.ta.com.news.feature.hackernewsdetail.HackerNewsDetailActivity

interface HackerNewsListRouter {
    fun gotoDetail(id:Int)
}

class HackerNewsListRouterImpl(val context: Context): HackerNewsListRouter {
    override fun gotoDetail(id: Int) {
        HackerNewsDetailActivity.route(context, id)
    }
}