package news.ta.com.news.feature.newsdetail

import androidx.appcompat.app.AppCompatActivity
import news.ta.com.news.common.replaceWith
import news.ta.com.news.databinding.ActivityNewsDetailsBinding
import news.ta.com.news.feature.newslist.NewsItem

interface NewsDetailActivityView

class NewsDetailActivityViewImpl(activity: AppCompatActivity, binding: ActivityNewsDetailsBinding?, viewModel: NewsDetailsViewModel) : NewsDetailActivityView {
    private val newsItem = activity.intent?.getSerializableExtra(NewsDetailActivity.EXTRA_NEWS_ITEM) as NewsItem

    init {
        with(activity) {
            supportFragmentManager.replaceWith(binding?.detail?.id, news.ta.com.news.feature.newsdetail.NewsDetailFragment.newInstance())
            setSupportActionBar(binding?.toolbar)
            kotlin.with(this.supportActionBar) {
                this?.setDisplayHomeAsUpEnabled(true)
                this?.setDisplayShowHomeEnabled(true)
            }
        }

        viewModel.item.set(newsItem)
        binding?.title = newsItem.headline
    }
}