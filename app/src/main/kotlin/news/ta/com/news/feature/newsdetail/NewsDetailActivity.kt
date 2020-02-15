package news.ta.com.news.feature.newsdetail

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Window
import news.ta.com.news.R
import news.ta.com.news.databinding.ActivityNewsDetailsBinding
import news.ta.com.news.feature.main.MainModule
import news.ta.com.news.feature.newslist.NewsItem
import org.koin.android.scope.currentScope
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

class NewsDetailActivity : AppCompatActivity() {
    var binding: ActivityNewsDetailsBinding? = null
    var binder: NewsDetailActivityBinder? = null
    var module: Module? = null

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
        module = NewsDetailModule().getModule(this, binding)
        module?.let {
            loadKoinModules(it)
        }
        binder = currentScope.get()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        module?.let {
            unloadKoinModules(it)
        }
    }
}