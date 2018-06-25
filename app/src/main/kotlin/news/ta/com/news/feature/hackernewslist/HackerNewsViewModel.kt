package news.ta.com.news.feature.hackernewslist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import news.ta.com.news.feature.NewsApplication
import javax.inject.Inject

class HackerNewsItem(val id: Int = 0,
                     val idTemp: String = "",
                     val text: String = "")

class HackerNewsViewModel: ViewModel() {
    @Inject
    lateinit var repository: HackerNewsRepository

    val items: LiveData<List<HackerNewsItem>>
            get() = repository.getNews()

    init {
        NewsApplication.applicationComponent.inject(this)
    }
}