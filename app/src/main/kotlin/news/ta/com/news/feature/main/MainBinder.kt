package news.ta.com.news.feature.main

import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.appcompat.app.AppCompatActivity
import news.ta.com.news.databinding.ActivityMainBinding
import news.ta.com.news.feature.newsdetail.NewsDetailsViewModel

class MainBinder(activity: AppCompatActivity, binding: ActivityMainBinding?) {

    val view: MainView
    private val newsDetailViewModel: NewsDetailsViewModel by activity.viewModels()

    init {
        view = MainViewImpl(binding, activity.supportFragmentManager)
        view.showList()
        view.showDetail()
    }

    fun bindTo(owner: LifecycleOwner) {
        newsDetailViewModel.selectedEvent.observe(owner, Observer { view.scrollDetailToTop() })
    }
}