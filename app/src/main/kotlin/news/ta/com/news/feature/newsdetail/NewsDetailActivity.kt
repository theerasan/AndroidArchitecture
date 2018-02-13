package news.ta.com.news.feature.newsdetail

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import news.ta.com.news.R
import news.ta.com.news.databinding.ActivityNewsDetailsBinding
import news.ta.com.news.feature.newslist.NewsItem

class NewsDetailActivity : AppCompatActivity() {
    var binding: ActivityNewsDetailsBinding? = null
    var binder: NewsDetailActivityBinder? = null

    companion object {

        val EXTRA_NEWS_ITEM = "news.ta.com.news.feature.newsdetail.EXTRA_NEWS_ITEM"

        fun route(context: Context, item: NewsItem) {
            val i = Intent(context, NewsDetailActivity::class.java)
            i.putExtra(EXTRA_NEWS_ITEM, item)
            context.startActivity(i)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_ACTION_BAR)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_details)
        binder = NewsDetailActivityBinder(this, binding)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}