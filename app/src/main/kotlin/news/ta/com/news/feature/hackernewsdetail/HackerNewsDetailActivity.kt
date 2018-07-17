package news.ta.com.news.feature.hackernewsdetail

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import news.ta.com.news.R
import news.ta.com.news.databinding.ActivityHackerNewDetailsBinding

class HackerNewsDetailActivity : AppCompatActivity() {

    var binding: ActivityHackerNewDetailsBinding? = null
    var binder: HackerNewsActivityBinder? = null

    companion object {

        val EXTRA_NEWS_ID = "news.ta.com.news.feature.hackernewsdetail.EXTRA_NEWS_ID"

        fun route(context: Context, id: Int) {
            val i = Intent(context, HackerNewsDetailActivity::class.java)
            i.putExtra(EXTRA_NEWS_ID, id)
            i.flags = FLAG_ACTIVITY_NEW_TASK
            context.startActivity(i)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_ACTION_BAR)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hacker_new_details)
        binder = HackerNewsActivityBinder(this, binding)
        binder?.bindTo(this)

        binding?.toolbar?.let {
            setSupportActionBar(it)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}