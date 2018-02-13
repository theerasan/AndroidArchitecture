package news.ta.com.news.feature.newslist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import news.ta.com.news.common.livedata.SingleLiveEvent
import news.ta.com.news.feature.NewsApplication
import java.io.Serializable
import javax.inject.Inject

class NewsItem(val id: Int = 0,
               val thumbnail: String = "",
               val headline: String = "--",
               val description: String = "--",
               val link: String = "",
               val source: String = "--") : Serializable

class NewsListViewModel : ViewModel() {

    @Inject
    lateinit var repository: NewsRepository

    val itemClickEvent = SingleLiveEvent<NewsItem>()

    var hasDetailView = false

    val items: LiveData<List<NewsItem>>
        get() = repository.getNews()

    val showDetailMediator = MediatorLiveData<NewsItem?>()
    val gotoDetailMediator = MediatorLiveData<NewsItem?>()

    init {
        NewsApplication.applicationComponent.inject(this)
        showDetailMediator.addSource(itemClickEvent, { if (hasDetailView) showDetailMediator.value = it })
        gotoDetailMediator.addSource(itemClickEvent, { if (!hasDetailView) gotoDetailMediator.value = it; afterGotoDetail() })
    }

    private fun afterGotoDetail() {
        gotoDetailMediator.value = null
    }
}