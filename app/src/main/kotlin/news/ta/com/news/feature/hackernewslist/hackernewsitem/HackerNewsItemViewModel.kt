package news.ta.com.news.feature.hackernewslist.hackernewsitem

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.support.annotation.VisibleForTesting
import news.ta.com.news.feature.NewsApplication
import news.ta.com.news.feature.hackernewslist.HackerNewsItem
import news.ta.com.news.feature.hackernewslist.HackerNewsRepository
import javax.inject.Inject

class HackerNewsItemViewModel(private val position: Int, private val kids: Boolean? = false) : ViewModel() {

    @Inject
    lateinit var repository: HackerNewsRepository

    val item: LiveData<HackerNewsItem>
        get() = repository.getNewsDetail(position)

    val itemMediator = MediatorLiveData<HackerNewsItem>()

    val topic = ObservableField<HackerNewsItem>()

    val loading = ObservableField<Boolean>(true)

    init {
        NewsApplication.applicationComponent.inject(this)
        itemMediator.addSource(item) { onGetData(it) }
    }

    @VisibleForTesting
    internal fun onGetData(newsItem: HackerNewsItem?) {
        newsItem?.let {
            when (kids) {
                true -> {
                    it.commentCount = it.list?.size
                    it.title = it.text
                }
            }
            topic.set(it)
            loading.set(false)
        }
    }
}