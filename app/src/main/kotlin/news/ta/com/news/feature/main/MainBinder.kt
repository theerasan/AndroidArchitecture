package news.ta.com.news.feature.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import news.ta.com.news.databinding.ActivityMainBinding
import news.ta.com.news.feature.newsdetail.NewsDetailsViewModel

class MainBinder(activity: AppCompatActivity, binding: ActivityMainBinding?) {

    val view: MainView
    private val router: MainRouter = MainRouterImpl(activity.supportFragmentManager, binding)
    private val newsDetailViewModel = ViewModelProviders.of(activity).get(NewsDetailsViewModel::class.java)

    init {
        view = MainViewImpl(binding)
        router.showList()
        router.showDetail()
    }

    fun bindTo(owner: LifecycleOwner) {
        newsDetailViewModel.selectedEvent.observe(owner, Observer { view.scrollDetailToTop() })
    }
}