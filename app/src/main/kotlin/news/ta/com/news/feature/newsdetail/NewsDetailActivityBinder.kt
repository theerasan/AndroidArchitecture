package news.ta.com.news.feature.newsdetail

import androidx.appcompat.app.AppCompatActivity
import news.ta.com.news.databinding.ActivityNewsDetailsBinding

class NewsDetailActivityBinder(activity: AppCompatActivity, binding: ActivityNewsDetailsBinding?, viewModel: NewsDetailsViewModel) {

    val view: NewsDetailActivityView
    init {
        view = NewsDetailActivityViewImpl(activity, binding, viewModel)
    }
}