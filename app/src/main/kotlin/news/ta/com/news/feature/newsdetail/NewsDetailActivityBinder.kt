package news.ta.com.news.feature.newsdetail

import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import news.ta.com.news.databinding.ActivityNewsDetailsBinding

class NewsDetailActivityBinder(activity: AppCompatActivity, binding: ActivityNewsDetailsBinding?) {

    val viewModel = ViewModelProviders.of(activity).get(NewsDetailsViewModel::class.java)
    val view: NewsDetailActivityView
    init {
        view = NewsDetailActivityViewImpl(activity, binding, viewModel)
    }
}