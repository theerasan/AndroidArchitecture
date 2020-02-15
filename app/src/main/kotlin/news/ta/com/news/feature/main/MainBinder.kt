package news.ta.com.news.feature.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import news.ta.com.news.feature.newsdetail.NewsDetailsViewModel

class MainBinder(val viewModel: NewsDetailsViewModel, val view: MainView) {
    init {
        view.showList()
        view.showDetail()
    }

    fun bindTo(owner: LifecycleOwner) {
        viewModel.selectedEvent.observe(owner, Observer { view.scrollDetailToTop() })
    }
}