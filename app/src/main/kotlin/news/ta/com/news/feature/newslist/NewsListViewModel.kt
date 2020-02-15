package news.ta.com.news.feature.newslist

import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import news.ta.com.news.common.livedata.SingleLiveEvent
import news.ta.com.news.feature.NewsApplication
import java.io.Serializable
class NewsItem(val id: Int = 0,
               val thumbnail: String = "",
               val headline: String = "--",
               val description: String = "--",
               val link: String = "",
               val source: String = "--") : Serializable

class NewsListViewModel(private val repository: NewsRepository) : ViewModel() {

    val itemClickEvent = SingleLiveEvent<NewsItem>()

    var hasDetailView = false

    val items: LiveData<List<NewsItem>>
        get() = repository.getNews()

    val showDetailMediator = MediatorLiveData<NewsItem?>()
    val gotoDetailMediator = MediatorLiveData<NewsItem?>()

    val selectedCount = ObservableField<String>("0")

    init {
        showDetailMediator.addSource(itemClickEvent) { if (hasDetailView) showDetailMediator.value = it }
        gotoDetailMediator.addSource(itemClickEvent) { if (!hasDetailView) gotoDetailMediator.value = it; afterGotoDetail() }
    }

    @VisibleForTesting
    fun afterGotoDetail() {
        gotoDetailMediator.value = null
    }

    fun setStatic(list: List<NewsItem>?) {
        NewsApplication.news = list?.get(0)
    }
}