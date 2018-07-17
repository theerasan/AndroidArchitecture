package news.ta.com.news.feature.hackernewslist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.support.annotation.VisibleForTesting
import android.support.v4.widget.SwipeRefreshLayout
import news.ta.com.news.common.livedata.SingleLiveEvent
import news.ta.com.news.feature.NewsApplication
import javax.inject.Inject

class HackerNewsItem(var id: Int = 0,
                     var commentCount: Int? = 0,
                     var by: String? = "",
                     var title: String? = "",
                     var text: String? = "",
                     var list: List<HackerNewsItem>? = emptyList())

class HackerNewsViewModel : ViewModel() {
    @Inject
    lateinit var repository: HackerNewsRepository

    val items: LiveData<List<HackerNewsItem>>
        get() = repository.getNews()

    val itemMediator = MediatorLiveData<List<HackerNewsItem>>()

    val refreshing = ObservableBoolean(false)

    val onSwipeRefreshLayout = SwipeRefreshLayout.OnRefreshListener { onRefresh() }

    val itemClickEvent = SingleLiveEvent<HackerNewsItem>()

    init {
        NewsApplication.applicationComponent.inject(this)
        itemMediator.addSource(items) { onGetNewData(it) }
    }

    @VisibleForTesting
    internal fun onGetNewData(data: List<HackerNewsItem>?) {
        data?.let { refreshing.set(false) }
    }

    private fun onRefresh() {
        refreshing.set(true)
        repository.getNews()
    }
}