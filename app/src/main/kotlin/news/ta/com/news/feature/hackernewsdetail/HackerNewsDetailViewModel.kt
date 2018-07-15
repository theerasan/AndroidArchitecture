package news.ta.com.news.feature.hackernewsdetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import news.ta.com.news.feature.NewsApplication
import news.ta.com.news.feature.hackernewslist.HackerNewsItem
import news.ta.com.news.feature.hackernewslist.HackerNewsRepository
import javax.inject.Inject

class HackerNewsDetailViewModel(val id: Int) : ViewModel() {

    @Inject
    lateinit var repository: HackerNewsRepository

    private val itemData: LiveData<HackerNewsItem>
        get() = repository.getNewsDetail(id)

    val item = ObservableField<HackerNewsItem>()

    val itemMediator = MediatorLiveData<HackerNewsItem>()

    init {
        NewsApplication.applicationComponent.inject(this)
        itemMediator.addSource(itemData) {
            it?.let {
                if (it.commentCount == 0) {
                    it.commentCount = it.list?.size
                }
                item.set(it)
            }
            itemMediator.value = it
        }
    }
}