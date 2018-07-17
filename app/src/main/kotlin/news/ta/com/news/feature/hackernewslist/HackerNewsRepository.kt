package news.ta.com.news.feature.hackernewslist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.annotation.VisibleForTesting
import android.util.Log
import news.ta.com.news.feature.NewsApplication
import news.ta.com.news.model.HackerNewsDTO
import news.ta.com.news.services.enqueueWithProcessing

interface HackerNewsRepository {
    fun getNews(): LiveData<List<HackerNewsItem>>
    fun getNewsDetail(id: Int): LiveData<HackerNewsItem>
}

class HackerNewsRepositoryImpl : HackerNewsRepository {

    @VisibleForTesting
    var service = NewsApplication.applicationComponent.getHackerNewsService()

    private val items = MutableLiveData<List<HackerNewsItem>>()

    override fun getNews(): LiveData<List<HackerNewsItem>> {
        service.getHackerNewsList().enqueueWithProcessing(
                preProcessing = {
                    it?.map { it.convertToHackerNewsItem() }
                },
                success = {
                    items.value = it
                },
                fail = { _, t ->
                    Log.w(this.javaClass.name, t?.message ?: "")
                }
        )

        return items
    }

    override fun getNewsDetail(id: Int): LiveData<HackerNewsItem> {
        val items = MutableLiveData<HackerNewsItem>()

        service.getHackerNewsDetail(id).enqueueWithProcessing(
                preProcessing = {
                    it?.convertToHackerNewsItem()
                },
                success = {
                    items.value = it
                },
                fail = { _, _ ->
                }
        )

        return items
    }
}

@VisibleForTesting
internal fun HackerNewsDTO.convertToHackerNewsItem(): HackerNewsItem {
    return HackerNewsItem(id = this.id ?: 0,
            text = this.text,
            by = this.by,
            title= this.title,
            commentCount = this.comment,
            list = this.kids?.map { it.convertToHackerNewsItem() })
}

@VisibleForTesting
internal fun Int.convertToHackerNewsItem(): HackerNewsItem {
    return HackerNewsItem(id = this)
}

