package news.ta.com.news.feature.newsdetail

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import news.ta.com.news.databinding.ActivityNewsDetailsBinding

class NewsDetailActivityBinder(activity: AppCompatActivity, binding: ActivityNewsDetailsBinding?) {

    val viewModel:NewsDetailsViewModel by activity.viewModels()
    val view: NewsDetailActivityView
    init {
        view = NewsDetailActivityViewImpl(activity, binding, viewModel)
    }
}